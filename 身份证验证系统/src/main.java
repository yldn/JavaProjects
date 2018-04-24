import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {
    private int[] IDnumber;

    public main(int[] IDnumber) {
        this.IDnumber = IDnumber;

    }

    public static void main(String[] args){
        System.out.println("please input your ID number:");
        String ID = new Scanner(System.in).next();

        System.out.println(checkID(parseID(ID)));
    }

    private static boolean checkID(int[] IDnumber){
        if(IDnumber.length != 18){
            return false;
        }
        int refer = multiply(IDnumber)%11;

        int [] setArray = {1,0,10,9,8,7,6,5,4,3,2};

        if (setArray[refer] == IDnumber[IDnumber.length-1]){
            return true;
        }
        else return false;


    }
    private static int multiply(int[] IDnumber){
        if(IDnumber.length != 18){
            return 0;
        }
        int [] setArrays = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        int result = 0;
        for(int i = 0; i < 17;i++){
            result += IDnumber[i]*setArrays[i];
        }
        return result;
    }

    private static int[] parseID(String ID){
        List<Integer> result = new LinkedList<Integer>();
        int i = 0;
        while (i < ID.length()){
            if(ID.charAt(i) == 'X'||ID.charAt(i) == 'x'){
                result.add(10);
            }
            else
                result.add(Character.getNumericValue(ID.charAt(i)));
            i++;
        }
        return result.stream().mapToInt(x->x).toArray();
    }


}
