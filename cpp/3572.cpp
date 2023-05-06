#include <iostream>
#include <string.h>
#include <stack>
using namespace std;

int Ispriority(char c)
{
    if (c == '-' || c == '+')
        return 1;
    else if (c == '*' || c == '/')
        return 2;
    return 0;
}
stack<char> s;
int main(void)
{
    string str;
    cin >> str;
    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] == '*' || str[i] == '-' || str[i] == '+' || str[i] == '/')
        {
            if (s.size() == 0 || (Ispriority(str[i]) > Ispriority(s.top())))
            {
                s.push(str[i]);
            }
            else
            {
                while (s.size() != 0 && Ispriority(str[i]) <= Ispriority(s.top()))
                {
                    cout << s.top();
                    s.pop();
                }
                s.push(str[i]);
            }
        }
        else
        {
            cout << str[i];
        }
    }
    while (!s.empty())
    {
        cout << s.top();
        s.pop();
    }

    return 0;
}