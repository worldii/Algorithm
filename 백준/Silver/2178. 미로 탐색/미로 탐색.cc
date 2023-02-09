#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<cstring>
#include<algorithm>
#include<vector>
#include<queue>
#include<stack>
#include<string>
#include<cstring>
#include<set>
#include<cstdlib>

using namespace std;

int check[130][130];
int a[101][101];
int dx[4] = { -1,1,0,0 };
int dy[4] = {0,0, 1,-1};
int n, m;
int dist[100][100];

void   bfs(int i, int j) {
	queue<pair<int, int>> q;
	q.push({ i,j });
	check[i][j] =1;
	while (!q.empty()) {
		int tx = q.front().first;
		int ty = q.front().second;
		q.pop();
		if (tx == n - 1 && ty == m - 1) return;
		for (int k = 0; k < 4; k++) {
			int nx = tx + dx[k];
			int ny = ty + dy[k];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (a[nx][ny] == 0) continue;
				if (check[nx][ny] != 0) continue;
				q.push({ nx, ny });
				check[nx][ny] = 1;
				dist[nx][ny] = dist[tx][ty] + 1;
			}			
		}
	
	}
}
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++)
		{
			scanf("%1d", &a[i][j]);
		}
	}
	dist[0][0] = 1;
	bfs(0, 0);

	cout << dist[n - 1][m - 1];
 return 0;
}