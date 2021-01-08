#include "Animal.h"
#include <iostream>
#include <string>

using namespace std ;

 Animal :: Animal() : _name("unknown") {
     cout << "constructing Animal object "<< _name << endl ;
 }
 
 Animal:: Animal(string n, COLOR c) {
     _name = n;
     _color = c;
     cout << "constructing Animal object named "<< _name << endl;
     switch (_color) {
         case 0:
             cout << "Its color is Green" << endl;
             break;
         case 1:
             cout << "Its color is Blue" << endl;
             break;
         case 2:
             cout << "Its color is White" << endl;
             break;
         case 3:
             cout << "Its color is Black" << endl;
             break;
         case 4:
             cout << "Its color is Brown" << endl;
             break;
         default:
             break;
     }

 }
 Animal:: ~Animal() {
     cout << "destructing Animal object "<< _name << endl ;
 }
 
  void Animal:: speak() const {
     cout << "Animal speaks "<< endl ;
 }
 
 

 
 Mammal:: Mammal(): Animal() {
 }
 
     Mammal:: Mammal (string n, COLOR c) : Animal(n, c) {
     
     }
     
     Mammal:: ~Mammal() {
         cout << "destructing Mammal object " << endl;
     }
 
void Mammal:: move() const {
     cout << "Mammal moving " << endl;
 };


 void Mammal :: eat() const {
    cout << "Mammal eats " << endl ;
}
/* enum COLOR { Green, Blue, White, Black, Brown } ;
class Animal {
 public :
    Animal() : _name("unknown") {
        cout << "constructing Animal object "<< _name << endl ;
    }
    
    Animal(string n, COLOR c) {
        _name = n;
        _color = c;
        cout << "constructing Animal object named "<< _name << endl;
        switch (_color) {
            case 0:
                cout << "Its color is Green" << endl;
                break;
            case 1:
                cout << "Its color is Blue" << endl;
                break;
            case 2:
                cout << "Its color is White" << endl;
                break;
            case 3:
                cout << "Its color is Black" << endl;
                break;
            case 4:
                cout << "Its color is Brown" << endl;
                break;
            default:
                break;
        }

    }
    ~Animal() {
        cout << "destructing Animal object "<< _name << endl ;
    }
    
    //for 3.2
    virtual void speak() const {
        cout << "Animal speaks "<< endl ;
    }
    
    virtual void move() const = 0;
    
    virtual void eat() const = 0;
    
    
 private :
        string _name;
        COLOR _color ;
};



class Mammal: public Animal{
    public:
        virtual void eat() const {
            cout << "Mammal eats " << endl ;
        }
    
    Mammal(): Animal() {
    }
    
        Mammal (string n, COLOR c) : Animal(n, c) {
        
        }
        
        ~Mammal() {
            cout << "destructing Mammal object " << endl;
        }
    
   void move() const {
        cout << "Mammal moving " << endl;
    };

};

class Dog: public Mammal {
    public:
        Dog(): Mammal() {
        }
    
        Dog (string n, COLOR c, string owner) : Mammal(n, c) {
            this->owner = owner;
            cout << "Dog owner is " << (this->owner) << endl;
        }
    
    ~Dog() {
        cout << "Destructing Dog :/" << endl;
    };
    
    void speak() const {
        cout << "Woof"<< endl ;
    }
    
    void eat() const {
        cout << "Dog eats"<< endl ;
    }
    
    private:
        string owner;
    
};

int main() {
    //for 3.1
    Animal a("Rufus", Blue);
    a.speak() ;
    cout << "\n";
    
    //for 3.2
    Mammal ma("Rufus", Blue);
    Animal &a = ma;
    a.speak() ;
    cout << "\n";
    
    
    
    //for 3.3
    Animal *animalPtr = new Dog("Lassie", White, "Andy");
    animalPtr->move();
    animalPtr->speak();
    delete animalPtr;
    
    Mammal m("Simba", Brown);
    m.speak() ;
    cout << "\n";
    
    Dog dog("Doge", White, "WS");
    dog.speak() ;
    dog.move();
    cout << "\n";
    
    Dog dogi("Lassie", White, "Andy");
    Mammal *aniPtr = &dogi ;
    Mammal &aniRef = dogi ;
    Mammal aniVal = dogi ;
    aniPtr->speak() ;
    aniRef.speak() ;
    aniVal.speak() ;

    
    
    cout << "Program exiting .... "<< endl ;
    return 0;
    
} */


