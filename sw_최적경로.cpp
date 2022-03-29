#include <algorithm>
#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
typedef struct _cor
{
    int x;
    int y;
} Cor;

Cor house;
Cor company;
int minSum = 987654321;

int distance(Cor c1, Cor c2)
{
    return abs(c1.x - c2.x) + abs(c1.y - c2.y);
}
int make_candidate(int k, int a[], int n, int C[])
{
    int inNum[100] = {
        0,
    };
    for (int i = 1; i < k; i++)
    {
        inNum[a[i]]++;
    }

    int Cnum = 0;
    for (int i = 1; i <= n; i++)
    {
        if (inNum[i] == 0)
        {
            C[Cnum++] = i;
        }
    }
    return Cnum;
}

void process(int a[], int n, vector<Cor> people)
{
    int sum = 0;
    sum += distance(company, people[a[1] - 1]);
    for (int i = 1; i < n; i++)
    {
        sum += distance(people[a[i] - 1], people[a[i + 1] - 1]);
    }
    sum += distance(house, people[a[n] - 1]);

    if (minSum > sum)
    {
        minSum = sum;
    }
}

int getDis(int k, int Csum, vector<Cor> people, int a[])
{
    int sum = 0;
    sum += distance(company, people[a[1] - 1]);
    for (int i = 1; i < k; i++)
    {
        sum += distance(people[a[i] - 1], people[a[i + 1] - 1]);
    }
    sum += distance(house, people[a[k] - 1]);
    return sum;
}

void backtrack(int a[], int k, int n, vector<Cor> people, int Csum)
{

    if (k == n)
    {
        process(a, n, people);
    }
    else
    {
        k++;
        int C[100] = {
            0,
        };
        int Cnum = make_candidate(k, a, n, C);
        for (int i = 0; i < Cnum; i++)
        {
            a[k] = C[i];
            int num = k;
            int tempDis = getDis(k, Csum, people, a);
            if (tempDis < minSum)
            {
                backtrack(a, k, n, people, tempDis);
            }
        }
    }
}
int main(void)
{
    int tc;
    cin >> tc;
    for (int t = 1; t <= tc; t++)
    {
        int n;
        cin >> n;
        vector<Cor> people(n);
        minSum = 987654321;

        cin >> company.x >> company.y >> house.x >> house.y;
        for (int i = 0; i < n; i++)
        {
            cin >> people[i].x >> people[i].y;
        }
        int a[100] = {
            0,
        };
        backtrack(a, 0, n, people, 0);
        cout << "#" << t << " " << minSum << "\n";
    }
    return 0;
}