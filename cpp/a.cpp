#include <iostream>
#include <string>
using namespace std;

void changeword(char c) {
    if (c=='B') cout<<"v";
    else if (c=='E') cout<<"ye";
    else if (c=='H') cout<<"n";
    else if (c=='P') cout<<"r";
    else if (c=='C') cout<<"s";
    else if (c=='Y') cout<<"u";
    else if (c=='X') cout<<"h";
    else cout<<(char )(c+('a'-'A'));
}
int main(void)
{
    string temp;
    cin>>temp;
    for (int i = 0 ; i<temp.length() ; i++){
        changeword(temp[i]);
    }
    return (0);
}