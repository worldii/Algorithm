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
#include<sstream>
using namespace std;

int n, m;
int check[1000];
int temp[1000];
void solve(int cnt) {
	if (cnt == m) {
		for (int i = 0; i < cnt; i++) {

			cout << temp[i] << ' ';
		}cout << '\n';
		return;
	}
	for (int i = 1; i <= n; i++) {
		if (check[i] == 0) {
			check[i] = 1;
			temp[cnt] = i;
			solve(cnt + 1);
			check[i] = 0;
		}
	}
}
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m;
	solve(0);
	return 0;
}