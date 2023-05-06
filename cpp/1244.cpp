#include <algorithm>
#include <iostream>
#include <string>
#include <string.h>
using namespace std;
int visited[999999][10];
int maxnum;
int real_swap_size;

string strswap(int i, int j, string str)
{
    char newnum = str[i];
    str[i] = str[j];
    str[j] = newnum;
    return str;
}
void dfs(string str, int swap_size)
{
    if (swap_size == real_swap_size)
    {
        if (stoi(str) > maxnum)
            maxnum = stoi(str);
        return;
    }
    for (int i = 0; i < str.length(); i++)
    {
        for (int j = i + 1; j < str.length(); j++)
        {

            string newString = strswap(i, j, str);
            int newNum = stoi(newString);
            if (!visited[newNum][swap_size + 1])
            {
                visited[newNum][swap_size + 1] = 1;
                dfs(newString, swap_size + 1);
            }
        }
    }
}
int main(void)
{
    int test_case;
    cin >> test_case;

    for (int i = 1; i <= test_case; i++)
    {
        string str;
        memset(visited, 0, sizeof(visited));
        maxnum = -1;
        cin >> str;
        cin >> real_swap_size;
        visited[stoi(str)][0] = 1;
        dfs(str, 0);
        cout << "#" << i << " " << maxnum << "\n";
    }
}