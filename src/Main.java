import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Calculator calculator = new Calculator();
        Scanner input = new Scanner(System.in);
        String expression;
        System.out.println("请输入表达式:");
        expression=input.nextLine();
        System.out.println("结果是:"+calculator.calculate(expression));
    }
}
