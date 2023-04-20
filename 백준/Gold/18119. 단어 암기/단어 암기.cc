#include <iostream>
#include <vector>
#include <string>
using namespace std;
int n, m;
int arr[51][51];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

vector<int > alph[27];

int main(void)
{
    int n, m;
    cin >> n >> m;

    vector<string> s;
    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        s.push_back(str);
        bool alphbetArr[27] = {
            false,
        };
        for (int j = 0; j < str.length(); j++)
        {
            if (!alphbetArr[str[j] - 'a'])
            {
                // 알파벳 마다 저장해놓음
                alph[str[j] - 'a'].push_back(i);
                alphbetArr[str[j] - 'a'] = true;
            }
        }
    }
    bool check[10001];

    int cntArr[10001] = {
        0,
    };
    for (int i = 0; i < n; i++)
    {
        check[i] = true;
    }

    for (int i = 0; i < m; i++)
    {
        int num;
        char ch;
        cin >> num >> ch;
        if (num == 1)
        {
            // ch 해당 알파벳
            for (int j = 0; j < alph[ch - 'a'].size(); j++)
            {
                cntArr[alph[ch - 'a'][j]]++;
                check[alph[ch - 'a'][j]] = false;
            }
        }
        else
        {
            // ch 해당 알파벳
            for (int j = 0; j < alph[ch - 'a'].size(); j++)
            {
                cntArr[alph[ch - 'a'][j]]--;
                if (cntArr[alph[ch - 'a'][j]] == 0)
                    check[alph[ch - 'a'][j]] = true;
            }
        }
        int cnt = 0;
        for (int j = 0; j < n; j++)
        {
            if (check[j])
                cnt++;
        }
        cout << cnt << "\n";
    }

    return 0;
}