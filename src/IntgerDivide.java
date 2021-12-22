/**
 * @author ldd
 * @date 2021-12-22 21:36
 * @description
 * 面试题1：整数除法
 * 输入2个int型整数，它们进行除法计算并返回商，要求不得使用乘号'*'、除号'/'及求余符号'%'。
 * 当发生溢出时，返回最大的整数值。假设除数不为0。例如，输入15和2，输出15/2的结果，即7。
 */
public class IntgerDivide {
    /**
     * @param dividend 被除数
     * @param divide 除数
     * @return 商的整数
     * @description 循环相减
     */
    public static int subtractionDivide(int dividend,int divide){
        int result = 0;
        //这里还要考虑Intger.MIN_VALUE/-1得到的整数会溢出的情况
        if(dividend == Integer.MIN_VALUE && divide == -1){
            return Integer.MAX_VALUE;
        }
        //除数和被除数都变为负数，因为正数转负数不会出现数据溢出
        int sign = 2;
        if(dividend > 0){
            dividend = -dividend;
            sign--;
        }
        if(divide > 0){
            divide = -divide;
            sign--;
        }
        //循环相减
        while(dividend <= divide){
            dividend -= divide;
            result++;
        }
        if(sign==1){
            result = -result;
        }
        return result;
    }

    /**
     * @param dividend 被除数
     * @param divide 除数
     * @return 商的整数
     * @description 循环相减升级版
     */
    public static int substationDivideImprove(int dividend,int divide){
        int result = 0;
        //这里还要考虑Integer.MIN_VALUE/-1得到的整数会溢出的情况
        if(dividend == Integer.MIN_VALUE && divide == -1){
            return Integer.MAX_VALUE;
        }
        //除数和被除数都变为负数，因为正数转负数不会出现数据溢出
        int sign = 2;
        if(dividend > 0){
            dividend = -dividend;
            sign--;
        }
        if(divide > 0){
            divide = -divide;
            sign--;
        }

        result = divideCore(dividend,divide);

        if(sign==1){
            result = -result;
        }
        return result;
    }

    /**升级版核心代码*/
    private static int divideCore(int dividend, int divisor) {
        int result = 0;
        while(dividend <= divisor){
            int value = divisor;
            int quotient = 1;
            while (value >= 0xc0000000 && dividend <= value + value){
                quotient += quotient;
                value += value;
            }
            result += quotient;
            dividend -= value;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subtractionDivide(Integer.MAX_VALUE,2));
        System.out.println(subtractionDivide(Integer.MIN_VALUE,-1));
        System.out.println(substationDivideImprove(Integer.MAX_VALUE,2));
        System.out.println(substationDivideImprove(Integer.MIN_VALUE,-1));
        System.out.println(substationDivideImprove(Integer.MIN_VALUE,-1));
    }
}