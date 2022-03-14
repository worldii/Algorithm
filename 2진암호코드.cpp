#include <iostream>
using namespace std;
char a[50][100];
// int b[50][100];
int code56bit[56];
int num[8];
int code[10][7] = {
    {0, 0, 0, 1, 1, 0, 1},
    {0, 0, 1, 1, 0, 0, 1},
    {0, 0, 1, 0, 0, 1, 1},
    {0, 1, 1, 1, 1, 0, 1},
    {0, 1, 0, 0, 0, 1, 1},
    {0, 1, 1, 0, 0, 0, 1},
    {0, 1, 0, 1, 1, 1, 1},
    {0, 1, 1, 1, 0, 1, 1},
    {0, 1, 1, 0, 1, 1, 1},
    {0, 0, 0, 1, 0, 1, 1}};

int main(void)

{
    int tc;
    int testcase;
    cin >> tc;
    for (testcase = 1; testcase <= tc; testcase++)
    {
        int n, m;
        cin >> n >> m;
        int codeflag = 0;
        int codeidx = 0;
        int row = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                cin >> a[i][j];
            }
            for (int j = m - 1; j >= 0; j--)
            {
                if (a[i][j] == '1')
                {
                    // 56번째
                    // break;
                    codeflag = 1;
                    row = i;
                    for (int k = j - 55; k <= j; k++)
                    {
                        code56bit[codeidx++] = a[i][k] - '0';
                    }
                    break;
                }
            }
            if (codeflag == 1)
                break;
        }
        for (int i = row + 1; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                cin >> a[i][j];
            }
        }
        int numidx = 0;

        for (int i = 0; i < codeidx; i += 7)
        {
            for (int k = 0; k < 10; k++)
            {
                int flag = true;

                for (int j = i; j < i + 7; j++)
                {
                    if (code56bit[j] != code[k][j - i])
                        flag = false;
                }
                if (flag == true)
                {
                    num[numidx++] = k;
                }
                // cout << "\n";
            }
            //   cout << codeidx;
        }
        int sum = 0;
        for (int i = 0; i < numidx; i++)
        {
            // cout << num[i] << " ";
            sum += num[i];
        }
        int decodenum = 0;
        int odd = 0;
        int even = 0;
        for (int i = 0; i < numidx - 1; i++)
        {
            if (i % 2 == 0)
            {
                even += num[i];
            }
            else
            {
                odd += num[i];
            }
        }
        decodenum = even * 3 + odd + num[numidx - 1];
        cout << "#" << testcase << " ";
        if (decodenum % 10 == 0)
            cout << sum;
        else
            cout << 0;
        cout << "\n";
    }
    return 0;
}