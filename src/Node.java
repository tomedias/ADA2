public class Node {
    private final int r, c;
    private final String state;


    public Node(int r, int c,String state){
        this.r = r;
        this.c = c;
        this.state = state;

    }
    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public String getState() {
        return state;
    }

}
