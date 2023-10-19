import java.util.*;
class Solution {
    public List<Employee> list = new ArrayList<>();
    class Employee {
        String laun;
        String workEnd;
        String workYear;
        String food;
        int score;
        Employee (String laun, String workEnd, String workYear, String food, int score) {
            this.laun = laun;
            this.workEnd= workEnd;
            this.workYear= workYear;
            this.food =food;
            this.score = score;
        }
        public String toString(){
            return laun+ " " + workEnd+ " " + workYear+ " " + food+ " " + score;
        }
    }
    public Map<String,ArrayList<Employee>> map= new HashMap<>();
    public void recur(String[] laun, String[] workEnd, String[] workYear, String[] food, Employee employee) {
        for (int i = 0 ; i< laun.length ; i++) {
            for (int j = 0 ; j< workEnd.length ; j++) {
                for (int t= 0 ;t< workYear.length; t++) {
                    for (int k = 0 ; k< food.length ; k++) {
                        StringBuilder sb= new StringBuilder();
                        String key = sb.append(laun[i]).append(workEnd[j])
                            .append(workYear[t]).append(food[k]).toString();
                        ArrayList<Employee> temp = map.getOrDefault(key, new ArrayList<>());
                        temp.add(employee);
                        map.put(key, temp);
                    }
                }
            }
        }
        
    }
     
    public void infoMap(String [] info){
        for (int i = 0 ; i< info.length; i++) {
            String[] splArr = info[i].split(" ");
            Employee employee = new Employee(splArr[0], splArr[1],splArr[2], splArr[3], Integer.parseInt(splArr[4]));
            recur(new String[] {splArr[0] , "-"},
                  new String[] {splArr[1], "-"}, 
                  new String[] {splArr[2], "-"},
                  new String[] {splArr[3], "-"}, 
                  employee
                 );
        }
    }
    
    public int binarySearch (ArrayList<Employee> list, int targetScore){
        int start = 0;
        int end = list.size();
        while (start < end) {
            int mid = (start +end) /2;
            if (list.get(mid).score >= targetScore) {
                end = mid;
            }
            else start = mid +1;
        }
    
        return list.size()-end;
    }
    public int findQuery(String query) {
        String[] splArr = query.split(" and ");
        String [] splArr2= splArr[3].split(" ");
        String key = new StringBuilder()
            .append(splArr[0])
            .append(splArr[1])
            .append(splArr[2]).
            append(splArr2[0]).toString();
        ArrayList<Employee> empList = map.getOrDefault(key, new ArrayList<>());       
        return binarySearch(empList, Integer.parseInt(splArr2[1]));
    }
    public int[] solution(String[] info, String[] query) {
        infoMap(info);
        for (String key : map.keySet()) {
           ArrayList<Employee> emp =  map.getOrDefault(key, new ArrayList<>());
            Collections.sort(emp, (a,b)-> a.score - b.score);
        }
        int [] answer = new int [query.length];
        for (int i = 0 ; i< query.length ; i++) {
            answer[i] = findQuery(query[i]);
        }
        return answer;
    }
}