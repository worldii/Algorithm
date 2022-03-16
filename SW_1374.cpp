#include <iostream>
#include <string.h>
#include <algorithm>
#include <stack>
using namespace std;
stack<int> num;

string addstr(string n1, string n2)
{
    int sum = 0;
    while (!n1.empty() || !n2.empty() || sum)
    {
        if (!n1.empty())
        {
            sum += n1.back() - '0';
            n1.pop_back();
        }
        if (!n2.empty())
        {
            sum += n2.back() - '0';
            n2.pop_back();
        }
        num.push(sum % 10);
        sum /= 10;
    }
    string strsum = "";
    while (!num.empty())
    {
        strsum += num.top() + '0';
        num.pop();
    }
    return strsum;
}
string substr(string n1, string n2)
{
    string strsub = "";

    int sum = 0;
    if (n1.size() < n2.size() || (n1.size() == n2.size() && n1[0] < n2[0]))
    {
        string temp = n1;
        n1 = n2;
        n2 = temp;
    }
    while (!n1.empty() || !n2.empty() || sum)
    {
        if (!n1.empty())
        {
            sum += n1.back() - '0';
            n1.pop_back();
        }
        if (!n2.empty())
        {
            sum -= n2.back() - '0';
            n2.pop_back();
        }
        if (sum < 0)
        {

            sum += 10;
            int lastdigit = n1.back();
            n1.pop_back();
            lastdigit--;
            n1.push_back(lastdigit);
        }
        num.push(sum % 10);
        sum /= 10;
    }
    while (!num.empty())
    {
        strsub += num.top() + '0';
        num.pop();
    }
    for (int i = 0; i < strsub.length(); i++)
    {
        if (strsub[i] != '0')
        {
            return strsub.substr(i, strsub.length() - i);
        }
    }

    return "0";
}
int main(void)
{
    string n, m;

    while (1)
    {
        cin >> n >> m;
        if (n == "0" && m == "0")
            return 0;
        cout << addstr(n, m) << "\n";
        cout << substr(n, m) << "\n";
    }
    return 0;
}