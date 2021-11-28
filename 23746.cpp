#include <iostream>
using namespace std;
string temp[100];
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        char  back;
        string t;
        cin >> t >> back;
        temp[back - 'A'] = t;
    }
    string ans;
    cin >> ans;
    int front, back;
    cin >> front >> back;
    int count = 0;
    for (int i = 0; i < ans.length(); i++)
    {
        string ttt = temp[ans[i] - 'A'];
        for (int i = 0 ; i < ttt.length() ; i++) {
            count++;
            if (count >= front && count <= back)
            {
                cout<<ttt[i];
            }
        }
        
    }

    return (0);
}