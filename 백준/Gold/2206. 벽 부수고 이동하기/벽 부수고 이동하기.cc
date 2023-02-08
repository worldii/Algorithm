#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include<cmath>
#include <sstream>
#include<string>
#include <cstring>
using namespace std;
#define MAX 1003
int d[MAX][MAX];
int d2[MAX][MAX];
int check[MAX][MAX][2];
int dist[MAX][MAX][2];
typedef struct _cor{
    int x;
    int y;
    int wall;
}Cor;
int dx[4] = {-1,1,0,0};
int dy[4] = {0,0,1,-1};
int n,m;

void bfs() {

    memset(check, 0, sizeof(check));
    memset(dist, -1, sizeof(dist));

    check[0][0][0] = 1;
    dist[0][0][0] =0;
    dist[0][0][1]= 0;
    queue<Cor> q;
    q.push(Cor{0,0,0});
    while(!q.empty()) {
        int tempx = q.front().x;
        int tempy = q.front().y;
        int wall = q.front().wall;
        q.pop();
        for (int i = 0 ; i< 4 ; i++) {
            int tmpx = tempx + dx[i];
            int tmpy = tempy + dy[i];
            if ( 0<= tmpx && tmpx < n && 0<=tmpy && tmpy < m) {
                if ( check[tmpx][tmpy][wall]==0 && d[tmpx][tmpy]==0) {
                    check[tmpx][tmpy][wall] =1;
                    dist[tmpx][tmpy][wall]= dist[tempx][tempy][wall] +1;
                    q.push(Cor{tmpx, tmpy, wall});
                }
                if( check[tmpx][tmpy][wall+1]==0 && d[tmpx][tmpy]==1 && wall==0) {
                    check[tmpx][tmpy][wall+1] =1;
                    dist[tmpx][tmpy][wall+1] = dist[tempx][tempy][wall] +1;
                    q.push(Cor{tmpx,tmpy , 1});
                }
              
            }
        }
    }

    

}
int main(void) {
    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    cin>>n>>m;
    for (int i = 0 ; i< n ; i++) {
        string temp ;
        cin>>temp;
        for (int j = 0 ; j< m ; j++) {
            d[i][j] = temp[j]-48;
        }
    }
     
    
      bfs();

    /*for (int i = 0 ; i< n ; i++) {
        for (int  j = 0 ; j <m ; j++) {
            cout<<dist[i][j][0];
        }cout<<"\n";
    }
     for (int i = 0 ; i< n ; i++) {
        for (int  j = 0 ; j <m ; j++) {
            cout<<dist[i][j][1];
        }cout<<"\n";
    }*/
    if( dist[n-1][m-1][0] != -1 && dist[n-1][m-1][1] != -1) {
        cout << min(dist[n-1][m-1][0], dist[n-1][m-1][1])+1 ;
    }
    else if ( dist[n-1][m-1][1]!= -1) {
        cout<<dist[n-1][m-1][1]+1;
    }
    else if ( dist[n-1][m-1][0] !=-1) {
        cout<<dist[n-1][m-1][0]+1;
    }
    else cout<< -1;
    return 0;
    }