# Query 1: Find the average price of “iPhone Xs” on Sharkee from 1 August 2020 to 31 August 2020.
SELECT AVG(retailPrice) AS avgPrice
FROM Price_history
WHERE (startDate BETWEEN '2020-08-01' AND '2020-08-31' OR
	   endDate BETWEEN '2020-08-01' AND '2020-08-31' OR
       (startDate<'2020-07-31' AND endDate is NULL)) AND 
	   serialNo IN (SELECT serialNo
					FROM Product_list_in_shop
					WHERE productName='iPhone Xs');
                              
                              
# Query 2: Find products that received at least 100 ratings of “5” in August 2020, and order them by their average ratings.
# Multi-query method
CREATE TEMPORARY TABLE Feedback2
SELECT productName
FROM Feedback
WHERE rating=5 AND commentDateTime BETWEEN '2020-08-01' AND '2020-08-31'
GROUP BY productName
HAVING COUNT(rating)>=100;

CREATE TEMPORARY TABLE Feedback3
SELECT productName, AVG(rating) AS avgRating
FROM Feedback
WHERE commentDateTime BETWEEN '2020-08-01' AND '2020-08-31'
GROUP BY productName
HAVING COUNT(rating)>=100;

SELECT productName, avgRating
FROM Feedback2
NATURAL JOIN Feedback3
ORDER BY avgRating;

DROP TEMPORARY TABLE Feedback2, Feedback3;

# Single-query method
SELECT productName, avgRating
FROM (
	SELECT productName
	FROM Feedback
	WHERE rating=5 AND commentDateTime BETWEEN '2020-08-01' AND '2020-08-31'
	GROUP BY productName
	HAVING COUNT(rating)>=100
	)Feedback2
NATURAL JOIN (
			 SELECT productName, AVG(rating) AS avgRating
			 FROM Feedback
			 WHERE commentDateTime BETWEEN '2020-08-01' AND '2020-08-31'
             GROUP BY productName
             HAVING COUNT(rating)>=100
             )Feedback3
ORDER BY avgRating;


# Query 3: For all products purchased in June 2020 that have been delivered, find the average time from the ordering date to the delivery date.
# To find the average time for each product
# Multi-query method
CREATE TEMPORARY TABLE June_products
SELECT *
FROM Product_list_in_order
WHERE orderDateTime BETWEEN '2020-06-01' AND '2020-06-30';

SELECT productName, AVG(datediff(deliveryDate, orderDateTime)) AS avgDeliveryTimeInDays
FROM Product_info_in_order
NATURAL JOIN June_products
WHERE productStatus IN ('delivered','returned')
GROUP BY productName;

DROP TEMPORARY TABLE June_products;

# Single-query method
SELECT productName, AVG(datediff(deliveryDate, orderDateTime)) AS avgDeliveryTimeInDays
FROM Product_info_in_order
NATURAL JOIN 
	(SELECT *
	FROM Product_list_in_order
	WHERE orderDateTime BETWEEN '2020-06-01' AND '2020-06-30'
    )June_products
WHERE productStatus IN ('delivered','returned')
GROUP BY productName;

# To find total average time of all products
# Multi-query method
CREATE TEMPORARY TABLE June_products
SELECT *
FROM Product_list_in_order
WHERE orderDateTime BETWEEN '2020-06-01' AND '2020-06-30';

SELECT AVG(datediff(deliveryDate, orderDateTime)) AS avgDeliveryTimeInDays
FROM Product_info_in_order
NATURAL JOIN June_products
WHERE productStatus IN ('delivered','returned');

DROP TEMPORARY TABLE June_products;

# Single-query method
SELECT AVG(datediff(deliveryDate, orderDateTime)) AS avgDeliveryTimeInDays
FROM Product_info_in_order
NATURAL JOIN 
	(SELECT *
	FROM Product_list_in_order
	WHERE orderDateTime BETWEEN '2020-06-01' AND '2020-06-30'
    )June_products
WHERE productStatus IN ('delivered','returned');


