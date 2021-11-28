#include<iostream>
#include<string>
using namespace std;
int n;
int num[101];
int main(void)
{
    cin >> n;
    for (int i = 0 ; i< n ; i++)
    {
        cin>>num[i];
    }
    int count = 0;
    int t= 0;
    int sum = 0;
    while (1) {
        if ( t>=n) break;
        if (sum<=0 ) sum = 30;
        if (sum >= (float) num[t]/2) count++;
        sum  = sum - num[t];
        t++;
        
        
    }
    cout<<count;
    return (0);
}