#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;

vector<int> s;

bool checkFunc(int x, int y, int r, int rx, int ry)
{
    // 원중심과 점과의 관계
    double s = (x - rx) * (x - rx) + (y - ry) * (y - ry);
    if (s < r * r)
    {
        return true;
    }
    else
    {
        return false;
    }
}
int main(void)
{
    ios::sync_with_stdio(false);

    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    while (t--)
    {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        int n;
        cin >> n;
        int cnt = 0;
        for (int i = 0; i < n; i++)
        {
           

            int rx, ry, r;
            cin >> rx >> ry >> r;
            if (checkFunc(x1, y1, r, rx, ry) != checkFunc(x2, y2, r, rx, ry))
                cnt++;
        }
        cout << cnt << "\n";
    }
    return 0;
}