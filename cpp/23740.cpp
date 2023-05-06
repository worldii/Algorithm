#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
typedef struct _tt
{
    int start;
    int end;
    int cost;
} temp;
vector<temp> hello;
vector<temp> h2;
bool compare(temp a, temp b)
{
    return a.start < b.start;
}
int main(void)
{
    int n;
    cin >> n;
    int start, end, cost;
    start = -1;
    end = -1;
    cost = -1;

    for (int i = 0; i < n; i++)
    {
        int s, e, c;
        cin >> s >> e >> c;
        hello.push_back(temp{s, e, c});
    }
    sort(hello.begin(), hello.end(), compare);
    for (int i = 0; i < hello.size(); i++)
    {
        if (start == -1 && end == -1 && cost == -1)
        {
            start = hello[i].start;
            end = hello[i].end;
            cost = hello[i].cost;
        }
        else
        {
            if (end < hello[i].start || start > hello[i].end)
            {
                h2.push_back(temp{start, end, cost});
               // printf("push ");
                    start = hello[i].start;
                end = hello[i].end;
                cost = hello[i].cost;
            }
            else
            {
                if (start <= hello[i].start &&  hello[i].end<= end) {
 start = min (hello[i].start , start);
                    end = max (hello[i].end, end);
                    cost = min (hello[i].cost, cost);
                  //  cout<<"a";
                }
                else if ( hello[i].start<= start &&  end<= hello[i].end) { 
                    start = min (hello[i].start , start);
                    end = max (hello[i].end, end);
                    cost = min (hello[i].cost, cost);
                    //cout<<"b";
                    }

                // 겹치는 구간이 있으면
                else if (end >= hello[i].start)
                {
                    //cout<<"c";
                    end = hello[i].end;
                    if (cost > hello[i].cost)
                        cost = hello[i].cost;
                }
                else if (start <= hello[i].end )
                {
                    //cout<<"d";
                    start = hello[i].start;
                    if (cost > hello[i].cost)
                        cost = hello[i].cost;
                }
                
            }
        }
        if (i == hello.size() - 1)
        {
            h2.push_back(temp{start, end, cost});
         // printf("push ");
        }
        //cout << start << end << cost << '\n';
    }
    cout << h2.size() << '\n';
    for (int i = 0; i < h2.size(); i++)
    {
        cout << h2[i].start << " " << h2[i].end << " " << h2[i].cost << '\n';
    }
    return (0);
}