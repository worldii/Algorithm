#include <iostream>
#include <cstdio>
using namespace std;
int a[1001];
int IsSky(int i, int n)
{

    int num = a[i];
    int left = num;
    int right = num;
    if (i - 1 >= 0)
        left = num - a[i - 1];
    if (i - 2 >= 0)
        left = min(left, num - a[i - 2]);
    left = max(0, left);
    if (i + 1 < n)
        right = num - a[i + 1];
    if (i + 2 < n)
        right = min(right, num - a[i + 2]);
    right = max(0, right);
    return min(right, left);
}
int main(int argc, char **argv)
{
    freopen("input.txt", "r", stdin);
    int tc = 10;

    while (tc)
    {
        int n;
        cin >> n;
        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }
        int sum = 0;
        for (int i = 0; i < n; i++)
        {
            sum += IsSky(i, n);
        }
        cout << "#" << 11 - tc << " " << sum << "\n";
        tc--;
    }
    return 0;
}