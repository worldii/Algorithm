#include <algorithm>
#include <iostream>
using namespace std;
#include <queue>

#include <vector>

priority_queue<int, vector<int>, greater<int>> minq;
priority_queue<int> maxq;

void getMid(int ele)
{
    if (maxq.size() == 0)
    {
        maxq.push(ele);
        cout << ele << "\n";
        return;
    }
    if (minq.size() < maxq.size())
    {
        minq.push(ele);
    }
    else
    {
        maxq.push(ele);
    }
    if (maxq.top() > minq.top())
    {
        int tempmax = maxq.top();
        maxq.pop();
        int tempmin = minq.top();
        minq.pop();
        minq.push(tempmax);
        maxq.push(tempmin);
    }
    cout << maxq.top() << "\n";
}
int main(void)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        getMid(temp);
    }
    return 0;
}