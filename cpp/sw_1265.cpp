#include <iostream>
using namespace std;

long long maxsum;
int n, p;

int main(void)
{
    int tc;
    cin >> tc;
    for (int i = 1; i <= tc; i++)
    {
        maxsum = 0;
        cin >> n >> p;
        int arr[101] = {
            0,
        };
        for (int i = 0; i < n; i++)
        {
            arr[i % p]++;
        }
        /*  for (int i = 0; i < n; i++) {
              cout << arr[i];
          }*/
        long long ans = 1;
        for (int i = 0; i < p; i++)
        {
            ans *= arr[i];
        }
        cout << "#" << tc << " " << ans << "\n";
    }
    return 0;
}