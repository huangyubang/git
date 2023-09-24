import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
    //密码管理系统主页面
        while (true) {

            System.out.println("================");
            System.out.println("欢迎来到密码管理系统");
            System.out.println("================");
            System.out.println("请选择操作：");
            System.out.println("1.加密");
            System.out.println("2.解密");
            System.out.println("3.判断密码强度");
            System.out.println("4.密码生成");
            System.out.println("5.退出");
            System.out.println("");
            System.out.println("请选择选项序号：");

            //用Scanner实现键盘录入数据
            Scanner sc = new Scanner(System.in);
            String ch = sc.nextLine();

            //用switch语句完成操作的选择
            switch (ch) {
                case "1":
                    Encryption();
                    break;
                case "2":
                    Decryption();
                    break;
                case "3":
                    Judge();
                    break;
                case "4":
                    Pg();
                    break;
                case "5":
                    System.out.println("谢谢使用");
//                  break;
//退出整个系统
                    System.exit(0);
            }
        }
    }
    //加密操作
    public static void Encryption(){
        System.out.println("请输入密码:");
        String input = new Scanner(System.in).nextLine();
        // 把字符串变成字节数组
        char[] ch = input.toCharArray();
        StringBuilder sb=new StringBuilder();
        //将字节数组按要求进行改变
        for (int i=0; i< ch.length;i++) {
            int b1 = ch[i];
            //往右边移动3位
            b1 = b1 + (i+1) + 3 ;
            char b2 = (char) b1;
            sb.append(b2);
        }
        //将首位和末尾调换位置
        char a= sb.charAt(0);
        char b= sb.charAt(ch.length-1);
        sb.setCharAt(0,b);
        sb.setCharAt(ch.length-1,a);
        //将字符串反转
        sb.reverse();
        System.out.println("密文："+sb.toString());

    }
    //解密操作
    public static void Decryption(){
        System.out.println("请输入你想要破解的密码:");
        String input = new Scanner(System.in).nextLine();
        //反转字符串
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        sb1.append(input);
        sb1.reverse();
        //将首位和末尾调换位置
        char a= sb1.charAt(0);
        char b= sb1.charAt(input.length()-1);
        sb1.setCharAt(0,b);
        sb1.setCharAt(input.length()-1,a);
        //按要求对该字符串进行移位
        for (int i = 0; i < input.length(); i++) {
            int b1=sb1.charAt(i);
            b1 = b1 - (i+1) - 3;
            char b2 =(char)b1;
            sb2.append(b2);
        }
        System.out.println("破解后的密码是："+sb2.toString());
    }
    //判断密码强度
    public static void Judge(){
        System.out.println("请输入密码(数字、大小写字母且长度<=16)：");
        String input = new Scanner(System.in).nextLine();
        String regex="[^0-9a-zA-Z]+";
        if(!input.matches(regex)||input.length()>16){
            System.out.println("输入的密码格式有误!!!");
            System.exit(0);
        }
        String regex1="[0-9]*[a-zA-Z]{0}";
        String regex2="[a-z]*[0-9A-Z]{0}";
        String regex3="[A-Z]*[0-9a-z]{0}";
        String regex4="[a-z0-9]+[A-Z]{0}";
        String regex5="[A-Z0-9]+[a-z]{0}";
        String regex6="[0-9a-zA-Z]+";

        if(input.length()<8||input.matches(regex1)||input.matches(regex2)||input.matches(regex3)){
            System.out.println("该密码强度为：弱强度");
        } else if ((input.length()>=8&&input.matches(regex4))||(input.length()>=8&&input.matches(regex5))) {
            System.out.println("该密码强度为：中强度");
        }
        else if (input.length()>=8&&input.matches(regex6)) {
            System.out.println("该密码强度为：高强度");
        }
    }
    //密码生成器
    public static void Pg(){
        System.out.println("请输入你想生成的密码的长度X(16>=x>=8)：");
        int input=new Scanner(System.in).nextInt();
        if(input<8||input>16){
            System.out.println("您输入的密码长度不在指定范围内!!!");
            System.exit(0);
        }
        ArrayList<Character> list=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        Random r=new Random();

        String regex="[0-9a-zA-Z]+";

        String a="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] a1=a.toCharArray();

        while (true){
            for (int i = 0; i < input; i++) {
                list.add(i,a1[r.nextInt(62)]);
                sb.append(list.get(i));
            }

            if(sb.length()>=8&&sb.toString().matches(regex)==true){
                break;
            }
        }
        System.out.println("生成的随机密码为："+sb.toString());
    }
}