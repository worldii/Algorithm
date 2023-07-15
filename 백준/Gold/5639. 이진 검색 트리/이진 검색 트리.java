import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int num;

        TreeNode(TreeNode left, TreeNode right, int num) {
            this.left = left;
            this.right = right;
            this.num = num;
        }
    }


    public static TreeNode add(TreeNode cur, int num) {
        if (cur == null) {
            cur = new TreeNode(null, null, num);
            return cur;
        }
        if (cur.num > num) {

            cur.left = add(cur.left, num);
            return cur;
        } else {
            cur.right = add(cur.right, num);
            return cur;
        }
    }

    public static void main(String[] args) throws IOException {

        TreeNode root = null;
        ArrayList<Integer> list = new ArrayList<>();
        while (true) {
            try {
                int num = Integer.parseInt(br.readLine());
                list.add(num);
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            root = add(root, list.get(i));
        }

        postOrder(root);
        System.out.println(sb.toString());
    }
    public static StringBuilder sb= new StringBuilder();
    private static void postOrder(TreeNode i) {
        if (i == null) return;
        postOrder(i.left);
        postOrder(i.right);
        sb.append(i.num).append("\n");
    }


}
