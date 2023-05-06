#include <iostream>
#include <vector>
using namespace std;

vector<int> a;

int binary_search(int start, int end, int num)
{
    while (start <= end)
    {
        int mid = (end + start) / 2;
        if (a[mid] == num)
            return mid;
        if (a[mid] < num)
        {
            start = mid + 1;
        }
        else
        {
            end = mid - 1;
        }
    }
    return -1;
}
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;
        a.push_back(num);
    }
    int tc;
    cin >> tc;
    for (int i = 0; i < tc; i++)
    {
        int num;
        cin >> num;
        cout << binary_search(0, n - 1, num) + 1 << "\n";
    }
    return 0;
}