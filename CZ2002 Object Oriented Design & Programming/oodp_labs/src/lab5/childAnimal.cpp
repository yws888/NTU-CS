

#include <stdio.h>
#include "childAnimal.h"
#include <string>
using namespace std;

        Dog:: Dog(): Mammal() {
            this->_owner = "unknown";
        }
    
        Dog:: Dog (string n, COLOR c, string owner) : Mammal(n, c) {
            this->_owner = owner;
            cout << "Dog owner is " << (this->_owner) << endl;
        }
    
    Dog:: ~Dog() {
        cout << "Destructing Dog :/" << endl;
    };
    
    void Dog::speak() const {
        cout << "Woof"<< endl ;
    }
    
    void Dog::eat() const {
        cout << "Dog eats"<< endl ;
    }

    void Dog::move() const {
        cout << "Dog moving" << endl;
    };


    Cat::Cat (string n, COLOR c) : Mammal(n, c) {
    };
    
    Cat::Cat(): Mammal() {
    }
    
    Cat::~Cat() {
        cout << "Destructing Cat" << endl;
    };
    
    void Cat::speak() const {
        cout << "Cat meow"<< endl ;
    };
    
    void Cat::move() const {
        cout << "Cat moving" << endl;
    };
    
    void Cat::eat() const {
        cout << "Cat eats"<< endl ;
    }


    Lion:: Lion (string n, COLOR c) : Mammal(n, c) {
    };
    
    Lion:: Lion(): Mammal() {
    }
    
    Lion:: ~Lion() {
        cout << "Destructing Lion" << endl;
    };
    
    void Lion:: speak() const {
        cout << "Lion Roar"<< endl ;
    };
    
    void Lion:: move() const {
        cout << "Lion moving" << endl;
    };
    
    void Lion:: eat() const {
        cout << "Lion eats"<< endl ;
    }

