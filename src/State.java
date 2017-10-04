import java.util.ArrayList;
import java.util.Scanner;

public class State {
    double initialValue;
    double previousValue;
    double currentValue;
    ArrayList<Action> action = new ArrayList<Action>();
    int performedAction;


    String stateName;

    public State(String dataLine) {
        System.out.println("In State: " + dataLine);

        Scanner scanner = new Scanner(dataLine);
        scanner.useDelimiter("\\(");
        String input;
        int counter = 0;
        while(scanner.hasNext()) {
            input = scanner.next();
            input = input.replace(")","");

            //System.out.println("Input: " + input);
            Scanner innerScanner = new Scanner(input);
            if(counter==0) {
                stateName = innerScanner.next();
                initialValue = innerScanner.nextDouble();
                //System.out.println("State Name: " + stateName + ", Value: " + initialValue);
            } else {

                Action act = new Action();
                act.setActionNumber(innerScanner.next());
                act.setToState(innerScanner.next());
                act.setProbability(Double.parseDouble(innerScanner.next()));
                action.add(act);
            }
            counter++;
            innerScanner.close();
        }

    }
    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(double previousValue) {
        this.previousValue = previousValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public ArrayList<Action> getAction() {
        return action;
    }

    public void setAction(ArrayList<Action> action) {
        this.action = action;
    }

    public int getPerformedAction() {
        return performedAction;
    }

    public void setPerformedAction(int performedAction) {
        this.performedAction = performedAction;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
