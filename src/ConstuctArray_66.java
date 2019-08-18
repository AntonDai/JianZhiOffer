/**
 * @description: 构建乘积数组
 * @author: Daniel
 * @create: 2019-02-27-15-25
 **/
public class ConstuctArray_66 {

    public int[] multiply(int[] A) {
        if(A == null || A.length == 0)
            return A;
        int[] B = new int[A.length]; // B[i] = C[i] * D[i];
        B[0] = 1;
        for(int i=1; i<A.length; i++) // C[i] = A[0]*A[1]*A[2]*...*A[i-1] = C[i-1]*A[i-1]
            B[i] = B[i-1] * A[i-1];
        int temp = 1;
        for(int i=A.length-2; i>=0; i--) { // D[i] = A[i+1]*A[i+2]*...*A[n-1] = D[i+1]*A[i+1]
            temp *= A[i+1];
            B[i] *= temp;
        }
        return B;
    }


}
