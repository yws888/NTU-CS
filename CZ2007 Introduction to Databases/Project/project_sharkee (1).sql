CREATE SCHEMA IF NOT EXISTS Sharkee;
USE Sharkee ;
 
CREATE TABLE IF NOT EXISTS Sharkee.Shops (
  shopName VARCHAR(50) PRIMARY KEY,
  location VARCHAR(50),
  shopOwner VARCHAR(20)
);
  
CREATE TABLE IF NOT EXISTS Sharkee.Users (
  userID INT PRIMARY KEY,
  uName VARCHAR(50) NOT NULL
);
  
CREATE TABLE IF NOT EXISTS Sharkee.Orders (
  orderID INT PRIMARY KEY,
  shippingAddress VARCHAR(200) NOT NULL,
  userID INT,
   FOREIGN KEY (userID) REFERENCES Sharkee.Users (userID)
    ON DELETE SET NULL
    ON UPDATE CASCADE);
 
CREATE TABLE IF NOT EXISTS Sharkee.Products (
  productName VARCHAR(50) PRIMARY KEY,
  maker VARCHAR(50),
  category VARCHAR(50));
  
CREATE TABLE IF NOT EXISTS Sharkee.Product_list_in_shop (
  serialNo INT PRIMARY KEY,
  productName VARCHAR(50),
  shopName VARCHAR(50),
    FOREIGN KEY (productName) REFERENCES Sharkee.Products (productName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (shopName) REFERENCES Sharkee.Shops (shopName)  
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS Sharkee.Product_info_in_shop (
  shopName VARCHAR(50) NOT NULL,
  productName VARCHAR(50) NOT NULL,
  priceVariation FLOAT,
  quantity INT NOT NULL
              	CHECK(quantity>=0),
  startDate DATETIME NOT NULL,
  endDate DATETIME,
  CONSTRAINT prcDate CHECK(endDate IS NULL OR endDate>=startDate),
  suggestedPrice FLOAT DEFAULT 0,
              	CHECK(suggestedPrice>=0),
  PRIMARY KEY (shopName, productName),
    FOREIGN KEY (shopName) REFERENCES Sharkee.Shops (shopName)  
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (productName) REFERENCES Sharkee.Products (productName)  
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS Sharkee.Product_list_in_order (
  orderID INT NOT NULL,
  shopName VARCHAR(50) NOT NULL,
  productName VARCHAR(50) NOT NULL,
  orderPrice FLOAT NOT NULL
              	CHECK(orderPrice>=0),
  orderQuantity INT NOT NULL
              	CHECK(orderQuantity>=0),
  orderDateTime DATETIME,
  PRIMARY KEY (orderID, shopName, productName),
    FOREIGN KEY (orderID) REFERENCES Sharkee.Orders (orderID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (shopName) REFERENCES Sharkee.Shops (shopName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (productName) REFERENCES Sharkee.Products (productName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS Sharkee.Product_info_in_order (
  serialNo INT, 
  orderID INT NOT NULL,
  shopName VARCHAR(50),
  productName VARCHAR(50),
  productStatus VARCHAR(20) DEFAULT 'being processed'
              	CHECK(productStatus IN 
        ('being processed', 'shipped', 'delivered', 'returned')),
  deliveryDate DATETIME,
    PRIMARY KEY (serialNo),
    FOREIGN KEY (productName) REFERENCES Sharkee.Products (productName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (shopName) REFERENCES Sharkee.Shops (shopName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY(orderID) REFERENCES Sharkee.Orders(orderID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
 
CREATE TABLE IF NOT EXISTS Sharkee.Price_history (
  serialNo INT NOT NULL,
  startDate DATETIME NOT NULL,
  endDate DATETIME,
  		CONSTRAINT priceDate CHECK(endDate IS NULL OR endDate>startDate),
  retailPrice FLOAT NOT NULL,
              	CHECK(retailPrice>=0),
  UNIQUE(serialNo, endDate),
  PRIMARY KEY (serialNo, startDate),
 FOREIGN KEY (serialNo) REFERENCES Sharkee.Product_list_in_shop(serialNo) 
 ON DELETE CASCADE  
 ON UPDATE CASCADE);
  
CREATE TABLE IF NOT EXISTS Sharkee.Feedback (
  feedbackID INT PRIMARY KEY,
  userID INT,
  productName VARCHAR(50) NOT NULL,
  rating INT,
		CHECK (rating BETWEEN 1 AND 5),
  commentDateTime DATETIME DEFAULT NOW(),
  comment VARCHAR(500) NOT NULL,
  UNIQUE(userID, productName),
    FOREIGN KEY (userID) REFERENCES Sharkee.Users (userID)  
    ON DELETE SET NULL 
    ON UPDATE CASCADE,
    FOREIGN KEY (productName) REFERENCES Sharkee.Products (productName)  
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
 
CREATE TABLE IF NOT EXISTS Sharkee.Employees (
  employeeID INT PRIMARY KEY,
  employeeName VARCHAR(50),
  salary FLOAT NOT NULL
              	CHECK(salary>=0));
        
CREATE TABLE IF NOT EXISTS Sharkee.Complaints (
  complaintID INT PRIMARY KEY,
  text varchar(200) NOT NULL,
  status VARCHAR(20) DEFAULT 'pending',
		CHECK (status IN ('pending', 'being handled', 'addressed')),
  filedDateTime DATETIME NOT NULL,
  handledDateTime DATETIME,
  CONSTRAINT cmp_date 
              	CHECK(handledDateTime IS NULL OR handledDateTime > filedDateTime),
  userID INT,
  employeeID INT,
  UNIQUE(employeeID, handledDateTime),
  UNIQUE(userID, filedDateTime),
    FOREIGN KEY (userID) REFERENCES Sharkee.Users (userID)  
    ON DELETE SET NULL 
    ON UPDATE CASCADE,
    FOREIGN KEY (employeeID) REFERENCES Sharkee.Employees (employeeID) 
    ON DELETE SET NULL 
    ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS Sharkee.Complaints_on_Shop (
  complaintID INT PRIMARY KEY,
  shopName VARCHAR(50) NOT NULL,
    FOREIGN KEY (complaintID) REFERENCES Sharkee.Complaints (complaintID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (shopName) REFERENCES Sharkee.Shops (shopName) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);
 
CREATE TABLE IF NOT EXISTS Sharkee.Complaints_on_order (
  complaintID INT PRIMARY KEY,
  orderID INT NOT NULL,
    FOREIGN KEY (complaintID)  REFERENCES Sharkee.Complaints (complaintID)  
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    FOREIGN KEY (orderID) REFERENCES Sharkee.Orders (orderID)   
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);
