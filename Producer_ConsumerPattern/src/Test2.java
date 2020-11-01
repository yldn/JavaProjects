
import java.util.Scanner;
import java.util.Stack;

public class Test2 {

    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            long n = sc.nextLong();
        if (n > 1000000 || n < 0){
            System.out.println("input false");
            return;
        }

        Stack stack1 = new Stack();
        Stack stack2 = new Stack();


        for(int i = 0; i < n; i++){
            String c = sc.next();
            c.toLowerCase();
            if (c.equals( "add") ){
               long x = sc.nextLong();
                if (x > 1000000 || x < -1000000){
                    System.out.println("input false");
                    return;
                }
                stack1.push(x);



            }else if (c.equals("poll")) {

                for (long sss = 0; sss < stack1.size() ; sss++) {
                    stack2.push(stack1.pop());
                }

//                System.out.println(stack1.pop());
                stack1.pop();

                for (long sss = 0; sss < stack2.size() ; sss++) {
                    stack1.push(stack2.pop());
                }

            }else if (c.equals("peek")){
                System.out.println(stack1.get(0));

            }else{
                continue;
            }
        }
    }
}
