#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int n,m;
int dx[9] = {0,0,-1,-1,-1,0,1,1,1};
int dy[9] = {0,-1,-1,0,1,1,1,0,-1};
int ddx[4] = {-1,1,1,-1};
int ddy[4] = {-1,1,-1,1};
int d[51] ;
int s[51];
int a[51][51];
int check[51][51];
int main(void) {
  int n,m ;
  cin>>n>>m;
  for (int i = 0 ; i< n ; i++) {
    for (int j = 0 ; j< n ; j++)
    {
      cin>> a[i][j];
    }
  }
  check[n-1][0] = 1;
  check[n-1][1] = 1;
  check[n-2][0] = 1;
  check[n-2][1] = 1;
  for (int i = 0  ; i< m ; i++) {
    cin>> d[i]>>s[i];
    vector<pair<int,int>> cor;
    for (int t = 0 ; t< n ; t++) {
      for (int tt = 0 ;tt< n ; tt++) {
        if ( check[t][tt] == 1) {
          check[t][tt] = 0;
          int tet= ((t+s[i]*dx[d[i]] )%n + n) %n  ;
          int tett =((tt+s[i]*dy[d[i]] )%n + n) %n  ;
          a[tet][tett] ++;
          cor.push_back (make_pair(tet,tett));
          }
      } 
    }
   
    for (int t=  0 ;t< cor.size();t++) {
    //  cout<< cor[t].first << cor[t].second<< "\n";
      int num = 0 ;
       for (int j = 0 ; j< 4;  j++) {
         int tet =ddx[j] + cor[t].first  ; ;
         int tett = ddy [j]  + cor[t].second  ;
         if ( tet <0 || tet >=n ) continue;
         if( tett <0 || tett >=n) continue;
         if ( a[tet][tett] > 0 ) num++;
       }
      a[cor[t].first][cor[t].second] += num;
      check[cor[t].first][cor[t].second] =1;

    }

    
  
  
vector<pair<int,int>> cor2;
     for (int t= 0 ; t< n ; t++) {
      for (int tt= 0 ;tt< n ; tt++) {
if ( check[t][tt]==0 ) {
  if ( a[t][tt] >=2) { a[t][tt] -=2;
  cor2.push_back(make_pair(t,tt));}
  
}      }
    }
for (int t= 0 ;t< cor2.size() ;t++) {
  check[cor2[t].first][cor2[t].second] =1;
}
for (int t= 0 ; t< cor.size() ; t++) {
        check[cor[t].first][cor[t].second] =0;

    }
    
  }
  
  int sum = 0; 
  for (int t= 0 ; t< n ; t++) {
      for (int tt= 0 ;tt< n ; tt++) {
        sum +=a[t][tt] ; 
      };
    }
  cout<<sum;
  return 0;
  
}