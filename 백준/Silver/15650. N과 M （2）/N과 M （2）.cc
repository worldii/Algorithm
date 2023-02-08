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

void solve(int cnt, int selected) {
	if (selected == m) {
		for (int i = 0; i < m; i++) {
			cout << temp[i] << ' ';
		}
		cout << '\n';
		return;
	}

	if (cnt >n) return;
	temp[selected] = cnt;
	solve(cnt + 1, selected+1);
//	temp[selected] = 0;
	solve(cnt + 1, selected );
}


int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	cin >> n >> m;
	solve(1,0);
	return 0;
}