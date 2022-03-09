#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;
string a[101][101];

string calStr(string a, string b)
{
    int alen = a.length();
    int blen = b.length();
    int clen = max(alen, blen) + 1;
    string c = "";
    for (int i = 0; i < clen; i++)
    {
        c += "0";
    }
    int i = alen - 1;
    int j = blen - 1;
    int k = clen - 1;

    while (i >= 0 && j >= 0)
    {
        int num = a[i] - '0' + b[j] - '0';

        int tenth = num / 10;
        int first = num % 10;
        //  cout << tenth << " " << first << "\n";
        c[k] = c[k] - '0' + first + '0';
        if (c[k] - '0' >= 10)
        {
            c[k] = c[k] - 10;
            c[k - 1] += 1;
        }
        c[k - 1] = c[k - 1] - '0' + tenth + '0';
        if (c[k - 1] - '0' >= 10)
        {
            c[k - 1] = c[k - 1] - 10;
            c[k - 2] += 1;
        }
        k--;
        i--;
        j--;
    }

    while (i >= 0)
    {
        int num = a[i] - '0';
        int tenth = num / 10;
        int first = num % 10;
        c[k] = c[k] - '0' + first + '0';
        c[k - 1] = c[k - 1] - '0' + tenth + '0';
        i--;
        k--;
    }

    while (j >= 0)
    {
        int num = b[j] - '0';
        int tenth = num / 10;
        int first = num % 10;
        c[k] = c[k] - '0' + first + '0';
        c[k - 1] = c[k - 1] - '0' + tenth + '0';
        j--;
        k--;
    }

    for (i = 0; i < clen; i++)
    {
        if (c[i] != '0')
            break;
    }
    string d = c.substr(i, clen);
    /*
        cout << d << " "
             << "a : " << a << " "
             << "b : " << b << "\n";
    */
    return d;
}
int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 0; i <= n; i++)
    {
        for (int j = 0; j <= n; j++)
        {
            a[i][j] = "0";
        }
    }
    for (int i = 0; i <= n; i++)
    {
        a[i][0] = "1";
        a[i][i] = "1";
    }
    //  cout << calStr("11628", "3876");

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= i; j++)
        {
            // cout << "i : " << i << "j : " << j << "\n";
            a[i][j] = calStr(a[i - 1][j - 1], a[i - 1][j]);
        }
    }

    cout << a[n][m];

    return 0;
}