/* Query 4: Let us define the “latency” of an employee by the average that he/she takes to process a complaint.
Find the employee with the smallest latency. */
# Multi-query method
CREATE TEMPORARY TABLE Avg_latency
SELECT employeeID, AVG(datediff(handledDateTime, filedDateTime)) AS avgLatency
FROM Complaints
WHERE status='addressed'
GROUP BY employeeID;

CREATE TEMPORARY TABLE Min_avg_latency
SELECT MIN(avgLatency) AS minLatency
FROM Avg_latency;

SELECT employeeID, employeeName
FROM Avg_latency
NATURAL JOIN Employees
WHERE avgLatency= (SELECT minLatency FROM Min_avg_latency);

DROP TEMPORARY TABLE Avg_latency, Min_avg_latency;

# Single-query method
SELECT t1. employeeID, t1.employeeName
FROM 
	(SELECT employeeID,employeeName, AVG(datediff(handledDateTime, filedDateTime)) AS avgLatency
	FROM Complaints
    NATURAL JOIN employees
	WHERE status='addressed'
	GROUP BY employeeID,employeeName) t1
	WHERE t1.avgLatency= 
		(SELECT MIN(avgLatency) 
		FROM 
			(SELECT employeeID, AVG(datediff(handledDateTime, filedDateTime)) AS avgLatency
			FROM Complaints
			WHERE status='addressed'
			GROUP BY employeeID) t2
		);


/* Query 5: Produce a list that contains (i) all products made by Samsung, and
(ii) for each of them, the number of shops on Sharkee that sell the product. */
# Multi-query method
CREATE TEMPORARY TABLE Samsung_products
SELECT DISTINCT productName, shopName
FROM Product_list_in_shop
NATURAL JOIN Products
WHERE maker='Samsung';

SELECT productName, COUNT(shopName) AS numOfShops
FROM Samsung_products
GROUP BY productName;

DROP TEMPORARY TABLE Samsung_products;

# Single-query method
SELECT productName, COUNT(shopName) AS numOfShops
FROM 
	(SELECT DISTINCT productName, shopName
	FROM Product_list_in_shop
	NATURAL JOIN Products
	WHERE maker='Samsung') Samsung_products
GROUP BY productName;


# Query 6: Find shops that made the most revenue in August 2020.
# Multi-query method
CREATE TEMPORARY TABLE Shop_revenue
SELECT shopName, SUM(orderPrice*orderQuantity) AS revenue
FROM Product_list_in_order
WHERE orderDateTime BETWEEN '2020-08-01' AND '2020-08-31'
GROUP BY shopName;

CREATE TEMPORARY TABLE Max_revenue
SELECT MAX(revenue) AS revenue
FROM Shop_revenue;

SELECT DISTINCT shopName
FROM Shop_revenue
NATURAL JOIN Max_revenue;

DROP TEMPORARY TABLE Shop_revenue, Max_revenue;

# Single-query method
SELECT t1.shopName
FROM
	(SELECT shopName, SUM(orderPrice*orderQuantity) AS revenue
	FROM Product_list_in_order
	WHERE orderDatetime BETWEEN '2020-08-01' AND '2020-08-31'
	GROUP BY shopName) t1
WHERE t1.revenue=
	(SELECT MAX(revenue)
    FROM
		(SELECT shopName, SUM(orderPrice*orderQuantity) AS revenue
		FROM Product_list_in_order
		WHERE orderDatetime BETWEEN '2020-08-01' AND '2020-08-31'
		GROUP BY shopName) t2
	);


# Query 7: For users that made the most amount of complaints, find the most expensive products he/she has ever purchased.
# Multi-query method
CREATE TEMPORARY TABLE Complaint_count_1
SELECT userID, COUNT(complaintID) AS complaintCount
FROM Complaints
GROUP BY userID;

CREATE TEMPORARY TABLE Complaint_count_2
SELECT MAX(complaintCount) as maxComplaintCount
FROM Complaint_count_1;

