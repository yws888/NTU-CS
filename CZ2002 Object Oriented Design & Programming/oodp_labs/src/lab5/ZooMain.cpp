

#include <stdio.h>
#include "Animal.h"
#include "childAnimal.h"
using namespace std;


int main() {
    /*
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
    aniVal.speak() ;*/
    
    int choice;
    Mammal **mammals = new Mammal*[3];
    mammals[0] = new Dog();
    mammals[1] = new Cat();
    mammals[2] = new Lion();
    
    do
    {
        cout << "Select the animal to send to Zoo :" << endl;
        cout << "(1) Dog (2) Cat (3) Lion (4) Move all animals (5) Quit" << endl;
        cin >> choice;

        switch(choice)
        {
            case 1:
                mammals[0]->move();
                mammals[0]->speak();
                mammals[0]->eat();
                break;

            case 2:
                mammals[1]->move();
                mammals[1]->speak();
                mammals[1]->eat();
                break;

            case 3:
                mammals[2]->move();
                mammals[2]->speak();
                mammals[2]->eat();
                break;

            case 4:
                for(int i=0; i<3; i++)
                {
                    mammals[i]->move();
                    mammals[i]->speak();
                    mammals[i]->eat();
                }
                break;
            default:
                break;
        }
    }while(choice != 5);

    for(int i=0; i<3; i++)
        delete mammals[i];
    delete [] mammals;

    cout << "Program exiting …. "<< endl ;
    
    return 0;
}
