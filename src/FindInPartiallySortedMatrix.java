/**
 * @description: 二维数组中的查找
 * 思路1：首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；
 * 如果该数字大于要查找的数字，则剔除这个数字所在的列；如果该数字小于要查找的数字，则
 * 剔除这个数字所在的行。直到找到要查找的数字，或者查找范围为空。(也可以选取左下角)
 * 时间复杂度O(mn),空间复杂度O(1)
 *
 * 思路2：矩阵的每一行都是有序递增的数组，对每一行进行二分查找
 * @author: Daniel
 * @create: 2019-02-15 09:59
 **/
public class FindInPartiallySortedMatrix {

    public boolean find1(int target, int[][] array) {
        if(array == null) return false;
        int row = array.length;
        int col = array[0].length;
        int i = 0, j = col - 1; // 右上角数字坐标
        while(i >= 0 && i < row && j >= 0 && j < col) { // 这里的判断条件可以简化为 i<row && j >= 0
            if(target == array[i][j]) // 正好等于
                return true;
            else if(target > array[i][j]) // 小于目标数字，剔除行
                i++;
            else  // 大于目标数字，剔除列
                j--;
        }
        return false; // 找不到，查找范围为空
    }

    public boolean find2(int target, int[][] array) {
        if(array == null) return false;
        for(int i=0; i<array.length; i++) { // 循环m次
            int start = 0;
            int end = array[i].length - 1;
            while(start <= end) { // O(logn)
                int mid = start + ((end - start) >> 1);
                if(target == array[i][mid])
                    return true;
                else if(target > array[i][mid])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[][] array1 = {{}};
        int[][] array2 = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int[][] array3 = {{1},{2},{3},{5}};
        int[][] array4 = {{1,2,3,4,5}};
        FindInPartiallySortedMatrix findInPartiallySortedMatrix = new FindInPartiallySortedMatrix();
        System.out.println(findInPartiallySortedMatrix.find1(1,array1)); // 特殊输入
        System.out.println(findInPartiallySortedMatrix.find1(7,array2)); // 最大值最小值之间
        System.out.println(findInPartiallySortedMatrix.find1(1,array2)); // 查找最小值
        System.out.println(findInPartiallySortedMatrix.find1(15,array2)); // 查找最大值
        System.out.println(findInPartiallySortedMatrix.find1(5,array2)); // 没有该数字
        System.out.println(findInPartiallySortedMatrix.find1(7,array3));
        System.out.println(findInPartiallySortedMatrix.find1(5,array4));

        System.out.println("---------------------------");

        System.out.println(findInPartiallySortedMatrix.find2(1,array1)); // 特殊输入
        System.out.println(findInPartiallySortedMatrix.find2(7,array2)); // 最大值最小值之间
        System.out.println(findInPartiallySortedMatrix.find2(1,array2)); // 查找最小值
        System.out.println(findInPartiallySortedMatrix.find2(15,array2)); // 查找最大值
        System.out.println(findInPartiallySortedMatrix.find2(5,array2)); // 没有该数字
        System.out.println(findInPartiallySortedMatrix.find2(7,array3));
        System.out.println(findInPartiallySortedMatrix.find2(5,array4));
    }
}
