#include <iostream>
using namespace std;
#include <algorithm>
#include <cmath>
double x[10001];
double y[10001];
double area[10001];

int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> x[i] >> y[i];
    }
    for (int i = 0; i < n - 2; i++)
    {
        area[i] = ((x[0] - x[i + 1]) * (y[0] - y[i + 2]) - (x[0] - x[i + 2]) * (y[0] - y[i + 1])) / 2.0;
    }
    double sum = 0.0;

    for (int i = 0; i < n - 2; i++)
    {
        sum += area[i];
    }
    cout.precision(1);
    cout << fixed;
    cout << abs(sum);
    return 0;
}
