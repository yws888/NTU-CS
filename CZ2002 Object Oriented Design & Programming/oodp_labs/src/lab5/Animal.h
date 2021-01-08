
#ifndef Animal_h
#define Animal_h


#include <stdio.h>
#include <string>

using namespace std;


enum COLOR { Green, Blue, White, Black, Brown };

class Animal {
    private :
        string _name;
        COLOR _color ;

    public:
        Animal();
        Animal(string n, COLOR c);

        ~Animal();
        
        virtual void speak() const;
        virtual void move() const = 0;
        virtual void eat() const = 0;


};

class Mammal: public Animal {
    public:
        Mammal();
        Mammal(string n, COLOR c);
        
        ~Mammal();

         virtual void eat() const;
          void move() const;
};
#endif
