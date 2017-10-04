import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) {
        System.out.println("Moose");
        System.out.println(args[2]);
        ArrayList<State> states = fileToArrayList(args[2]);

        System.out.println("Printing States");
        for (int y=0; y<states.size(); y++) {
            System.out.println("\nState Name: " + states.get(y).getStateName() + " InitialValue: " + states.get(y).getInitialValue());
            for(int x=0; x<states.get(y).getAction().size(); x++) {
                System.out.println("Action: " + states.get(y).getAction().get(x).getActionNumber() +
                        " State: " + states.get(y).getAction().get(x).getToState() + " Prob: " + states.get(y).getAction().get(x).getProbability());
            }
        }
    }

    private static ArrayList<State> fileToArrayList(String fileName) {
        ArrayList<State> list = new ArrayList<State>();
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);

            String input;
            while(scanner.hasNext()){
                input = scanner.nextLine();
                State newState = new State(input);
                list.add(newState);
            }

        } catch (FileNotFoundException e){
            System.out.println("BAD:(");
        }




        return list;
    }
}
