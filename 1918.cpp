#include<iostream>
#include<cstdio>
#include<stack>
using namespace std;
bool isalpa (char ch){
    if( ch == '*' || ch =='/' || ch=='+' || ch == '-') return false;
    return true;
}

int priority(char ch) {
    if(ch=='*' || ch =='/') return 2;
    else if ( ch =='+'|| ch =='-') return 1;
    return 0;
}
int main(void) {
    string str;
    cin>>str;
    stack<int> s;
    for(int i = 0 ; i<str.length(); i++) {
        if(str[i] =='(') {
            s.push(str[i]);
        }
        else if( str[i] ==')') {
            while(!s.empty()) {
                if( s.top() =='(') break;
                cout<<(char)s.top();
              s.pop();
            }
            s.pop();
        }
        else if( isalpa(str[i])==true ) {
            cout<<str[i];
        }
        else {
            // 연산자
             while(!s.empty()) {
                int temp = priority(s.top());
                if( temp >=priority(str[i])) {
                    cout<<(char)s.top();
                    s.pop();
                }
                else {
                    break;
                }
                }
            s.push(str[i]);
        }
      


    }  while(!s.empty()) {
            cout<<(char)s.top();
            s.pop();
        }
    return 0;
}