CREATE TEMPORARY TABLE Max_complaint_count
SELECT a.userID,a.complaintCount
FROM Complaint_count_1 a, Complaint_count_2 b
WHERE a.complaintCount=b.maxComplaintCount;

CREATE TEMPORARY TABLE User_order_ID
SELECT DISTINCT O.orderID, C.userID
FROM Orders O, Complaints C, Product_list_in_order P
WHERE O.orderID=P.orderID AND C.userID=O.userID;

CREATE TEMPORARY TABLE Max_user_order
SELECT userID, orderID
FROM User_order_ID
WHERE userID IN (SELECT userID FROM Max_complaint_count);

CREATE TEMPORARY TABLE Max_user_products
SELECT *
FROM Max_user_order
NATURAL JOIN Product_list_in_order;

CREATE TEMPORARY TABLE Max_user_price
SELECT userID, MAX(orderPrice) AS orderPrice
FROM Max_user_products
GROUP BY userID;

SELECT DISTINCT userID, productName
FROM (SELECT *
	  FROM Max_user_products
	  NATURAL JOIN Max_user_price) Product_name;

DROP TEMPORARY TABLE Complaint_count_1, Complaint_count_2, Max_complaint_count, User_order_ID, Max_user_order, Max_user_products, Max_user_price;

# Single query method
SELECT DISTINCT userID, productName
FROM product_list_in_order 
JOIN
(SELECT userID, MAX(orderPrice) AS maxPrice
 FROM
	(SELECT userID, COUNT(complaintID) AS cnt
	FROM complaints c
	GROUP BY userID
	HAVING cnt=(SELECT MAX(t.cnt) AS maxCnt
				FROM(
				SELECT userID, COUNT(complaintID) AS cnt
				FROM complaints c
				GROUP BY userID) t
				)
	) t1
NATURAL JOIN orders o
NATURAL JOIN product_list_in_order plo
GROUP BY userID) t2
ON userID=t2.userID AND orderPrice=maxPrice;


# Query 8: Find products that have never been purchased by some users, but are the top 5 most purchased products by other users in August 2020.
# Multi-query method
CREATE TEMPORARY TABLE Aug_orders
SELECT orderID, userID
FROM orders
WHERE orderID IN (SELECT orderID FROM Product_list_in_order WHERE orderDateTime BETWEEN '2020-08-01' AND '2020-08-31');

CREATE TEMPORARY TABLE Aug_order_info
SELECT productName, COUNT(DISTINCT userID) as count
FROM Aug_orders
NATURAL JOIN Product_list_in_order
GROUP BY ProductName
ORDER BY count DESC;

SELECT productName
FROM Aug_order_info
WHERE count<(SELECT COUNT(userID) FROM users) LIMIT 5;

DROP TEMPORARY TABLE Aug_orders, Aug_order_info;

# Single-query method
SELECT productName
FROM (
		SELECT productName, COUNT(DISTINCT userID) as count
		FROM (SELECT orderID, userID
			FROM orders
			WHERE orderID IN (SELECT orderID 
						FROM Product_list_in_order 
						WHERE orderDateTime BETWEEN '2020-08-01' AND '2020-08-31')
						)Aug_orders
		NATURAL JOIN Product_list_in_order
		GROUP BY ProductName
		ORDER BY count DESC
	  )Aug_order_info
WHERE count<(SELECT COUNT(userID) FROM users) LIMIT 5;


# Query 9: Find products that are increasingly being purchased over at least 3 months.
CREATE VIEW monthPurchase AS
SELECT productName, SUM(orderQuantity) AS total, MONTH(orderDateTime) AS months
FROM product_list_in_order
GROUP BY productName, months
ORDER BY productName, months;

SELECT m1.productName
FROM monthPurchase m1, monthPurchase m2, monthPurchase m3
WHERE m1.productName=m2.productName AND m1.productName=m3.productName AND
   m1.months=m2.months-1 AND m1.months=m3.months-2 AND
      m1.total<m2.total AND m2.total<m3.total;

DROP VIEW monthPurchase;
      
