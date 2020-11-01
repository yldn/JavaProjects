
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String out = parse(str);
//        out.toLowerCase();
//
//        HashMap<Character,Integer> mp = new HashMap<Character,Integer>();
//        for (char i : out.toCharArray()) {
//            if (!mp.containsKey(i)){
//                mp.put(i,1);
//            }
//            else{
//                int v = mp.get(i);
//                mp.replace(i,v+1) ;
//            }
//        }
//        Integer[] vs = (Integer[]) mp.values().toArray();
//        int max = 0;
//        for (Integer i : vs ){
//            if(max < i ){
//                max = i ;
//            }
//        }



    }




//   static String  parse(String input){
////       "A3bc2X-b2"
//        StringBuffer bf = new StringBuffer();
////       RLE compressed with 3 letters
//        char char1;
//        char char2;
//       for (int i = 0; i < input.length(); i++) {
//           char1 = input.charAt(i);
//           StringBuffer tmp = new StringBuffer();
//           if((input.charAt(i)>'A'&& input.charAt(i)<'Z')||(input.charAt(i)>'a'&& input.charAt(i)<'z')){
//               //encoded?
//               tmp.append(char1);
//               if(input.charAt(i+1)== '-') {
//                   char2 = input.charAt(i+2);
//
//                    for (;char1<=char2;char1++){
//                        if( char1 > 'Z' && char1 < 'a'){
//                            char1 = 'a';
//                        }
//                        tmp.append(char1);
//                    }
//
//                   i+=2;
//               }
//
//           }
//           else if(input.charAt(i+1)< '9' &&input.charAt(i+1)> '0'){
//
//               StringBuffer p = new StringBuffer();
//               int j = i+1;
//               while ((input.charAt(j)< '9' &&input.charAt(j)> '0')){
//                   p.append(input.charAt(j));
//               }
//               int times = Integer.parseInt(p.toString()) ;
//
//               //duplicate
//               for (int k = 0; k < times ; k++) {
//                   bf.append(tmp);
//               }
//
//
//           }else{
//               return null;
//           }
//
//
//       }
//       return  bf.toString();
//
//    }






    //    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//
//
//        int notice_number = in.nextInt();
//        int[][] st = new int[notice_number][2];
//        //read reyclic
//
//        for (int i = 0; i < notice_number ; i++) {
//            int startTime = in.nextInt();
//            //not included
//            int endTime = in.nextInt();
//            st[i][0] = startTime;
//            st[i][1] = endTime;
//        }
//
//
//
//        int count = 1 ;
//        for (int i = 0; i < st.length; i++) {
//            for (int j = i+1; j < st.length; j++) {
//                if(st[i][1]>st[j][0]){
//                    count ++;
//                }
//            }
//        }
//
//        System.out.println(count);
//
//
//
//    }


}
