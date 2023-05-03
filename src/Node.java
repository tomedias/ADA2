
public class Node {
    private final int r, c, moves;
    private final String state;
    public Node(int r, int c, int moves,String state){
        this.r = r;
        this.c = c;
        this.moves = moves;
        this.state = state;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getMoves() {
        return moves;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return r == node.r && c == node.c;
    }


}
