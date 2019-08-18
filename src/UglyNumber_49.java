/**
 * @description: 丑数
 * @author: Daniel
 * @create: 2019-02-23-09-47
 **/
public class UglyNumber_49 {
    // 判断一个数是否是丑数 p = 2^x*3^y*5^z
    public static boolean isUgly(int number) {
        while(number % 2 == 0)
            number /= 2;
        while(number % 3 == 0)
            number /= 3;
        while(number % 5 == 0)
            number /= 5;
        return number == 1;
    }

    // 返回从小到大的第n个丑数
    public static int getUglyNumber(int index) {
        if(index <= 0)
            return 0;
        int number = 0;
        int count = 0;
        while(count < index) {
            number++;
            if(isUgly(number))
                count++;
        }
        return number;
    }

    public static int getUglyNumber2(int index) {
        if(index < 1) return 0;
        // p2,p3,p5分别为3个队列的指针，min 为从三个队列头中选出的最小数
        int p2 = 0, p3 = 0, p5 = 0, min = 1;
        int[] array = new int[index]; // 存储n个丑数的数组
        int cur = 0;
        array[cur] = min;
        while(++cur < index) {
            // 选出三个队列头中最小的数
            min = Math.min(array[p2] * 2, Math.min(array[p3] * 3,array[p5] * 5));
            if(array[p2] * 2 == min)
                p2++;
            if(array[p3] * 3 == min)
                p3++;
            if(array[p5] * 5 == min)
                p5++;
            array[cur] =  min;
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(getUglyNumber(1500));
        System.out.println(getUglyNumber2(1500));
    }
}

