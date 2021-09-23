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
vector<int> a;
#define MAX 100000000
// 현재 index 에서 갈 수 있는 것의 최소 
int n,m;
int d[1001];
// d[i] = min( d[(0~ a[i])]) + 1;
int go(int idx ){
    if( idx==n-1) return 1;
    if( idx >= n) return 0;
    if( d[idx] >0) return d[idx];
    d[idx] = 10001;
    for (int i = 1 ; i<= a[idx] ; i++) {
       if( idx+i <n) {
        d[idx] = 1+ min ( d[idx], d[idx+ i]);
        }
    }
    return d[idx];
}

int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);

    cin>>n;
    for(int i=  0 ; i<n  ; i++) {
        int temp;
        cin>>temp;
        a.push_back(temp);
    }
    //d[i] // i번쨰에서 n-1 번쨰까지 가는 최소 회수
    // d[n-1] // n-1 번째에서 n-1 번째 까지 가는 최소 회수
    // d[i] = min(d[i+( 0~ a[i])])  +1;;
    d[n-1] = 0;
    for (int i= n-2; i>=0  ;i--) {
        d[i] =100000000;
        for (int j = 1 ; j<=a[i] ; j++) {
            if( i+j<n) {
                d[i] = min (d[i], d[i+j]+1); 
            }
        }
    } 
    if(d[0]== MAX) {
        cout<<-1;
    }
    else {
        cout<<d[0];
    }
  return 0;
}