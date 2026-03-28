#include <iostream>
using namespace std;
int main(){
    int age = 20;
    char grade ='A';
    float pi = 3.14;
    bool isSafe = true;
    double price = 100.990;

    cout << grade << endl;
    cout << sizeof(grade) << endl;
    cout << age << endl;
    cout << sizeof(age) << endl;
    cout << pi << endl;
    cout << isSafe << endl;
    cout << price << endl;
    // int = 4bytes;
    // char = 1bytes;
    // float = 4bytes;
    // boolean = 1bytes;
    // if true then 1 , false 0;
    // double = 8bytes;
    //they all are primitive 
    // .cpp is C++ exetension for files
    // Implicit means automatic conversion from small dt to large eg: float(4 bytes) -> double (8 bytes)
    char grades = 'A';
    int value = grades;
    cout << value << endl;
    // Explicit means forcefully conversation from large to small dt eg: double -> int 
    double rupees = 100.99;
    int newPrice = (int)rupees;
    cout << newPrice << endl;
    return 0;
} // C++,Java,c are Case sensitive language