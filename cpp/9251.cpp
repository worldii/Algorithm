#include <iostream>
#include <string.h>
#include <algorithm>
using namespace std;
int d[1001][1001];
int main(void)
{
    string a;
    string b;
    cin >> a >> b;
    for (int i = 0; i <= a.length(); i++)
    {
        for (int j = 0; j <= b.length(); j++)
        {
            d[i][j] = 0;
        }
    }
    for (int i = 0; i < a.length(); i++)
    {
        for (int j = 0; j < b.length(); j++)
        {

            //  cout << a[i] << " " << b[j] << "\n";
            if (a[i] == b[j])
            {
                d[i + 1][j + 1] = d[i][j] + 1;
            }
            else
            {
                d[i + 1][j + 1] = max(d[i][j + 1], d[i + 1][j]);
            }
        }
    }
    int max1 = d[1][1];
    for (int i = 0; i <= a.length(); i++)
    {
        for (int j = 0; j <= b.length(); j++)
        {
            if (max1 < d[i][j])
                max1 = d[i][j];
            //  cout << d[i][j] << " ";
        }
        //        cout << "\n";
    }
    cout << max1;
    return 0;
}