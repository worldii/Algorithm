#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;
int R, C;
char arr[1501][1501];
queue<pair<int, int>> q;
queue<pair<int, int>> nextq;
queue<pair<int, int>> wq;
queue<pair<int, int>> wnextq;
vector<pair<int, int>> back;
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int check[1501][1501];
int check2[1501][1501];

void ClearQueue(std::queue<pair<int, int>> &someQueue)
{
    std::queue<pair<int, int>> empty;
    std::swap(someQueue, empty);
}

int main(void)
{
    cin >> R >> C;
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            cin >> arr[i][j];
            if (arr[i][j] == 'L')
            {
                back.push_back({i, j});
            }
            if (arr[i][j] != 'X')
            {
                wq.push({i, j});
                check2[i][j] = 1;
            }
        }
    }
    q.push({back[0].first, back[0].second});
    check[back[0].first][back[0].second] = 1;

    int count = 0;
    // 백조 시점
    // 물이면은 q에다가 계속 푸쉬
    // 빙판이면 nextq에다가 푸쉬
    // 백조이면 만나는것
    while (1)
    {

        while (!q.empty())
        {
            int tempx = q.front().first;
            int tempy = q.front().second;
            q.pop();
            //  cout << tempx << " " << tempy << "\n";

            for (int i = 0; i < 4; i++)
            {
                int newx = tempx + dx[i];
                int newy = tempy + dy[i];
                if (0 <= newx && newx < R && 0 <= newy && newy < C)
                {

                    if (check[newx][newy] == 0)
                    {
                        check[newx][newy] = 1;
                        if (arr[newx][newy] == '.')
                        {
                            q.push({newx, newy});
                        }
                        else if (arr[newx][newy] == 'X')
                        {
                            nextq.push({newx, newy});
                        }
                        else if (arr[newx][newy] == 'L')
                        {
                            cout << count;
                            return 0;
                        }
                    }
                }
            }
        }
        // 물 시점
        // 물이면은 q에다가 푸쉬
        // 빙판이면 nextq에다가 푸쉬하고 물로 바꾸어줌.

        while (!wq.empty())
        {
            int tempx = wq.front().first;
            int tempy = wq.front().second;
            wq.pop();
            for (int i = 0; i < 4; i++)
            {
                int newx = tempx + dx[i];
                int newy = tempy + dy[i];

                if (0 <= newx && newx < R && 0 <= newy && newy < C)
                {
                    if (check2[newx][newy] == 0)
                    {
                        check2[newx][newy] = 1;
                        if (arr[newx][newy] == 'X')
                        {
                            wnextq.push({newx, newy});
                            arr[newx][newy] = '.';
                        }
                    }
                }
            }
        }
        // printf("dasdsa\n");
        q = nextq;
        while (!nextq.empty())
            nextq.pop();
        wq = wnextq;
        while (!wnextq.empty())
            wnextq.pop();
        count++;
        // printf("dasdsdda\n");
    }
    return 0;
}