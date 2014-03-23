

import java.util.*;

public class CultureGrowth{
    public double finalTray(int[] xs, int[] ys){

        //find left-top most point
        int start = 0;
        int N = xs.length;
        for(int i = 1; i < xs.length; i++){
            if (xs[i] < xs[start])
                start = i;
            else if (xs[i] == xs[start]){
                if (ys[i] > ys[start])
                    start = i;
            }
        }

        //find mean point
        double med_x = 0, med_y = 0;
        for(int i = 0; i < N; i++){
            med_x += xs[i];
            med_y += ys[i];
        }
        med_x /= N;
        med_y /= N;

        boolean[] visited = new boolean[N];


        int p = start;

        double area = 0;

        do{
            int n = -1;
            for(int i = 0; i < N; i++){
                if (i==p) continue;
                if (visited[i]) continue;

                if (n==-1){
                    n = i;
                    continue;
                }

                //NOT DONE YET!
                ///int cross =


            }
        }while(p != start);

        return area;

    }
}

