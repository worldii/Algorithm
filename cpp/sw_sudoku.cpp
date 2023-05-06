#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int sudoku[10][10];
typedef struct _cor
{
    int x;
    int y;
} Cor;
vector<Cor> arr;
bool check(int row, int col, int chooseNum)
{
    for (int i = 0; i < 9; i++)
    {
        if (sudoku[row][i] == chooseNum)
            return false;
        if (sudoku[i][col] == chooseNum)
            return false;
    }

    int temprow = row / 3 * 3;
    int tempcol = col / 3 * 3;
    for (int i = temprow; i < temprow + 3; i++)
    {
        for (int j = tempcol; j < tempcol + 3; j++)
        {
            if (sudoku[i][j] == chooseNum)
                return false;
        }
    }
    return true;
}
void backtrack(vector<Cor> arr, int k, int n)
{
    if (k == n)
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                cout << sudoku[i][j] << " ";
            }

            cout << "\n";
        }
        exit(0);
    }
    else
    {
        int tempx = arr[k].x;
        int tempy = arr[k].y;
        for (int i = 1; i <= 9; i++)
        {
            if (check(tempx, tempy, i))
            {
                sudoku[tempx][tempy] = i;
                backtrack(arr, k + 1, n);
                sudoku[tempx][tempy] = 0;
            }
        }
    }
}
int main(void)
{
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            cin >> sudoku[i][j];
            if (sudoku[i][j] == 0)
            {
                Cor temp = {i, j};
                arr.push_back(temp);
            }
        }
    }

    int size = arr.size();
    backtrack(arr, 0, size);
    return 0;
}