package est.booth.com.booth;

public class ListItem {
    protected String count,stepM,stepA,stepL,stepQ,stepTodo;

    public ListItem(String stepM, String count, String stepA, String stepL, String stepQ, String stepTodo) {
        this.stepM = stepM;
        this.count = count;
        this.stepA = stepA;
        this.stepL = stepL;
        this.stepQ = stepQ;
        this.stepTodo = stepTodo;
    }
    public ListItem(String stepM, String count, String stepA, String stepQ, String stepTodo) {
        this.stepM = stepM;
        this.count = count;
        this.stepA = stepA;
        this.stepQ = stepQ;
        this.stepTodo = stepTodo;
    }
}