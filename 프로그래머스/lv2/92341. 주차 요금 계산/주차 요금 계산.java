import java.util.*;
class Solution {
    
    public int upTime (int time , int perTime) {
        if (time % perTime == 0) return time/ perTime;
        return time/perTime +1;
    }
    
    public int getFare(int basicFare, int basicTime , int time, int perFare, int perTime){
        if (basicTime >= time) return basicFare;
        return basicFare + upTime(time - basicTime, perTime) * perFare;
    }

    static class Car {
        Time time;
        int inOut;
        int total;
        Car (Time time, int inOut, int total) {
            this.time = time;
            this.inOut = inOut;
            this.total =total;
        }
    }

     class Time {
        int hour;
        int minute;
        Time(int hour, int minute) {
            this.hour= hour;
            this.minute = minute;
        }
        Time add (Time start, Time end) {
            if (start.minute+ end.minute >= 60) {
                return new Time(start.hour+ end.hour+1, start.minute+ end.minute-60);
            }
            return new Time(start.hour+ end.hour, start.minute + end.minute);
        }
        
        Time diff(Time start) {
            if (minute < start.minute) {
                hour -=1;
                minute+=60;
            }
            return new Time (hour - start.hour,  minute- start.minute);
        }
        
        Time (String str) {
            String [] num = str.split(":");
            this.hour =  Integer.parseInt(num[0]);
            this.minute = Integer.parseInt(num[1]);
        }
        public int getTime () {
            return hour * 60 + minute;
        }
        
    }

 
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<Integer, Car> carMap = new HashMap<>();
        
        for (int i = 0  ; i < records.length ; i++) {
            String[] tempCar = records[i].split(" ");
            Time time =  new Time(tempCar[0]);
            int num = Integer.parseInt(tempCar[1]);
            String inFlag = (tempCar[2]);
            
            if (inFlag.equals("IN")) {
                Car newCar = carMap.get(num);
                if (newCar== null) {
                    newCar = new Car(null, 1, 0);
                }
                carMap.put(num, new Car(time, 1, newCar.total));
            }
            else {
                Car newCar = carMap.get(num);
                // 시간을 계산한다. 
                Time beforeTime = newCar.time;
                
                int realTime = time.diff(beforeTime).getTime();
               // System.out.println(realTime);

                carMap.put(num, new Car(null, 2, newCar.total + realTime));
            }
        }
                           
        // Map 
        List<Integer> keySet = new ArrayList<>(carMap.keySet());
        for (Integer num : keySet) {
            Car car = carMap.get(num);
            if (car.inOut% 2!=0) {
                Time beforeTime = car.time;
                int realTime = new Time("23:59").diff(beforeTime).getTime();
                carMap.put(num, new Car(null, 2, car.total + realTime ));
            }
        }
        Collections.sort(keySet);
        int idx = 0;
        answer = new int [keySet.size()];
        for (Integer num : keySet) {
            Car car = carMap.get(num);
            int carFare =getFare(fees[1], fees[0], car.total, fees[3], fees[2]);
            //System.out.println(num+" " + car.total+ " " + carFare);
            answer[idx++] = carFare;            
        }
     
        return answer;
    }
}