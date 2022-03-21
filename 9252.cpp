#include <iostream>
#include <string.h>
#include <stack>
#include <algorithm>
using namespace std;
int a[1001][1001];
int before[1001][1001];

int main(void)
{
    string str1, str2;
    cin >> str1 >> str2;
    for (int i = 1; i <= str1.length(); i++)
    {
        for (int j = 1; j <= str2.length(); j++)
        {
            if (str1[i - 1] == str2[j - 1])
            {

                a[i][j] = a[i - 1][j - 1] + 1;
            }
            else
            {
                a[i][j] = a[i - 1][j];
                before[i][j] = 1;
                if (a[i][j] < a[i][j - 1])
                {
                    a[i][j] = a[i][j - 1];
                    before[i][j] = 2;
                }
            }
        }
    }
    /*for (int i = 1; i <= str1.length(); i++)
    {
        for (int j = 1; j <= str2.length(); j++)
        {
            cout << before[i][j];
        }
        cout << "\n";
    }
    for (int i = 1; i <= str1.length(); i++)
    {
        for (int j = 1; j <= str2.length(); j++)
        {
            cout << a[i][j];
        }
        cout << "\n";
    }*/
    int maxx = 0;
    int maxy = 0;

    for (int i = 1; i <= str1.length(); i++)
    {
        for (int j = 1; j <= str2.length(); j++)
        {
            if (a[maxx][maxy] <= a[i][j])
            {
                maxx = i;
                maxy = j;
                ;
            }
        }
    }
    // cout << maxx << maxy << "\n";
    cout << a[maxx][maxy] << "\n";
    stack<char> s;
    if (a[maxx][maxy] == 0)
        return 0;
    while (a[maxx][maxy] != 0)
    {

        if (str1[maxx - 1] == str2[maxy - 1])
        {
            s.push(str1[maxx - 1]);
            // cout << str1[maxx - 1];
            // cout     << maxx << maxy;
            maxx--;
            maxy--;
            continue;
        }
        if (before[maxx][maxy] == 1)
        {
            maxx--;
        }
        else if (before[maxx][maxy] == 2)
        {
            maxy--;
        }
    }
    while (!s.empty())
    {
        cout << s.top();
        s.pop();
    }
    return 0;
}