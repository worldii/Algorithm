#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef struct _arr
{
    int val;
    int idx;
    int after;
} Arr;
vector<Arr> A;
bool cmp(Arr &a1, Arr &a2)
{
    if (a1.val != a2.val)
    {
        return a1.val < a2.val;
    }
    else
        return a1.idx < a2.idx;
}
int main(void)
{

    int N;
    cin >> N;

    for (int i = 1; i <= N; i++)
    {
        int value;

        cin >> value;
        A.push_back({value, i - 1, 0});
    }

    sort(A.begin(), A.end(), cmp);
    for (int i = 0; i < A.size(); i++)
    {
        A[i].after = i;
    }
    int maxidx = 0;
    for (int i = 0; i < A.size(); i++)
    {
        if (A[i].idx - A[i].after > maxidx)
            maxidx = A[i].idx - A[i].after;
    }
    cout << maxidx + 1;
    return 0;
}