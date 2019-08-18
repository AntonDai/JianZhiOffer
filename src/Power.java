/**
 * @description: 数值的整数次方
 * @author: Daniel
 * @create: 2019-02-19-08-40
 **/
public class Power {

    public double power(double base, int exponent) {
        if(equal(base,0))
            throw new IllegalArgumentException("invalid input!"); // 底数不能为0
        int absExponent = exponent < 0 ? (-exponent) : exponent;
//        double result = powerWithUnsignedExponent(base,absExponent);
        double result = powerWithUnsignedExponent2(base,absExponent);
        return exponent < 0 ? (1 / result) : result;
    }

    private double powerWithUnsignedExponent(double base, int absExponent) {
        double result = 1.0;
        for(int i=1; i<= absExponent; i++)
            result *= base;
        return result;
    }

    // 更高效的做法
    private double powerWithUnsignedExponent2(double base, int absExponent) {
        if(absExponent == 0)
            return 1;
        if(absExponent == 1)
            return base;
        double result = powerWithUnsignedExponent(base,absExponent >> 1);
        result *= result;
        if((absExponent & 1) == 1) // 奇数
            result *= base;
        return result;
    }

    // 在允许的误差下比较两个浮点数是否相等
    public boolean equal(double num1, double num2) {
        double error = 1.0e-6;
        if(Math.abs(num1 - num2) < error)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Power power = new Power();
        power.test(2,3,8);
        power.test(-2,3,-8);
        power.test(2,-3,0.125);
        power.test(2,0,1);
        // 要求底数不能为0
//        power.power(0,-2); // 测试不合法的输入
    }

    public void test(double base, int exponent, double expected) {
        double result = power(base,exponent);
        if(equal(result,expected))
            System.out.println("passed");
        else
            System.out.println("failed");
    }
}
