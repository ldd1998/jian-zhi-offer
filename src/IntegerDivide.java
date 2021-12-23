/**
 * @author ldd
 * @date 2021-12-22 21:36
 * @description
 * 面试题1：整数除法
 * 输入2个int型整数，它们进行除法计算并返回商，要求不得使用乘号'*'、除号'/'及求余符号'%'。
 * 当发生溢出时，返回最大的整数值。假设除数不为0。例如，输入15和2，输出15/2的结果，即7。
 */
public class IntegerDivide {
    /**
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商的整数
     * @description 循环相减
     */
    public static int subtractionDivide(int dividend,int divisor,Divide divide){
        int result;
        //这里还要考虑Integer.MIN_VALUE/-1得到的整数会溢出的情况
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        //除数和被除数都变为负数，因为正数转负数不会出现数据溢出
        int sign = 2;
        if(dividend > 0){
            dividend = -dividend;
            sign--;
        }
        if(divisor > 0){
            divisor = -divisor;
            sign--;
        }
        //循环相减
        result = divide.divide(dividend,divisor);

        if(sign==1){
            result = -result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subtractionDivide(Integer.MAX_VALUE,2,new ReduceDivide()));
        System.out.println(subtractionDivide(Integer.MIN_VALUE,-1,new ReduceDivide()));
        System.out.println(subtractionDivide(Integer.MAX_VALUE,2,new ReduceDivideImprove()));
        System.out.println(subtractionDivide(Integer.MIN_VALUE,-1,new ReduceDivideImprove()));
    }
}
interface Divide{
    /**
     * 除法的抽象方法
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商的整数
     */
    int divide(int dividend, int divisor);
}
class ReduceDivide implements Divide{
    /**通过循环相减除法*/
    @Override
    public int divide(int dividend, int divisor) {
        int result = 0;
        while(dividend <= divisor){
            dividend -= divisor;
            result++;
        }
        return result;
    }
}

class ReduceDivideImprove implements Divide{
    /**Integer最大值的一半*/
    private static final int MAX_VALUE_HALF = 0xc0000000;
    /**升级版循环相减实现除法代码*/
    @Override
    public int divide(int dividend, int divisor) {
        int result = 0;
        while(dividend <= divisor){
            int value = divisor;
            int quotient = 1;
            while (value >= MAX_VALUE_HALF && dividend <= value + value){
                quotient += quotient;
                value += value;
            }
            result += quotient;
            dividend -= value;
        }
        return result;
    }
}