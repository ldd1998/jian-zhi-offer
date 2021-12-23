/**
 * @author ldd
 * @date 2021-12-23 19:27
 * @description
 * 面试题2：二进制加法
 * 题目：输入两个表示二进制的字符串，请计算它们的和，并以二进制字符串的形式输出。
 * 例如，输入的二进制字符串分别是"11"和"10"，则输出"101"。
 */
public class BinaryAdd {
    public static String binaryAddFun(String a,String b){
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder result = new StringBuilder();
        //进位变量
        int carry = 0;
        while(i >= 0 || j >= 0){
            //a变量的最后一位
            int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            //b变量的最后一位
            int digitB = j >= 0 ? a.charAt(j--) - '0' : 0;
            //相加后的总数
            int sum = digitA + digitB + carry;
            //进位的值
            carry = sum >= 2 ? 1 : 0;
            //进位后当前位值
            sum = sum >= 2 ? sum - 2 : sum;
            result.append(sum);
        }
        //最后检查是否还剩进位没有进
        if(carry ==1){
            result.append("1");
        }
        return result.reverse().toString();
    }
    public static void main(String[] args) {
        System.out.println(binaryAddFun("101","10"));
        System.out.println(binaryAddFun("111","10"));
    }
}
