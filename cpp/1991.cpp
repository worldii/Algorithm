#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
typedef struct _node
{
    int left;
    int right;
} Node;
Node tree[27];
;
void preorder(int n)
{
    if (n == 0)
        return;
    cout << (char)n;
    preorder(tree[n - 'A'].left);
    preorder(tree[n - 'A'].right);
};
void inorder(int n)
{
    if (n == 0)
        return;
    inorder(tree[n - 'A'].left);
    cout << (char)n;
    inorder(tree[n - 'A'].right);
};
void postorder(int n)
{
    if (n == 0)
        return;
    postorder(tree[n - 'A'].left);
    postorder(tree[n - 'A'].right);
    cout << (char)n;
};
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        char n1, n2, n3;
        cin >> n1 >> n2 >> n3;
        if (n2 == '.')
        {
            tree[n1 - 'A'].left = 0;
        }
        else
        {
            tree[n1 - 'A'].left = n2;
        }

        if (n3 == '.')
        {
            tree[n1 - 'A'].right = 0;
        }
        else
        {
            tree[n1 - 'A'].right = n3;
        }
    }
    preorder('A');
    cout << "\n";
    inorder('A');
    cout << "\n";
    postorder('A');

    return 0;
}