# EXTRA QUERIES
# Retrieve the min price of iphone 11 on 2020-08-10
# The price and product name can be changed randomly and generalised to stored procedure
SELECT DISTINCT shopName
FROM price_history NATURAL JOIN product_list_in_shop
WHERE startDate<='2020-08-20' AND 
		(endDate>'2020-08-20' OR endDate IS NULL) AND
		productName='Iphone 11' AND
		retailPrice=
			(SELECT MIN(retailPrice)
			FROM price_history NATURAL JOIN product_list_in_shop
			WHERE startDate<='2020-08-20' AND 
				  (endDate>'2020-08-20' OR endDate IS NULL) AND
					productName='Iphone 11');
                    
DELIMITER $$
CREATE PROCEDURE checkPrice
(
	orderDate DATETIME,
    pName VARCHAR(50)
)
BEGIN
	SELECT DISTINCT shopName
	FROM price_history NATURAL JOIN product_list_in_shop
	WHERE startDate<=orderDate AND 
		(endDate>orderDate OR endDate IS NULL) AND
		productName=pName AND
		retailPrice=
			(SELECT MIN(retailPrice)
			FROM price_history NATURAL JOIN product_list_in_shop
			WHERE startDate<=orderDate AND 
				  (endDate>orderDate OR endDate IS NULL) AND
				  productName=pName);
END $$
DELIMITER ;
CALL checkPrice('2020-07-20','Iphone 11');

DROP PROCEDURE checkPrice;


# Find the orders that can save some money if some products are chosen from other shops, and their savings
SELECT orderID, 
	   ROUND(SUM((orderPrice-minPrice)*orderQuantity),1) AS totalSave
FROM
(SELECT plo.orderID, plo.productName, plo.orderPrice, plo.orderQuantity,
		(SELECT MIN(retailPrice)
		FROM price_history NATURAL JOIN product_list_in_shop
		WHERE startDate<=plo.orderDateTime AND 
			(endDate>plo.orderDateTime OR 
             endDate IS NULL) AND
			productName=plo.productName
	    ) AS minPrice
FROM Product_list_in_order plo
WHERE plo.orderPrice>
	(	SELECT MIN(retailPrice)
		FROM price_history 
        NATURAL JOIN product_list_in_shop
		WHERE startDate<=plo.orderDateTime AND 
			(endDate>plo.orderDateTime OR 
             endDate IS NULL) AND
			productName=plo.productName
	) AND plo.orderDateTime>='2020-06-01' AND
          plo.orderDateTime<'2020-08-01'
) AS min
GROUP BY orderID;


# Find products whose prices have not been increased in some shops
SELECT DISTINCT productName,shopName
FROM price_history
NATURAL JOIN product_list_in_shop
WHERE serialNo NOT IN
	(SELECT DISTINCT ph1.serialNo
	FROM price_history ph1
	WHERE EXISTS(
		SELECT *
		FROM price_history ph2
		WHERE ph1.serialNo=ph2.serialNo AND
		ph1.startDate<ph2.StartDate AND
		ph1.retailPrice<ph2.retailPrice
	));
    

# Find the customer who bought the most items in each shop and the number of products they bought
SELECT * 
FROM
(SELECT shopName, MAX(totalProduct) AS totalProduct
FROM
(SELECT userID,shopName,SUM(orderQuantity) AS totalProduct
FROM product_list_in_order
NATURAL JOIN orders
WHERE userID IN(
		SELECT userID
        FROM (
			SELECT userID, COUNT(orderID) AS cnt
			FROM orders
			GROUP BY userID
            HAVING cnt>1
			) cnt
		)
GROUP BY userID,shopName) p
GROUP BY shopName) P2
NATURAL JOIN 
(SELECT userID,shopName,SUM(orderQuantity) AS totalProduct
FROM product_list_in_order
NATURAL JOIN orders
WHERE userID IN(
		SELECT userID
        FROM (
			SELECT userID, COUNT(orderID) AS cnt
			FROM orders
			GROUP BY userID
            HAVING cnt>1
			) cnt
		)
GROUP BY userID,shopName) P1;