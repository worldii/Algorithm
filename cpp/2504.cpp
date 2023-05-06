#include<iostream>
#include<stack>
using namespace std;

stack<int> st;
int main(void) {
    cin.tie(NULL);
    ios_base::sync_with_stdio(0);
    cout.tie(NULL);
    string str;
    cin>>str;
    int sum=0;
    int flag =0;
    if( str[0] ==']' || str[0]==')' ) {
        cout<<0;
        return 0;
    }
    for (int i = 0  ; i< str.length() ; i++) {
        // ] , ) 이거면 [, ( 나올때까지 pop 해야함. 
        // 근디 사이즈 0되면 0 출력 하고 종료.
        // [, ( 나오면 나온값 * 2 , *3 하고 sum 에 더함.
        
        if (str[i] ==']') {
          int tmp =0;
          if( str[i-1] =='[') st.push('1');
          while (st.top() !='[') {
           
            tmp += st.top()-'0';
            st.pop();
               if(st.size()==0) {
                  cout<<0;
                  return 0;
              }
          }
          st.pop();
         flag --;
          st.push(tmp*3+'0');
        
        }
        if (str[i] ==')'){    
        if(str[i-1]=='(') st.push('1');      
          int tmp =0;
          while (st.top() !='(') {
            tmp += st.top()-'0';
            st.pop();
              if( st.size() ==0) {
                cout<<0;
                return 0;
            }
          }
          st.pop();
          flag--;
          st.push(tmp*2 +'0');
        }
        // [, (  이거면 그냥 푸쉬
        if( str[i] =='[' || str[i] =='(' ) {
            st.push(str[i]);
            flag ++;
        }
    }
    if(flag !=0) {
        cout<<0;
    }
    while(!st.empty()) {
        sum+=st.top()-'0';
        st.pop();
       }
   //    cout<<flag;
    cout<<sum;
    
}