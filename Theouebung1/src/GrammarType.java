import java.util.Scanner;

public class GrammarType {


    public static boolean isType0(Grammar g){
    	return true;
    }

    public static boolean isType1(Grammar g){
        for(Production p: g.getProductions()) {
        	if((p.getRightSide().length()==0)&&(p.getRightSide().length()==1)&&
        			(g.getStartingSymbol().toString() .contains(p.getRightSide().toString()))){
        		continue;
        	}else {
        		if(p.getRightSide().length()<p.getLeftSide().length()) {
        			return false;
        		}
        	}
        }
        return true;
    }

    public static boolean isType2(Grammar g){
        //TODO
        return false;
    }

    public static boolean isType3(Grammar g){
        //TODO
        return false;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Grammar g = Grammar.parseGrammar(s);
        System.out.println(isType0(g));
        System.out.println(isType1(g));
        System.out.println(isType2(g));
        System.out.println(isType3(g));
    }

}
