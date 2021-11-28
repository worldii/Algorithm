#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<int> t;

int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        t.push_back(temp);
    }
    sort(t.begin(), t.end());
    /* for (int i = 0; i < t.size(); i++)
    {
        cout << t[i] << " ";
    }*/
    long long sum = 0;
    int realsize = -1;
    int idx = t.size();
    long long umm;
    for (int i = 0; i < t.size(); i++)
    {
        if (t[i] < 0)
        {
            sum += t[i];
        }
        else
        {
            realsize = t.size() - 1 - i + 1;
            idx = i;
            break;
        }
    }
    umm = sum;
    if (realsize != -1)
    {
        long long tempt = 0;
        for (int i = idx; i < t.size(); i++)
        {
            tempt += t[i];
        }
        sum = umm + tempt * realsize;
        //  cout << sum << "\n";
        while (1)
        {
            if (realsize == t.size() || idx == 0)
                break;
            realsize++;
            idx--;
            umm -= t[idx];
            tempt += t[idx];
            if (umm + realsize * tempt > sum)
                sum = umm + realsize * tempt;
        }
    }
    cout << sum;
    return (0);
}