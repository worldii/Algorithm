#include <iostream>
using namespace std;
#include <vector>
#include <algorithm>
vector<long long> a;
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        a.push_back(temp);
    }
    sort(a.begin(), a.end());
    long long sum;
    int left;
    int right;
    int mid;
    long long maxsum = 9876543210;
    for (int i = 1; i < n - 1; i++)
    {
        int start = 0;
        int end = n - 1;
        while (start < end && start < i && i < end)
        {
            sum = a[i];
            sum += a[start];
            sum += a[end];
            if (abs(sum) < abs(maxsum))
            {
                left = start;
                right = end;
                mid = i;
                maxsum = sum;
            }
            if (sum <= 0)
            {
                start++;
                if (start == i)
                    start++;
            }
            else
            {
                end--;
                if (end == i)
                    end--;
            }
        }
    }
    cout << a[left] << " " << a[mid] << " " << a[right];
    return 0;
}