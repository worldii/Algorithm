#include <iostream>
#include <stack>
#include <string.h>
using namespace std;
stack<char> s;

int main(void)
{
    string str;
    int len;
    for (int t = 1; t <= 10; t++)
    {
        cin >> len >> str;
        for (int i = 0; i < len; i++)
        {
            if (!s.empty())
            {
                if (s.top() == str[i])
                {
                    s.pop();
                }
                else
                    s.push(str[i]);
            }
            else
                s.push(str[i]);
        }
        long long sum = 0;
        int digit = 1;
        while (!s.empty())
        {
            sum = digit * (s.top() - '0') + sum;
            digit *= 10;

            s.pop();
        }
        cout << "#" << t << " " << sum << "\n";
        }
    return 0;
}