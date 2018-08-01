package niuke;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static BigInteger fac(BigInteger n){
        if (Objects.equals(n, BigInteger.ONE)){
            return BigInteger.ONE;
        }

        return fac(n.subtract(BigInteger.ONE)).multiply(n);
    }

    public static int getZero(BigInteger m){
        String s = m.toString();
        char[] arr = s.toCharArray();
        int count=0;
        for (int i=arr.length-1; i >=0; i--) {
            if (arr[i]=='0'){
                count++;
            }else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n= scanner.nextLine();
        System.out.println(getZero(fac(new BigInteger(n))));
    }
}
