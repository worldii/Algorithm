#include <iostream>
#include <string>
#include <algorithm>
using namespace std;
int d[1001][1001];

int main(void)
{
    int a;
    string astr, bstr;
    int b;
    cin >> a >> astr;
    cin >> b >> bstr;
    int num = 0;
    for (int i = 0; i <= a; i++)
    {
        d[i][0] = num++;
    }
    num = 0;
    for (int i = 0; i <= b; i++)
    {
        d[0][i] = num++;
    }
    for (int i = 1; i <= a; i++)
    {
        for (int j = 1; j <= b; j++)
        {
            if (astr[i - 1] == bstr[j - 1])
                d[i][j] = d[i - 1][j - 1];
            else
            {
                d[i][j] = min(d[i - 1][j], min(d[i][j - 1], d[i - 1][j - 1])) + 1;
            }
        }
    }

    cout << d[a][b];

    return 0;
}