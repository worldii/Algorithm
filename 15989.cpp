#include <iostream>
using namespace std;
int d[10001][4];
int count = 0;
int main(void)
{
    int n;
    cin >> n;
    d[1][1] = 1;
    d[2][1] = 1;
    d[2][2] = 1;
    d[3][1] = 1;
    d[3][2] = 1;
    d[3][3] = 1;
    for (int i = 4; i <= 10000; i++)
    {
        d[i][1] = d[i - 1][1];
        d[i][2] = d[i - 2][2] + d[i - 2][1];
        d[i][3] = d[i - 3][3] + d[i - 3][1] + d[i - 3][2];
    }
    for (int i = 0; i < n; i++)
    {
        int m;
        cin >> m;
        cout << d[m][1] + d[m][2] + d[m][3] << "\n";
    }
    return 0;
}