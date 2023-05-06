#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;
int n;
int locate[11];
int weight[11];
double findloc(int i, double x)
{
    return ((double)weight[i] / (double)((locate[i] - x) * (locate[i] - x)));
}

void binary_search_loc(int idx1, int idx2)

{
    double l = locate[idx1];
    double r = locate[idx2];
    double mid;
    int cnt = 0;

    for (int t = 0; t < 100; t++)
    {
        mid = (l + r) / 2;
        double sum = 0;

        for (int i = 0; i <= idx1; i++)
        {

            sum += findloc(i, mid);
        }
        for (int i = idx2; i < n; i++)
        {
            sum -= findloc(i, mid);
        }
        // cout << sum<< " " << l<< " " << r<< " " << mid <<"\n";
        if (sum > 0)
        {

            l = mid;
        }
        else if (sum < 0)
        {
            r = mid;
        }
        t++;
    }
    cout << mid;
}

int main(void)
{
    cout.precision(10);
    cout << fixed;
    int tc;
    cin >> tc;
    for (int t = 1; t <= tc; t++)
    {
        cin >> n;
        for (int i = 0; i < n; i++)
        {
            cin >> locate[i];
        }
        for (int i = 0; i < n; i++)
        {
            cin >> weight[i];
        }
        cout << "#" << tc << " ";
        for (int i = 0; i < n - 1; i++)
        {
            binary_search_loc(i, i + 1);
            cout << " ";
        }
        cout << "\n";
    }

    return 0;
}