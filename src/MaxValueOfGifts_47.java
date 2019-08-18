/**
 * @description: 礼物的最大价值
 * 动态规划：定义f(i,j)为到达(i,j)位置格子时能拿到的礼物总和的最大值，则有：f(i,j)=max{f(i-1,j),f(i,j-1)}+values(i,j)。
 * @author: Daniel
 * @create: 2019-02-22-19-24
 **/
public class MaxValueOfGifts_47 {

    public static int maxValueOfGiftsRecursive(int[][] values) {
        if(values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int rows = values.length;
        int cols = values[0].length;
        return maxValueOfGiftsRecursive(values,rows-1,cols-1);
    }

    public static int maxValueOfGiftsRecursive(int[][] values, int i, int j) {
        if(i < 0 || j < 0)
            return 0;
        int maxValue = Math.max(maxValueOfGiftsRecursive(values,i-1,j), maxValueOfGiftsRecursive(values,i,j-1));
        return maxValue + values[i][j];
    }

    public static int maxValueOfGifts(int[][] values) {
        if(values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int rows = values.length;
        int cols = values[0].length;
        int[][] maxValue = new int[rows][cols];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                int left = 0;
                int up = 0;
                if(i > 0)
                    up = maxValue[i-1][j];
                if(j > 0)
                    left = maxValue[i][j-1];
                maxValue[i][j] = Math.max(up,left) + values[i][j];
            }
        }
        return maxValue[rows-1][cols-1];
    }

    // 优化后的代码
    public static int maxValueOfGifts2(int[][] values) {
        if(values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int rows = values.length;
        int cols = values[0].length;
        int[] maxValue = new int[cols];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                int left = 0;
                int up = 0;
                if(i > 0)
                    up = maxValue[j];
                if(j > 0)
                    left = maxValue[j-1];
                maxValue[j] = Math.max(up,left) + values[i][j];
            }
        }
        return maxValue[cols-1];
    }

    public static void main(String[] args) {
        int[][] data = {
                {1,10,3,8},
                {12,2,9,6},
                {5,7,4,11},
                {3,7,16,5}};
        System.out.println(maxValueOfGiftsRecursive(data));
        System.out.println(maxValueOfGifts(data));
        System.out.println(maxValueOfGifts2(data));
    }
}
