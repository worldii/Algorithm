#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;
long long a[1000001];
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
    sort(a, a + n);
    /*for (int i = 0; i < n; i++)
    {
        cout << a[i];
    }*/
    int start = 0;
    int end = n - 1;
    long long maxsum = 9876543210;
    int select1 = 0;
    int select2 = n - 1;
    while (start < end)
    {
        long long sum = a[start] + a[end];
        if (abs(sum) < abs(maxsum))
        {
            select1 = start;
            select2 = end;
            maxsum = sum;
        }
        if (sum < 0)
            start++;
        else
            end--;
    }
    cout << a[select1] << " " << a[select2];
    return 0;
}