#include <iostream>
#include <vector>
using namespace std;
int d[101][100001];
vector<pair<int, int>> bag;
int main(void)
{
    int n, k;
    cin >> n >> k;
    bag.push_back({0, 0});
    for (int i = 0; i < n; i++)
    {
        int w, v;
        cin >> w >> v;
        bag.push_back({w, v});
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= k; j++)
        {
            if (j < bag[i].first)
            {
                d[i][j] = d[i - 1][j];
            }
            else
            {
                d[i][j] = max(d[i - 1][j], d[i - 1][j - bag[i].first] + bag[i].second);
            }
        }
    }
    /*  for (int i = 1; i <= n; i++)
      {
          for (int j = 1; j <= k; j++)
          {
              cout << d[i][j] << " ";
          }
          cout << "\n";
      }*/
    cout << d[n][k];
    return 0;
}