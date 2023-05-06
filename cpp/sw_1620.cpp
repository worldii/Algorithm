#include <iostream>
#include <vector>
#include <string>
using namespace std;
vector<string> num;
int main(void)
{
    string s;
    int p, m;

    cin >> s >> p >> m;

    int start = 0;
    for (int i = 0; i <= s.length(); i++)
    {
        if (s[i] == '-' || s[i] == '\0')

        {
            int len = i - start;
            if (len > 4)
            {
                cout << "INPUT ERROR!";
                return 0;
            }
            num.push_back(s.substr(start, i - start));
            start = i + 1;
        }
    }

    string target = num[m - 1];

    while (target.length() != 4)
    {
        target.insert(0, "0");
    }
    // cout << target;
    int sum = 0;
    for (int i = 0; i < 4; i++)
    {
        sum = sum * 10 + target[i] - '0';
    }

    // cout << sum;

    int digit = 1000;
    for (int i = 0; i < 4; i++)
    {
        int tempsum = (sum % (10 * digit)) / digit;
        tempsum += p;
        digit /= 10;
        if (tempsum > 9)
            tempsum %= 10;
        cout << tempsum;
    }
    return 0;
}