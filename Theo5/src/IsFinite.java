import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class IsFinite {

	public static boolean isFinite(DFA d) {
		
		for (Transition t : d.getTransitions()) {
			State s = t.getStart();
			if (t.getEnd() == s) {
				return false;
			}
		}

		return true;
	}
	
    public Set<State> computeReachableStates(){
        return computeReachableStates(startState);
    }

    public Set<State> computeReachableStates(State s){
        Set<State> result = new HashSet<>();
        //implement a set as working stack
        List<State> worklist = new ArrayList<State>();
        worklist.add(s);
        while(worklist.size()>0){
            State p = worklist.get(0);
            worklist.remove(0);
            if(!result.contains(p)){
                result.add(p);
                for (State q : getAllPossibleSuccessors(p)) {
                    worklist.add(q);//having the list makes me add stuff multiple times here, but it makes iterating much easier
                }
            }
        }
        return result;
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EpsilonNFA e = Parser.parse(scanner);
		DFA d = null;
		if (e instanceof DFA) {
			d = (DFA) e;
		} else {
			System.out.println("No DFA provided, aborting");
			System.exit(3);
		}
		System.out.println(isFinite(d));
		scanner.close();
	}

}
