#include <iostream>
#include <algorithm>
using namespace std;
#include <vector>
vector<int> a;
int main(void)
{
    int n, s;
    cin >> n >> s;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        a.push_back(temp);
    }
    int select = -1;
    int start = 0;
    int end = 0;
    long long sum = a[0];
    if (sum >= s)
    {
        cout << 1;
        return 0;
    }
    while (start < n && end < n)
    {
        if (sum >= s)
        {
            sum -= a[start];
            start++;
            if (sum >= s)
            {
                if (select == -1 || select > end - start + 1)
                {
                    select = end - start + 1;
                }
            }
        }
        else
        {
            end++;
            sum += a[end];
            if (sum >= s)
            {
                if (select == -1 || select > end - start + 1)
                {
                    select = end - start + 1;
                }
            }
        }
    }
    if (select == -1)
        cout << 0;
    else
        cout << select;
    return 0;
}