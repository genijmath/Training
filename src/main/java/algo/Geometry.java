package algo;

/**
 * Created with IntelliJ IDEA.
 * User: yevgen
 * Date: 3/23/14
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Geometry {

    //follows TopCoder's CultureGrowth problem SRM 249
    double convexHull(int[] xs, int[] ys){
        //returns area of a convex hull
        if (xs.length < 3) return 0;
        final int N = xs.length;

        //find mean point
        double med_x = 0, med_y = 0;
        for(int i = 0; i < N; i++){
            med_x += xs[i];
            med_y += ys[i];
        }
        med_x /= N;
        med_y /= N;


        //find left-top most point
        int start = 0;
        for(int i = 1; i < xs.length; i++){
            if (xs[i] < xs[start])
                start = i;
            else if (xs[i] == xs[start]){
                if (ys[i] > ys[start])
                    start = i;
            }
        }


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

                int cross =  det(xs[n] - xs[p], ys[n] - ys[p],
                        xs[i] - xs[p],  ys[i] - ys[p]);

                if (cross < 0){
                    n = i;
                }
                if (cross == 0){
                    if (norm2(xs[i] - xs[p], ys[i] - ys[p]) > norm2(xs[n] - xs[p], ys[n] - ys[p])){
                        n = i;
                    }
                }

            }

            area += areaTriangle(xs[p], ys[p], xs[n], ys[n], med_x, med_y);

            p = n;
            visited[p] = true;
        }while(p != start);

        return area;

    }

    long norm2(long v1, long v2){
        return v1*v1 + v2 * v2;
    }

    int det(int v1, int v2, int w1, int w2){
        return v1*w2 - v2*w1;
    }

    double det(double v1, double v2, double w1, double w2){
        return v1*w2 - v2*w1;
    }

    double areaTriangle(double x1, double y1, double x2, double y2, double x3, double y3){

        return 0.5 * Math.abs(det(x2-x1, y2-y1, x3-x1, y3-y1));
    }
}
