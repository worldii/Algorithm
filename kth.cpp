#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(void)
{
    int tc;
    cin >> tc;
    for (int t = 1; t <= tc; t++)
    {
        int num;
        string s;
        vector<string> sa;
        vector<int> lcp;
        cin >> num >> s;
        for (int i = 0; i < s.length(); i++)
        {
            sa.push_back(s.substr(i, s.length()));
        }

        sort(sa.begin(), sa.end());
        lcp.push_back(0);

        for (int i = 1; i < sa.size(); i++)
        {
            string s1 = sa[i - 1];
            string s2 = sa[i];
            int idx = 0;
            while (idx < s1.length() && idx < s2.length())
            {
                if (s1[idx] != s2[idx])
                    break;
                idx++;
            }
            lcp.push_back(idx);
        }

        int sum = 0;
        int flag = 0;
        cout << "#" << t << " ";
        for (int j = 0; j < s.length(); j++)
        {
            sum = sum + sa[j].length() - lcp[j];
            //  cout << sum << " ";
            if (sum >= num)
            {
                cout << sa[j].substr(0, sa[j].length() - (sum - num));
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            cout << "none";
        cout << "\n";
    }
    return 0;
}