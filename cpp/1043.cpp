#include<cstdio>
#include<iostream>
#include<vector>
#include<queue>
#include<cstring>
using namespace std;
vector<int> people[51];
vector<int> room[51];
int jinsilchck[51];
int check[51];
bool bfs(int idx) {
    memset(check, 0, sizeof(check));
    check[idx] = 1;
    queue<int> q;
    q.push(idx);
    if( jinsilchck[idx] ==1) return true;
    while(!q.empty()) {
        int temp = q.front();
        q.pop();
        for ( int next : people[temp]) {
            if( check[next] == 0 ){
                check[next] =1;
                if(jinsilchck[next]==1) return true;
                q.push(next);
            }
        }
    }
    return false;
}
int main(void) {
    int n,m;
    cin>>n>>m;
    int jinsil;
    cin>>jinsil;
    if(jinsil!=0) {
        for(int i = 0 ;  i< jinsil; i++) {
            int k=0;
            cin>>k;
            jinsilchck[k] =1;
        }
    }
    
    for (int i = 0 ; i< m  ; i++) {
        int k;
        cin>>k;
        int num ;
        for(int j= 0 ; j< k ; j++) {
            cin>>num;
            room[i].push_back(num);
        }
        for(int j = 0 ; j< k ; j++) {
            for(int t= j+1 ; t< k ; t++) {
             people[room[i][j]].push_back(room[i][t]);
             people[room[i][t]].push_back(room[i][j]);              
          }
        }
    }
    if(jinsil==0) {
        cout<<m;
        return 0;
    }
  /*  for(int i = 0 ; i< m ; i++) {
        auto he = room[i];
        for(int j = 0 ; j< he.size(); j++) {
            cout<<he[j]<< ' ';
        }cout<<'\n';
    }*/
    int roomnum=0;
    for(int i = 0 ; i< m ; i++ ) {
        auto he = room[i];
         for(int j = 0 ; j< he.size(); j++) {
            
            if(bfs(he[j])==true) {
                roomnum++;
                break;
            }
        }
    }
    cout<<m-roomnum;
    return 0;
}