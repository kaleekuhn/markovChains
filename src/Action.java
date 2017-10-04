import java.util.*;

public class Action {
    String actionNumber;
    String toState;
    double probability;

    public String getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(String actionNumber) {
        this.actionNumber = actionNumber;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
