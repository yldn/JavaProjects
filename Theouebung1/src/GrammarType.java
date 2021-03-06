import java.util.Iterator;
import java.util.Scanner;

public class GrammarType {


    public static boolean isType0(Grammar g){
    	return true;
    }

    public static boolean isType1(Grammar g){
    	Iterator<Production> i = g.getProductions().iterator();
        while (i.hasNext()) {
        	Production p = i.next();
        	//jump if right side == empty && left in Startsymbol
        	// not sure if .contains safe
        	if((p.getRightSide().length()==0)&&(p.getLeftSide().length()==1)&&
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
        if(!isType1(g)) {
        	return false;
        }
        Iterator<Production> i = g.getProductions().iterator();
        while (i.hasNext()) {
        	Production p = i.next();
        	char[] char_array = p.getLeftSide().toCharArray();
        	if(!g.getNonTerminals().contains(char_array[0])) {
        		return false ;
        	};
        	
        }
        return true;
    }

    public static boolean isType3(Grammar g){
    	if(!isType2(g)) {
    		return false;
    	}
    	Iterator<Production> i = g.getProductions().iterator();
        while (i.hasNext()) {
        	Production p = i.next();
        	// not sure if .contains safe
        	if((p.getRightSide().length()==0)&&(p.getLeftSide().length()==1)&&
        			(g.getStartingSymbol().toString() .contains(p.getRightSide().toString()))){
        		continue;
        	}else {
        		char[] char_array = p.getRightSide().toCharArray();
        		
        		char lastChar = char_array[0];
        		
        		if(g.getNonTerminals().contains(lastChar)) {
        			return false;
        		}
        		for(int j = 1 ; j< char_array.length ; j++) {
        			char curChar = char_array[j];
        			
        			if(g.getAlphabet().contains(curChar)) {
        				if(g.getNonTerminals().contains(lastChar)) {
        					return false;
        				}
        			}
        			lastChar = curChar;
        		}
        		
        	}
        }
        return true;
    }
    /*Grammar
    Nonterminals: A
    Alphabet: a,b,c
    Startsymbol: A
    Productions:
    A -> a|b|c
    END*/
    
    /*Grammar
    Nonterminals: A
    Alphabet: a,b,c
    Startsymbol: A
    Productions:
    A -> Aa|
    END*/

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Grammar g = Grammar.parseGrammar(s);
        System.out.println(isType0(g));
        System.out.println(isType1(g));
        System.out.println(isType2(g));
        System.out.println(isType3(g));
    }

}
