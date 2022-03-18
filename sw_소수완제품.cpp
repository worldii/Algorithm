#include <iostream>
using namespace std;
int combi[20][20];
int cnt[12] = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
double getnum(int prob)
{
    double num = (double)prob / 100;
    double sum = 0;
    for (int i = 0; i < 12; i++)
    {
        int loop1 = cnt[i];
        int loop2 = 18 - cnt[i];
        double prob1 = 1;
        for (int j = 0; j < loop1; j++)
        {
            prob1 *= num;
        }
        double prob2 = 1;
        for (int j = 0; j < loop2; j++)
        {
            prob2 *= (1 - num);
        }
        sum += combi[18][loop1] * prob1 * prob2;
        //      cout << combi[18][loop1] * prob1 * prob2 << "\n";
    }
    return sum;
}
int main(void)
{

    for (int i = 0; i <= 20; i++)
    {
        combi[i][i] = 1;
        combi[i][0] = 1;
    }
    for (int i = 1; i <= 20; i++)
    {
        for (int j = 1; j < i; j++)
        {
            combi[i][j] = combi[i - 1][j - 1] + combi[i - 1][j];
        }
    }

    int tc;
    cin >> tc;
    for (int i = 1; i <= tc; i++)
    {
        int proba, probb;
        cin >> proba >> probb;
        cout.precision(6);
        cout << fixed;

        cout << 1 - (getnum(proba) * getnum(probb));
    }
    return 0;
}