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

// 라빈 카프 알고리즘 
string p;
string s;
int mod = 127;
int base = 256 ;

int hashfunc(string p) {
    int sum=0;
    for (int i = 0 ; i< p.length(); i++) {
        sum = (sum * base + char(p[i])) % mod;
    }
    return sum;
}
int comparestr(string p, string s) {
   // cout<<p<<' '<<s;
    for (int i = 0 ; i< p.length(); i++) {
        if( p[i] != s[i]) return 0;
    }
    return 1;
}
int rabin () {
    int psize = p.length();
    int ssize = s.length ();
    if( psize> ssize) return 0;
    // p의 해쉬값을 구함
    int phash = hashfunc(p);
   
    // s 의 부분 해쉬 값을 구함. 
    int shash = hashfunc(s.substr(0, psize));
    int first = 1;
    for (int i=  0 ; i< psize-1 ; i++) {
        first =  (first * base)%mod;;
    }

    for (int i= 0 ; i<= ssize -psize ; i++) {
        // p 의 해쉬값과 s의 부분 해쉬 값이 같을 때
        // 문자열을 비교해준다. 
    //    cout<<phash<<' ' <<shash<<'\n';
        if(phash== shash) {
            if(comparestr(p,s.substr(i,i+psize))==1) return 1;
        }
        // s 의 부분 해쉬 값을 업데이트 해준다. 
        shash = shash -(s[i]  * first ) %mod;  
        shash = (shash+ mod ) % mod;
        shash =  (shash *base  + s[i+psize] ) %mod;
    }
    return 0;
}
int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
 
    cin>>s;
    cin>>p;
    cout<<rabin();
    return 0;
}