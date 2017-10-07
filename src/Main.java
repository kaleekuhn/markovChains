import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) {
      //  System.out.println("Moose");
        //System.out.println(args[3]);
        ArrayList<State> states = fileToArrayList(args[2]);

      /*  System.out.println("Printing States");
        for (int y=0; y<states.size(); y++) {
            System.out.println("\nState Name: " + states.get(y).getStateName() + " InitialValue: " + states.get(y).getInitialValue());
            for(int x=0; x<states.get(y).getAction().size(); x++) {
                System.out.println("Action: " + states.get(y).getAction().get(x).getActionNumber() +
                        " State: " + states.get(y).getAction().get(x).getToState() + " Prob: " + states.get(y).getAction().get(x).getProbability());
            }
        }*/
     for(int x=0;x<states.size();x++)
     {
         states.get(x).setPreviousValue(states.get(x).getInitialValue());
         states.get(x).setCurrentValue(states.get(x).getInitialValue());
     }
        CreateOutput(states,Double.parseDouble(args[3]));
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
    private static void CreateOutput(ArrayList<State> States,double discount)
    {
        String maxActionName="";
        String currentActionName="";
        DecimalFormat df= new DecimalFormat("#.0000");
        double maxActionValue=-100;
        double tempActionValue=0;
        for(int iteration=1; iteration<=20; iteration++)
        {
            System.out.println("After Iteration "+ iteration+": ");
                for(int currentState=0; currentState<States.size(); currentState++)
                {
                    if(iteration==1)
                    {
                        System.out.print("("+States.get(currentState).getStateName()+" "
                                +States.get(currentState).getAction().get(0).getActionNumber()
                                +" " +df.format(States.get(currentState).getInitialValue())+") ");
                    }
                    else {
                        for (int action = 0; action < States.get(currentState).getAction().size(); action++)//if preferred action needs to be saved into the actual state
                        {                                                                               //this is where it will be changed
                            if (action == 0)
                            {
                                currentActionName = States.get(currentState).getAction().get(0).getActionNumber();
                                maxActionName=currentActionName;
                            }


                            if (States.get(currentState).getAction().get(action).getActionNumber().equals(currentActionName)) {
                                //for instance State in position 0       action in position 0

                                tempActionValue += discount
                                        * getToStatePreviousValue(States, States.get(currentState).getAction().get(action).getToState())
                                          *States.get(currentState).getAction().get(action).getProbability();

                                if(action+1== States.get(currentState).getAction().size())
                                {
                                    tempActionValue+= States.get(currentState).getInitialValue();
                                    if(tempActionValue>maxActionValue)
                                    {
                                        maxActionName=currentActionName;
                                        maxActionValue=tempActionValue;

                                    }
                                }
                            }
                            else{
                                tempActionValue+= States.get(currentState).getInitialValue();
                                if(tempActionValue>maxActionValue)
                                {
                                    maxActionName=currentActionName;
                                    maxActionValue=tempActionValue;

                                }
                                currentActionName=States.get(currentState).getAction().get(action).getActionNumber();
                                tempActionValue=discount
                                        * getToStatePreviousValue(States, States.get(currentState).getAction().get(action).getToState())
                                        *States.get(currentState).getAction().get(action).getProbability();
                            }

                        }

                        System.out.print("("+States.get(currentState).getStateName()+" "+ maxActionName+" "+df.format(maxActionValue)+") ");
                        States.get(currentState).setCurrentValue(maxActionValue);
                      }
                    maxActionValue =-100;
                    tempActionValue=0;
                }
            if(iteration!=1)
                switchStateValues(States);
                System.out.println();
            System.out.println();
        }
    }
    private static double getToStatePreviousValue(ArrayList<State> states, String lookString)
    {
        for(int x=0; x<states.size();x++)
        {
            if(states.get(x).getStateName().equals(lookString))
                return states.get(x).getPreviousValue();
        }
        return 0;
    }
    private static void switchStateValues(ArrayList<State> s)
    {
        for(int x=0;x<s.size();x++)
            s.get(x).setPreviousValue(s.get(x).getCurrentValue());
    }
}
