#include <iostream>
using namespace std;

int a[19][19];
int isWin(int row, int col, int color)
{
    int isWinFlag = 0;
    if (0 <= col && col + 4 < 19)
    {
        // 가로
        isWinFlag = 1;
        for (int i = col; i < col + 5; i++)
        {
            if (color != a[row][i])
                isWinFlag = 0;
        }
        if (col + 5 < 19 && color == a[row][col + 5])
            isWinFlag = 0;
        if (col - 1 >= 0 && color == a[row][col - 1])
            isWinFlag = 0;
        if (isWinFlag == 1)
            return 1;
    }
    if (0 <= row && row + 4 < 19)
    {
        isWinFlag = 1;
        for (int j = row; j < row + 5; j++)
        {
            if (color != a[j][col])
                isWinFlag = 0;
        }
        if (row + 5 < 19 && color == a[row + 5][col])
            isWinFlag = 0;
        if (row - 1 >= 0 && color == a[row - 1][col])
            isWinFlag = 0;
        if (isWinFlag == 1)
            return 1;
    }
    if (0 <= row && row + 4 < 19 && 0 <= col && col + 4 < 19)
    {
        //대각선 하향
        isWinFlag = 1;
        for (int i = 0; i < 5; i++)
        {
            int nextx = row + i;
            int nexty = col + i;
            if (color != a[nextx][nexty])
                isWinFlag = 0;
        }
        if (row + 5 < 19 && col + 5 < 19 && color == a[row + 5][col + 5])
            isWinFlag = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && color == a[row - 1][col - 1])
            isWinFlag = 0;
        if (isWinFlag == 1)
            return 1;
    }
    if (0 <= row - 4 && row < 19 && 0 <= col && col + 4 < 19)
    {
        //대각선 상향
        isWinFlag = 1;
        for (int i = 0; i < 5; i++)
        {
            int nextx = row - i;
            int nexty = col + i;
            if (color != a[nextx][nexty])
                isWinFlag = 0;
        }
        if (row - 5 >= 0 && col + 5 < 19 && color == a[row - 5][col + 5])
            isWinFlag = 0;
        if (row + 1 < 19 && col - 1 >= 0 && color == a[row + 1][col - 1])
            isWinFlag = 0;
        if (isWinFlag == 1)
            return 1;
    }
    return 0;
}
int main(void)
{
    for (int i = 0; i < 19; i++)
    {
        for (int j = 0; j < 19; j++)
        {
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < 19; i++)
    {
        for (int j = 0; j < 19; j++)
        {
            if (a[i][j] == 1 || a[i][j] == 2)
            {
                int num = isWin(i, j, a[i][j]);
                if (num == 1)
                {
                    cout << a[i][j] << "\n";
                    cout << i + 1 << " " << j + 1;
                    return 0;
                }
            }
        }
    }
    cout << 0;
    return 0;
}