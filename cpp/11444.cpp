
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
#include<array>

using namespace std;
#define MAX 1000000007
long long n;
#define arr array<array<long long ,3>,3>
// 피보나치 주기 구했더니 시간 초과. 
// 행렬 곱셈 이용 
arr mat;
arr ans;
// 곱셈 함수
arr gob(int n, int m , int k, arr a, arr b){
    arr temp;
    for (int i = 0 ; i< n ; i++) {
       for (int j=0; j<m ; j++) {
           temp[i][j] = 0;
           for (int t = 0 ; t<k ; t++) {
               temp[i][j] += (a[i][t] * b[t][j]);
               temp[i][j]%=MAX;
           }
       }
    }
    return temp;
}

arr matrixgob(long long   num) {
    // num 이 1일 때 
    if (num ==0) return mat;
    else if (num ==1) return mat;
    else {
     if(num %2==0) {
        //짝수 일 때 
        // num/2 * num/2 한 값
        arr  temp = matrixgob(num/2);
        return gob(2,2,2,temp,temp);
     }
    else {
        // 홀수 일때 
        return gob(2,2,2,mat,matrixgob(num-1));
    }}
}


int main (void) {
    long long n;
    cin>>n;
    mat[0][0] = 1;
    mat[0][1] = 1;
    mat[1][0] = 1;
    mat[1][1] = 0;
    // 처음 행렬 : 단일 행렬
    // 곱해주는 행렬 : 
    //11
    //10
    // n 번 곱함 -> 분할 정복 이용해서
    //  ans[0][1] 을 구함.
    arr ans = matrixgob(n);
   // cout<<ans[0][0]<<ans[0][1]<<ans[1][0]<<ans[1][1];
    cout<<ans[0][1];
    return 0;
    }


