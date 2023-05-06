#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

int sum = 0;
int visited[14];
bool check(vector<int> Col, int k, int Choosenum)
{

    for (int i = 0; i < k; i++)
    {
        if (abs(k - i) == abs(Col[i] - Choosenum))
        {
            return false;
        }
    }
    return true;
}
void backtrack(vector<int> Col, int k, int n)
{
    if (k == n)
    {
        sum++;
        return;
    }
    else
    {
        for (int i = 0; i < n; i++)
        {
            if (visited[i] == 0 && check(Col, k, i))
            {
                visited[i] = 1;
                Col[k] = i;
                backtrack(Col, k + 1, n);
                visited[i] = 0;
            }
        }
    }
}
int main(void)
{
    int n;
    cin >> n;
    vector<int> Col(n);

    backtrack(Col, 0, n);
    cout << sum;
    return 0;
}