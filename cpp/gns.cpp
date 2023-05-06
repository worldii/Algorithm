
#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdio>
using namespace std;
int strToNum(string num)
{
    if (num == "ZRO")
        return 0;
    else if (num == "ONE")
        return 1;
    else if (num == "TWO")
        return 2;
    else if (num == "THR")
        return 3;
    else if (num == "FOR")
        return 4;
    else if (num == "FIV")
        return 5;
    else if (num == "SIX")
        return 6;
    else if (num == "SVN")
        return 7;
    else if (num == "EGT")
        return 8;
    else if (num == "NIN")
        return 9;
}
string numToStr(int num)
{
    if (num == 0)
        return "ZRO";
    else if (num == 1)
        return "ONE";
    else if (num == 2)
        return "TWO";
    else if (num == 3)
        return "THR";
    else if (num == 4)
        return "FOR";
    else if (num == 5)
        return "FIV";
    else if (num == 6)
        return "SIX";
    else if (num == 7)
        return "SVN";
    else if (num == 8)
        return "EGT";
    else if (num == 9)
        return "NIN";
}
int main(void)
{
    freopen("input.txt", "r", stdin);

    int testnum;
    cin >> testnum;
    for (int testcase = 1; testcase <= testnum; testcase++)
    {
        string tc;
        vector<int> a;

        int len;
        cin >> tc >> len;
        cout << tc << "\n";
        for (int i = 0; i < len; i++)
        {
            string temp;
            cin >> temp;
            int num = strToNum(temp);
            a.push_back(num);
        }
        sort(a.begin(), a.end());
        for (int i = 0; i < a.size(); i++)
        {
            string temp = numToStr(a[i]);
            cout << temp << " ";
        }
        cout << "\n";
    }
    return 0;
}