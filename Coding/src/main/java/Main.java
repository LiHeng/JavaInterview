import java.util.*;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a%b==0){
            System.out.println((double) a/b);
        }else {
            HashMap<Integer,Integer> lefts = new HashMap<>();
            String prefix = "";
            if (a<b){
                prefix = "0.";
            }else {
                prefix+=String.valueOf(a/b);
                a = a%b;
                prefix+=".";
            }
            int index = 0;
            String tail = "";
            while (!lefts.containsKey(a)&&a!=0){
                lefts.put(a,index);
                a = a*10;
                if (a<b){
                    tail+="0";
                    index++;
                    continue;
                }
                tail+=a/b;
                a = a%b;
                index++;
            }

            if (a==0){
                System.out.println(prefix+tail);
            }else {
                int repeatIndex = lefts.get(a);
                String repeat = tail.substring(repeatIndex, index);
                tail = tail.substring(0, repeatIndex);
                tail = tail + "(" + repeat + ")";
                System.out.println(prefix + tail);
            }
        }
    }


}

