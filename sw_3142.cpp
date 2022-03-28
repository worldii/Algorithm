#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <map>
using namespace std;
map<string, int> namelist;
int loginsum;
int validate(string ID)
{

    return namelist.count(ID);
}
int activate(string ID)
{
    if (validate(ID))
    {

        return namelist[ID];
    }
    return 0;
}
int close(string ID)
{
    if (validate(ID))
    {
        if (namelist[ID])
            loginsum--;
        namelist.erase(ID);
        }
    return namelist.size();
}
int signup(string ID)
{
    if (validate(ID) == 0)
    {
        namelist.insert(make_pair(ID, 0));
    }
    return namelist.size();
}
int login(string ID)
{
    int sum = 0;
    if (validate(ID) && namelist[ID] == 0)
    {
        namelist[ID] = 1;
        loginsum++;
    }

    return loginsum;
}
int logout(string ID)
{

    if (validate(ID))
    {
        if (namelist[ID])
        {
            namelist[ID] = 0;
            loginsum--;
        }
    }

    return loginsum;
}

int chooseCmd(int num, string name)
{
    if (num == 1)
    {
        int idx;
        return validate(name);
    }
    else if (num == 2)
    {
        return activate(name);
    }
    else if (num == 3)
    {
        return signup(name);
    }
    else if (num == 4)
    {
        return close(name);
    }
    else if (num == 5)
    {
        return login(name);
    }
    else if (num == 6)
    {
        return logout(name);
    }
    return 0;
}

int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int num;
        string name;
        cin >> num >> name;
        cout << chooseCmd(num, name) << "\n";
    }
    return 0;
}
