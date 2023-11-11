import java.util.*;

class Solution {
    public class Node {
        int data;
        Node pre;
        Node next;
        Node(int data) {
            this.data =data;
            this.pre = pre;
            this.next= next;
        }
    }

   public class MyLinkedList {
       private Node head;
       private Node tail;
       private Node cur;
       MyLinkedList(int n, int cursor) {
          this.tail = new Node (-1);
          this.head = new Node(-1);
          head.next = tail;
          tail.pre = head;
          Node temp = head;
          for (int i = 0 ; i< n ; i++) {
              Node newNode = new Node(i);
              temp.next = newNode;
              newNode.pre = temp;
              temp = temp.next;
          }
          temp.next = tail;
          tail.pre = temp;
          cur = head.next;
          for (int i = 0 ; i< cursor; i++) {
             cur = cur.next;
          }
           System.out.println(cur.data);
       }
       public Node[] remove(){
           Node beforeNode = this.cur.pre;
           Node AfterNode = this.cur.next;
           Node removeNode = this.cur;
           beforeNode.next = AfterNode;
           AfterNode.pre = beforeNode;
           if (cur.next.data !=-1) cur = cur.next;
           else cur = cur.pre;
           return new Node[]{beforeNode, removeNode, AfterNode};
       }
       
       public void up(int num){
           int count = 0;
           while (count < num) {
               cur = cur.pre;
               count++;
           }
       }
       public void down(int num){
           int count =0;
           while (count < num){
               cur = cur.next;
               count++;
           }
       }
       public void add(Node beforeNode, Node addNode, Node afterNode){
           beforeNode.next = addNode;
           addNode.next= afterNode;
           addNode.pre = beforeNode;
           afterNode.pre = addNode;
       }
      
       public String getAnswer(int n){
           Node temp = head.next;
           StringBuilder  answer = new StringBuilder();
           boolean [] isdeleted = new boolean[n];
           while (temp.data != -1){
               isdeleted[temp.data] = true;
               temp = temp.next;
           }
           for (int i = 0 ; i<n ; i++){
                 if (isdeleted[i]) answer.append("O");
                else answer.append("X");
           }
           return answer.toString();
       }
   }
    
    public String solution(int n, int k, String[] cmd) {
        MyLinkedList linkedList = new MyLinkedList(n,k);
        Stack<Node []> deleteNodes = new Stack<>();
        for (String str : cmd) {
            if (str.equals("Z")){
                Node [] remove = deleteNodes.pop();
                linkedList.add(remove[0], remove[1], remove[2]);
            }
            else if (str.equals("C")){
                Node[] remove= linkedList.remove();
                deleteNodes.add(remove);
            }
            else {
                String[] cmds = str.split(" ");
                int num = Integer.parseInt(cmds[1]);
                if (cmds[0].equals("D")) {
                    linkedList.down(num);
                }
                else {
                  linkedList.up(num);
                }
            }
        }
        return linkedList.getAnswer(n);
    }
}