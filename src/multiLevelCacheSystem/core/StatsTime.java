package multiLevelCacheSystem.core;

import java.util.LinkedList;

public class StatsTime {
    int c;
    int totTime;
    LinkedList<Integer> times;

    StatsTime(int c){
        this.c = c;
        times = new LinkedList<>();
    }

    public int getAvgTime() {
        if(times.size() < 1){
            return 0;
        }
        return totTime / times.size();
    }

    public void addToTottime(int time) {
        if(times.size() < c){
            times.add(time);
            totTime = totTime + time;
        }else{
            int removed = times.poll();
            totTime = totTime - removed;
        }
    }
}
