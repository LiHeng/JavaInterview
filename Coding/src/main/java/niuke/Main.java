package niuke;

import java.util.*;

public class Main{


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int codepoints[] = new int[n];
        for (int i = 0; i < n; i++) {
            codepoints[i] = in.nextInt();
        }

        int index=0;
        boolean flag = true;
        while (index<n){
            String coding = getBinaryString(codepoints[index]);
            if (coding.startsWith("0")){
                index++;
            }else if (coding.startsWith("110")){
                if (index+1<n){
                    if (!startWith(codepoints[index+1],"10")){
                        flag = false;
                        break;
                    }
                }else {
                    flag = false;
                    break;
                }
                index+=2;
            }else if (coding.startsWith("1110")){
                if (index+2<n){
                    if (!startWith(codepoints[index+1],"10")||!startWith(codepoints[index+2],"10")){
                        flag = false;
                        break;
                    }
                }else {
                    flag = false;
                    break;
                }
                index+=3;
            }else if(coding.startsWith("11110")){
                if (index+3<n){
                    if (!startWith(codepoints[index+1],"10")||!startWith(codepoints[index+2],"10")
                            ||!startWith(codepoints[index+3],"10")){
                        flag = false;
                        break;
                    }
                }else {
                    flag = false;
                    break;
                }
                index+=4;
            }else {
                flag = false;
                break;
            }
        }
        System.out.println(flag?1:0);
    }

    private static boolean startWith(int code, String str){
        String binary = getBinaryString(code);
        return binary.startsWith(str);
    }

    private static String getBinaryString(int i){
        String binary = Integer.toBinaryString(i);
        if (binary.length()>8){
            return binary.substring(binary.length()-8);
        }
        while (binary.length()<8){
            binary = '0'+binary;
        }
        return binary;
    }

}
