package algo; /**
 * Created with IntelliJ IDEA.
 * User: yevgen
 * Date: 3/22/14
 * Time: 8:46 AM
 * To change this template use File | Settings | File Templates.
 */

import algo.BasicAlgorithms;
import org.junit.*;
import java.util.*;


public class BasicAlgorithmsTest {
    void testSort(BasicAlgorithms.ISort sort){
        int[] data;
        Random rnd = new Random();
        for(int len = 0; len < 100; len++){
            data = new int[len];
            for(int k = 0; k < 3; k++){
                for(int j = 0; j < len; j++){
                    data[j] = rnd.nextInt() % 10;
                }
                int[] orig_data = data.clone();
                int[] correct = orig_data.clone();
                Arrays.sort(correct);
                sort.sort(data);

                if (!Arrays.equals(data, correct)){
                    System.out.println("ORIG: " + Arrays.toString(orig_data));
                    System.out.println("RES:  " + Arrays.toString(data));
                    System.out.println("EXP:  " + Arrays.toString(correct));
                    System.out.println(sort.getClass());
                    throw new RuntimeException("Oops");
                }
            }
        }
        System.out.println("OK: " + sort.getClass());
    }

    @Test
    public void testInsertionSort(){
        testSort(new BasicAlgorithms.InsertionSort());
    }

    @Test
    public void testSelectionSort(){
        testSort(new BasicAlgorithms.SelectionSort());
    }

    @Test
    public void testQuickSort(){
        testSort(new BasicAlgorithms.QuickSort());
    }

    @Test
    public void testBubbleSort(){
        testSort(new BasicAlgorithms.BubbleSort());
    }

    @Test
    public void testMergeSort(){
        testSort(new BasicAlgorithms.MergeSort());
    }

    @Test
    public void testWTH(){
        int[] data = new int[]{28, 7, 18};
        BasicAlgorithms.Selection selection = new BasicAlgorithms.Selection();
        System.out.println(selection.smallest_k(data, 0));
        System.out.println(Arrays.toString(data));

    }

    @Test
    public void testSelection(){
        Random rnd = new Random();
        BasicAlgorithms.Selection selection = new BasicAlgorithms.Selection();
        for(int len = 0; len < 100; len++){
            int[] data = new int[len];
            for(int k = 0; k < 5; k++){
                for(int i = 0; i < len; i++)
                    data[i] = rnd.nextInt() % 30;

                for(int el = 0; el < 20; el++){
                    int sel = rnd.nextInt() % (len + 2);
                    int[] inp1 = data.clone();
                    int[] inp2 = data.clone();
                    int[] inp3 = data.clone();
                    int sel1 = selection.smallest_k_simple(inp1, el);
                    int sel2 = selection.smallest_k(inp2, el);
                    int sel3 = selection.smallest_k2(inp3, el);
                    if (sel1 != sel2){
                        System.out.printf("el=%d, sel1=%d, sel2=%d\n", el, sel1, sel2);
                        System.out.println(Arrays.toString(data));
                        Arrays.sort(data);
                        System.out.println(Arrays.toString(data));
                        Assert.assertEquals(sel1, sel2);
                    }

                    if (sel1 != sel3){
                        System.out.printf("el=%d, sel1=%d, sel3=%d\n", el, sel1, sel3);
                        System.out.println(Arrays.toString(data));
                        Arrays.sort(data);
                        System.out.println(Arrays.toString(data));
                        Assert.assertEquals(sel1, sel3);
                    }

                }
            }
        }

        System.out.println("Success: " + selection.getClass());
    }
}
