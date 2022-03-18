#include <iostream>
#include <string.h>
#include <stack>
using namespace std;

char openblank[4] = {'(', '<', '{', '['};
char closeblank[4] = {')', '>', '}', ']'};

int main(void)
{
    int n;
    string str;
    for (int t = 1; t <= 10; t++)
    {
        cin >> n >> str;
        stack<char> s;

        for (int i = 0; i < n; i++)
        {

            if (str[i] == '(' || str[i] == '<' || str[i] == '{' || str[i] == '[')
            {
                s.push(str[i]);
            }
            else
            {
                int flag = 0;
                for (int j = 0; j < 4; j++)
                {
                    if (str[i] == closeblank[j] && openblank[j] == s.top())
                    {

                        s.pop();
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)
                {
                    s.push(str[i]);
                }
            }
        }
        /*  while (!s.empty())
          {
              cout << s.top();
              s.pop();
          }

          cout << "\n";
  */
        cout << "#" << t << " ";
        if (s.size() == 0)
            cout << 1;
        else
            cout << 0;
        cout << "\n";
    }

    return 0;
}