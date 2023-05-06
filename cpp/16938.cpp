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
int n,m,l,r,x;
vector<int> pro;
vector<int> a;
int flag =0;
void go (int cnt, int sum ) {
    if( cnt == n ){
    if( l<= sum && sum <=r && a.size()>=2  ) {
       int maxx = -987654321;
       int minn = 987654321;
       for (int i = 0 ; i< a.size() ; i++) {
           maxx = max(maxx, a[i]);
           minn = min(minn, a[i]);
       }
       int diff = maxx -minn;
       if (diff>=x) flag++;
     
    }  return ;
    }
    // 선택하는 경우
    if( sum + pro[cnt]<=r ) {
    a.push_back(pro[cnt]);
    go(cnt+1, sum+ pro[cnt]);
    a.pop_back();
    }
    // 안선택하는 경우
    go(cnt+1, sum);
}
int main(void) {

    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
  
    cin>>n>>l>>r>>x;
    for (int i = 0  ; i< n ; i++) {
        int temp;
        cin>>temp;
        pro.push_back(temp);
    }
    go(0,0);
    cout<<flag<<'\n';
    return 0;
}