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
int a[1001][1001];

int d[1001][1001];
int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    int n,m;
    cin>>n>>m;
    for (int i = 0 ;  i< n ; i++) {
        for (int j = 0 ;  j<m ; j++) {
            cin>>a[i][j];
        }
    }
    int sum =a[0][0];
    d[0][0] = a[0][0];
    for (int i = 1 ; i < m ; i++) {
        sum+=a[0][i];
        d[0][i] = sum;
    }
    // d[i][j] = max(d[i-1][j-1], d[i-1][j], d[i][j-1])+ a[i][j];
   for (int i = 1 ; i< n ; i++) {
       for (int j = 0 ; j< m ; j++) {
           d[i][j] = d[i-1][j] + a[i][j];
           if( j-1>=0) {d[i][j] = max(d[i][j], d[i][j-1] +a[i][j]);
            d[i][j] = max(d[i][j], d[i-1][j-1]+a[i][j]);
           }
       }
   }
   cout<<d[n-1][m-1];
  return 0;
}