

#ifndef childAnimal_h
#define childAnimal_h

#include <stdio.h>
#include <iostream>
#include "Animal.h"

using namespace std;

class Dog : public Mammal {
    private:
        string _owner;

    public:
        Dog();
        Dog(string n, COLOR c, string owner);

        ~Dog();

        void speak() const;
        void move() const;
        void eat() const;
};

class Cat : public Mammal {

    public:
        Cat();
        Cat(string n, COLOR c);

        ~Cat();

        void speak() const;
        void move() const;
        void eat() const;
};

class Lion : public Mammal {

    public:
        Lion();
        Lion(string n, COLOR c);

        ~Lion();

        void speak() const;
        void move() const;
        void eat() const;
};



#endif
