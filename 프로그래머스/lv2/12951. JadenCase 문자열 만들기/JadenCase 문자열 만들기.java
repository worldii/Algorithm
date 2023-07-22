class Solution {
   
    public String solution(String s) {
        boolean beforeFlag = true;
        StringBuilder sb= new StringBuilder ();
        String[] temp = s.split("");
        for (String str : temp) {
            if (beforeFlag) {
                sb.append(str.toUpperCase());
            }
            else {
                sb.append(str.toLowerCase());
            }
            if (str.equals(" ")) beforeFlag = true;
            else beforeFlag = false;
        }
        return sb.toString();
    }
}