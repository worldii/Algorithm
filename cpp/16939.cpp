#include<iostream>
#include<queue>
#include<stack>
#include<cstdio>
#include<algorithm>
#include<cstring>
#include<sstream>
#include<cmath>
#include<set>
using namespace std;
long long  n;
set<long long > s;
vector<long long > s2;
vector<long long > he;
void go(int na, int gob, long long  num) {

    if(he.size() ==n ) {
        // check;
        
        int flag =0;
        for (int i = 0 ; i< he.size() ;i++) {
         if (s.count(he[i])==0) {
             flag =1;
             break;
         }
         if( flag==0) {
             for (int i = 0  ; i< he.size(); i ++) {
                 cout<<he[i]<<' ';
             }
          //   exit(0);
         }
         return ;
        }
    }
    if(he.size() >n) return ;
    if (num%3==0 && s.count(num/3)>0)  {
        he.push_back(num/3);
        go(na+1, gob, num/3);
        he.pop_back();
    }
    if( s.count(num*2)>0) {
        he.push_back(num*2);
        go(na, gob+1, num*2);
        he.pop_back();
    }

    

}
int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    cin>>n;
  for(int i = 0 ; i< n ; i++) {
      long long  temp;
      cin>>temp;
      s.insert(temp);
      s2.push_back(temp);
    }
    for (int i = 0 ; i< s2.size(); i++) {
        he.push_back(s2[i]);
         go(0,0, s2[i]);
         he.clear();

    }
   

    return 0;
}