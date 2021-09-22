#include<iostream>
#include<queue>
#include<stack>
#include<cstdio>
#include<algorithm>
#include<cstring>
#include<sstream>
#include<cmath>
using namespace std;

#define MAX 10000
typedef int(*arrPointer)[6];
int **a;

int n,m;
long long int t;

// 한번 곱하는 것
int ** gob(int n, int m , int k, int **a, int **b){
    int **temp= (int**)malloc(sizeof(int*)*6);
    for (int i=  0 ; i< 6 ; i++) {
        temp[i] = (int* )malloc(sizeof(int)*6);
    }

    for (int i = 0 ; i< n ; i++) {
       for (int j=0; j<m ; j++) {
           temp[i][j] = 0;
           for (int t = 0 ; t<k ; t++) {
               temp[i][j] += (a[i][t] * b[t][j]);
               temp[i][j]%=1000;
           }
       }
    }

    return temp;
}
int ** matrixgob(long long int   num) {
    // num 이 1일 때 
    if (num ==1) return a;
    else {
     if(num %2==0) {
        //짝수 일 때 
        // num/2 * num/2 한 값
        int ** temp = matrixgob(num/2);
        return gob(n,n,n,temp,temp);
     }
    else {
        // 홀수 일때 
        // a * num/2
        return gob(n,n,n,a,matrixgob(num-1));
    }}
}


int main(void) {
    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    
    cin >>n >>t;

    a= (int**)malloc(sizeof(int*)*6);
    for (int i=  0 ; i< 6 ; i++) {
        a[i] = (int* )malloc(sizeof(int)*6);
    }

    for (int i = 0 ; i<n ; i++) {
        for (int j = 0 ; j<n ; j++) {
            cin>>a[i][j];
           a[i][j]%=1000;
        }
    
    }
    int ** temp = matrixgob(t);
    
    for (int i = 0 ; i<n ; i++) {
        for (int j = 0 ; j<n ; j++) {
            cout<<temp[i][j]<<' ';;
           
        }cout<<'\n';
    
    }
 
    return 0;
}