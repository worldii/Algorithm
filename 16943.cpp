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
vector<int> pro;
vector<int> a;
int flag =0;
void go (int cnt, int sum ) {
 
}

vector<char> s1;
int getnum () {
    int sum =0;
    int size = s1.size();
    int flag =0;
    for (int i = 0 ; i< size ; i++) {
        if( flag ==0 && s1[i] =='0' ) {
            return -1;
        }
        if (flag ==0 && s1[i] !='0') flag =1;
    }

    for (int i = 0 ; i< size ; i++) {
        sum = (sum *10 + (s1[i])-'0');
    }
    return sum;
}

int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    string num1,num2;
  
    cin>>num1>>num2;
    int num22 = stoi (num2);
    if( num1.length() > num2.length() ) {
        cout<<-1;
        return 0;
    }
    
    for (int i = 0 ;  i< num1.length() ; i++) {
        int temp = num1[i];
        s1.push_back(temp);
    }
    sort(s1.begin(), s1.end());
    int flag = -1;
    do {
       
        
        int num = getnum();
        if(num==-1) continue;
        if( num <num22) flag = max(num, flag );
    }while(next_permutation(s1.begin(), s1.end()));

    cout<<flag;
    return 0;
}