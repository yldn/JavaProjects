import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n > 10000 || n < 0){
            System.out.println("input false");
            return;
        }
        long x ;
        int k ;
        for(int i = 0; i < n; i++){
            x = sc.nextLong();
            if (x > 100000000000 || x < 0){
                System.out.println("input false");
                return;
            }

            k = sc.nextInt();
            if (k > 60 || k < 0){
                System.out.println("input false");
                return;
            }





        }





    }
}
