import java.util.Stack;

public class Calculator {
    private Stack<Character> OPTR = new Stack<>();
    private Stack<Double> OPND = new Stack<>();
    private final char end = '=';

    public double calculate(String expression){
        try{
            return Evaluate_expreesion(expression);
        }catch (CalcuException e){
            Double f = null;
            System.out.println(e.getMessage());
            System.exit(0);
            return f;
        }
    }

    private char Precede(char a, char b) {
        int i = 6, j = 6;
        char pre[][] =
                {
        /*运算符之间的优先级制作成一张表格*/
                        {'>', '>', '<', '<', '<', '>', '>'},
                        {'>', '>', '<', '<', '<', '>', '>'},
                        {'>', '>', '>', '>', '<', '>', '>'},
                        {'>', '>', '>', '>', '<', '>', '>'},
                        {'<', '<', '<', '<', '<', '=', '0'},
                        {'>', '>', '>', '>', '0', '>', '>'},
                        {'<', '<', '<', '<', '<', '0', '='}
                };
        switch (a) {
            case '+':
                i = 0;
                break;
            case '-':
                i = 1;
                break;
            case '*':
                i = 2;
                break;
            case '/':
                i = 3;
                break;
            case '(':
                i = 4;
                break;
            case ')':
                i = 5;
                break;
            case '#':
                i = 6;
                break;
        }
        switch (b) {
            case '+':
                j = 0;
                break;
            case '-':
                j = 1;
                break;
            case '*':
                j = 2;
                break;
            case '/':
                j = 3;
                break;
            case '(':
                j = 4;
                break;
            case ')':
                j = 5;
                break;
            case '#':
                j = 6;
                break;
        }
        return pre[i][j];
    }


    private double Evaluate_expreesion(String expression) throws CalcuException {
        int i = 0;
        int count = 0;
        char x, theta;
        double a, b;
        char c = expression.charAt(i);
        OPTR.push(end);
        expression = removeSpace(expression);
        x=OPTR.peek();
        while (c!='=' || x!='=' ) {
            count = 0;
            if (Character.isDigit(c)) {
                StringBuffer tempNum = new StringBuffer();
                while (Character.isDigit(c) || c == '.') {
                    if(c=='.' && count==1)  throw new CalcuException("输入错误！");
                    tempNum.append(c);
                    if (c == '.') count++;
                    c = expression.charAt(++i);
                }
                OPND.push(Double.valueOf(tempNum.toString()));
            } else {
                switch (Precede(x, c)) {
                    case '<':
                        OPTR.push(c);
                        c = expression.charAt(++i);
                        break;
                    case '=':
                        x = OPTR.pop();
                        c = expression.charAt(++i);
                        break;
                    case '>':
                        theta = OPTR.pop();
                        b = OPND.pop();
                        a = OPND.pop();
                        OPND.push(calculate(a, theta, b));
                        break;

                }
            }
            x=OPTR.peek();


        }
        return OPND.peek();
    }



    private double calculate(double a, char theta, double b) throws CalcuException {
        switch (theta) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return div(a, b);
            default:
                throw new CalcuException("运算符错误！");
        }
    }

    private double div(double a, double b) throws CalcuException {
        if (b == 0)
            throw new CalcuException("除数不能为0!");
        return a / b;
    }

    private String removeSpace(String S){
        int i=0;
        char[] ch=S.toCharArray();
        StringBuffer str=new StringBuffer();
        while(i<S.length()){
            if(ch[i]!=' '){
                str.append(ch[i]);
            }
            i++;
        }
        return str.toString();
    }
}
