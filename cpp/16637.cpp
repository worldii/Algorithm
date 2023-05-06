#include<iostream>
#include<queue>
#include<stack>
#include<cstdio>
#include<algorithm>
#include<cstring>
#include<sstream>
#include<cmath>
#include<string>
#include<set>
using namespace std;
int n,m,l,r,x;
int num ;
    string s;

vector <char> t;
int flag = -1000000000;
vector<string> str;
void go (int cnt ){
    if( cnt == num) {
        // check 해서 
        //숫자 max 인거 반납.
        int sum=0;
       for (int i = 0 ; i< str.size() ; i++) {
           cout<<str[i]<<"    ";
       }
        cout<<'\n';
        int prev = -1;
        for (int i = 0 ; i< str.size();  i++) {
            if( str[i].length()!=1) {
                 int temp=0;
                    if(str[i][1] =='*') {
                        temp = (str[i][0]-'0') *(str[i][2]-'0');
                    }
                    else if (str[i][1] =='-' ) {
                           temp = (str[i][0]-'0') -(str[i][2]-'0');
                    }
                    else if( str[i][1]== '+') {
                           temp = (str[i][0]-'0') +(str[i][2]-'0');
                    }
                if( prev==-1) {
                   
                    sum = temp;
                }
                else {
                    if(prev==1) {
                        sum = sum * temp;
                    }
                    else if (prev==2) {
                         sum = sum - temp;
                    }
                    else if (prev==3) {
                         sum = sum + temp;
                    }
                }
            }
            else {
                if( str[i][0] =='*') {
                    prev = 1;
                }
                else if ( str[i][0] =='-') {
                    prev =2;
                }
                else if ( str[i][0] =='+') {
                    prev = 3;
                }
                else {
                    if( prev ==-1) {
                        sum = str[i][0] -'0';
                    }
                    else {
                        if( prev==1) {
                            sum = sum * ( str[i][0] -'0');
                        }
                        else if ( prev==2) {
                             sum = sum - ( str[i][0] -'0');
                        }
                        else {
                              sum = sum + ( str[i][0] -'0');
                        }
                    }
                }
            }
        }
        flag = max( flag , sum);
        return ;
    }
    if( s[cnt]== '*' || s[cnt]=='-' || s[cnt]=='+') {
     //   cout<<s[cnt]<<' ';;
     //   cout<<'\n';
        if( s[cnt]=='*') {
            str.push_back("*");
        }
         if( s[cnt]=='-') {
            str.push_back("-");
        }
         if( s[cnt]=='+') {
            str.push_back("+");
        }
        go(cnt+1);
        str.pop_back();
    }
    else {
        
        string temp ="";
        temp+=s[cnt];
        str.push_back(temp);
        go(cnt+1);
        str.pop_back();
       
        if( cnt+3<= num) {
         temp ="";
        for (int i = cnt ; i< cnt+3 ; i++) {
            temp+=s[i];
        }
        str.push_back(temp);
        go(cnt+3);
        str.pop_back();}
    }
    
}
int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    
    cin>>num;
    cin>>s;
    go(0);
    cout<<flag;
   
    return 0;
}