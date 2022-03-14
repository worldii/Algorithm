#include <iostream>
#include <string.h>
using namespace std;
char a[8][8];
int isPand1(int row, int col, int n)
{
    int tempsum = 0;
    // 가로 판단.
    int start = col;
    int end = col + n - 1;
    if (start >= 0 && end < 8)
    {
        while (start <= end)
        {
            if (a[row][start] != a[row][end])
                break;
            start++;
            end--;
        }
        if (start > end)
            tempsum++;
    }
    return tempsum;
}
int isPand2(int row, int col, int n)
{ // 세로 판단
    int start = row;
    int end = row + n - 1;
    int tempsum = 0;

    if (start >= 0 && end < 8)
    {
        while (start <= end)
        {
            if (a[start][col] != a[end][col])
                break;
            start++;
            end--;
        }
        if (start > end)
            tempsum++;
    }
    return tempsum;
}
int main(void)

{
    freopen("input.txt", "r", stdin);
    int test_case;
    for (test_case = 1; test_case <= 10; ++test_case)
    {
        int n;
        cin >> n;
        for (int i = 0; i < 8; i++)
        {

            // cout << str;
            for (int j = 0; j < 8; j++)
            {
                //  a[i][j] = str[j] - '0';
                cin >> a[i][j];
            }
        }

        int sum = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                // 가로판단.
                sum += isPand1(i, j, n);
                //세로
                sum += isPand2(i, j, n);
            }
        }
        cout << "#" << test_case << " " << sum << "\n";
    }
    return 0;
}