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
int check[MAX][MAX][11][2];
int dist[MAX][MAX][11][2];

typedef struct _cor{
    int x;
    int y;
    int wall;
    int flag; // 0일때 낮, 1일때 밤
}Cor;

int dx[4] = {-1,1,0,0};
int dy[4] = {0,0,1,-1};

int n,m,k;

void bfs() {

    memset(check, 0, sizeof(check));
    memset(dist, -1, sizeof(dist));

    check[0][0][0][0] = 1;
    dist[0][0][0][0] =0;


    queue<Cor> q;
    q.push(Cor{0,0,0,0});
    while(!q.empty()) {
        int tempx = q.front().x;
        int tempy = q.front().y;
        int wall = q.front().wall;
        int flag = q.front().flag ;
        q.pop();
        
        for (int i = 0 ; i< 4 ; i++) {
            int tmpx = tempx + dx[i];
            int tmpy = tempy + dy[i];
            if ( 0<= tmpx && tmpx < n && 0<=tmpy && tmpy < m) {
               
                    if ( check[tmpx][tmpy][wall][1-flag]==0 && d[tmpx][tmpy]==0) {
                        check[tmpx][tmpy][wall][1-flag] =1;
                        dist[tmpx][tmpy][wall][1-flag]= dist[tempx][tempy][wall][flag] +1;
                        q.push(Cor{tmpx, tmpy, wall, 1-flag });
                    }
                
                if ( flag ==0 ){
                    if( check[tmpx][tmpy][wall+1][1-flag]==0 && d[tmpx][tmpy]==1 && wall<k) {
                        check[tmpx][tmpy][wall+1][1-flag] =1;
                        dist[tmpx][tmpy][wall+1][1-flag] = dist[tempx][tempy][wall][flag] +1;
                        q.push(Cor{tmpx,tmpy , wall+1, 1-flag });
                    }
                }
            
            }
        }
        if(  check[tempx][tempy][wall][1-flag]==0) {
        dist[tempx][tempy][wall][1-flag] = dist[tempx][tempy][wall][flag]+1;
        check[tempx][tempy][wall][1-flag ]=1;
        q.push(Cor{tempx, tempy, wall, 1-flag});
        }
    }

    

}
int main(void) {
    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    cin>>n>>m>>k;
    for (int i = 0 ; i< n ; i++) {
        string temp ;
        cin>>temp;
        for (int j = 0 ; j< m ; j++) {
            d[i][j] = temp[j]-48;
        }
    }
    bfs();
  
    int flag = -2;
    for (int i = 0 ;i< 2; i++) {
    for (int t= 0 ; t <= k ; t++) {
        if (dist[n-1][m-1][t][i]!=-1) {
            flag =0;
        }
    }}
 /*    for (int i = 0 ;i< 2; i++) {
        for (int t= 0 ; t <= k ; t++) {
            cout<<dist[n-1][m-1][t][i];
        }
    }*/
    //  다 -1 일이 아님
    if (flag ==0) {
      flag = n* m*2 ;
        for (int i = 0 ; i< 2; i++) {
            for (int t= 0 ; t <= k ; t++) {
                if (dist[n-1][m-1][t][i]!=-1) {
                    flag = min( dist[n-1][m-1][t][i], flag);
                }
            }
        }
        cout<<flag+1;
    
    }
    else {
        cout<<-1;
    }
    //cout<<'\n';
    
    return 0;
}