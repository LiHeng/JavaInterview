import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        System.out.println(isValid(line));
    }

    private static int isValid(String line){
        String current = "";
        Stack<Character> stack = new Stack<>();
        Stack<Integer> num = new Stack<>();
        int i=0;
        for (i=0;i<line.length();i++) {
            char c = line.charAt(i);
            if (isOp(c)){
                stack.push(c);
            }else if (c >= '0' && c <= '9'){
                num.push(c-'0');
            }else if(c=='('){
                stack.push(c);
            }else if (c==')'){
                char op = stack.pop();
                while(op!='('){
                    if(op=='^'){
                        num.push(num.pop()+1);
                    }else if (op=='+'){
                        num.push(num.pop()+num.pop());
                    }else if (op=='*'){
                        num.push(num.pop()*num.pop());
                    }else if (op=='-'){
                        num.push(num.pop()-num.pop());
                    }else if (op=='/'){
                        num.push(num.pop()/num.pop());
                    }
                    op = stack.pop();
                }
            }else if (c==' '){
               continue;
            }
        }

        if (stack.isEmpty()){
            return num.get(0);
        }else {
            return -1;
        }
    }

    private static boolean isOp(char c){
        return c=='^'||c=='+'||c=='*';
    }

}
