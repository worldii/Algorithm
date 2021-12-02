#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<int> s[32001];
int check[32001];
void go(int src)
{
    check[src] = 1;
    for (int y : s[src])
    {
        if (check[y] == 0)
        {
            check[y] = 1;
            go(y);
        }
    }
    cout << src << " ";
}
int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < m; i++)
    {
        int src, dest;
        cin >> src >> dest;
        s[dest].push_back(src);
    }
    for (int i = 1; i <= n; i++)
    {
        if (check[i] == 0)
        {
            go(i);
        }
    }
    return 0;
}