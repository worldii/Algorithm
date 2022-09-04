#include<iostream>
#include <queue>
#include <algorithm>
using namespace std;
char  arr[101][101]; 
int R, C;
int dx[4] = {-1,1,0,0};
int dy[4] = {0,0,1,-1};    
int check[101][101] = {0,};
#include<vector>
#include<memory.h>
void bfs (int x, int y ) 
{

    check[x][y] = 1;
    queue <pair<int, int>> q;
    q.push ({x,y});
    while (!q.empty())
    {
        int tx = q.front().first;
        int ty = q.front().second;
        q.pop();
        for (int i  = 0 ; i< 4;  i++) {
            int nowx = tx + dx[i];
            int nowy = ty + dy[i];
            if ( 0> nowy || nowy >= C) continue;
            if ( 0> nowx || nowx >= R) continue;
            if (check[nowx][nowy] == 0  &&arr[nowx][nowy] == 'x')
            {
                check[nowx][nowy ] = 1;
                q.push({nowx, nowy});
            }
        }
    }
  
    
}
int getidx (int x, int y) {
    int flag = 0;
    int i;
    for ( i = x+1 ; i<= R-1 ; i++) {
        if (arr[i][y] == '.') continue;
        else {
            flag = 1;
            break;
        }
    }
    if (flag)  return i-1;
    else return R-1;
}

bool compare (pair<int ,int > x, pair <int , int >y ) {
    return x.first  > y.first;
}

void check_air() 
{           
    for (int i = 0 ;  i< 101 ; i++) {
        for (int j = 0 ; j< 101 ; j++) {
            check[i][j] = 0;
        }
    }
    
    for (int i =  0 ; i < C ; i++)
    {
        if ( check[R-1][i] ==0 && arr[R-1][i] == 'x')
        {
            bfs(R-1, i);
        }
    }
    vector<pair<int, int>> v;
    vector<pair<int,int>> m;
    for (int i = 0 ; i<  R ; i++ ) {
        for (int j  = 0 ; j< C; j++) {

           if (arr[i][j] == 'x' && check[i][j] == 0) {
               v.push_back ({
                   i,j
               });
               m.push_back({i+1, j});
           }
        }
    }

    sort(v.begin(), v.end(), compare);
  /*  for (int i = 0 ; i< v.size() ; i++)
    {
        cout<< v[i].first << v[i].second << "\n";
    }*/
    if (v.size()==0) return ; 

        
    while (1)
    {
        bool flag = 1;
        for (int i = 0 ; i< v.size() ; i++)
        {
      arr[v[i].first][v[i].second] = '.';
        }
        for (int i  = 0 ; i< m.size() ; i++) 
        {
            if (m[i].first == R ) {flag = 0; break;}
            if (arr[m[i].first][m[i].second] == 'x') {flag = 0 ; break;}
        }
        if ( !flag)
        {
            for (int i  = 0 ; i< v.size(); i++) {
                arr[v[i].first][v[i].second] = 'x';
                
            }
            break;
        }
        else {
             for (int i  = 0 ; i< m.size() ; i++) 
        {
            arr[m[i].first][m[i].second]  = 'x';
        }
        }
        v.clear();
        for (int i = 0 ; i<  m.size() ; i++) 
        {
        v.push_back ({m[i].first, m[i].second});
        }
        m.clear();
        for (int i = 0 ; i< v.size() ; i++) 
        {

        m.push_back ({v[i].first+1, v[i].second});
      

        }
    

  
    }
     
}

int main(void)
{
    cin>> R>> C;
    for (int i = 0 ; i< R ; i++) {
        for (int j = 0 ; j< C ; j++) {
            cin>> arr[i][j];
        }
    }  

    int cnt ;
    cin>> cnt;
    for (int i = 0 ; i< cnt ; i++)
    {
        int hei;
        cin>> hei;
        if ( i%2==0)
        {
            //짝수 왼 -> 오 
            for (int j  = 0 ; j< C; j++ )
            {
                if (arr[R-hei][j] == 'x') {
                    arr[R-hei][j] = '.';
                    break;
                }
            }
            check_air();
        }
        else
        {
            // 홀수 오 -> 왼 
            for (int j = C-1 ; j>= 0  ; j--)
            {
                if ( arr[R-hei][j] == 'x') 
                {
                    arr[R-hei][j] = '.' ;
                     break; 
                }
            }
            check_air();
        }
    }
    for (int i = 0 ; i< R ; i++)
    {
        for (int j = 0  ; j< C; j++) 
        {
            cout<< arr[i][j];
        }
        cout<<"\n";
    }
    return 0; 
}