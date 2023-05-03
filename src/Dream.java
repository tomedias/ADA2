import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dream {


    public static final char END = 'H';
    public static final char WALL = 'O';

    public static final String RIGHT = "right";

    public static final String LEFT = "left";

    public static final String UP = "up";

    public static final String DOWN = "down";

    private final char[][] map;
    private final int R, C;
    private List<Node> list;
    public Dream(char[][] map, int R, int C) {
        this.map = map;
        this.R = R;
        this.C = C;
        this.list = new ArrayList<>();

    }

    public int solve(Node root){
        list = new ArrayList<>();
        Stack<Node> q = new Stack<>();
        list.add(root);
        int r = root.getR();
        int c = root.getC();
        int moves = root.getMoves();
        if(map[r][c]==END) return moves;
        moveUpDown(q, r, c, moves);
        moveRightLeft(q, r, c, moves);
        do{
            Node cur = q.pop();
            if(list.contains(cur)) continue;
            list.add(cur);
            r = cur.getR();
            c = cur.getC();
            moves = cur.getMoves();
            String state = cur.getState();
            if(map[r][c]== END) return moves;
            move(q,r,c,moves,state);
        }while(!q.isEmpty());

        return -1;
    }

    public void move(Stack<Node> q,int r, int c,int moves, String state) {

        if (state.equals(RIGHT) && c + 1 < C) {
            if (map[r][c + 1] != WALL) q.push(new Node(r, c + 1, moves, RIGHT));
            else {
                moveUpDown(q,r,c,moves);
            }
        } else if (state.equals(LEFT) && c - 1 >= 0) {
            if (map[r][c - 1] != WALL) q.push(new Node(r, c - 1, moves, LEFT));
            else {
                moveUpDown(q,r,c,moves);
            }
        } else if (state.equals(UP) && r - 1 >= 0) {
            if (map[r - 1][c] != WALL) q.push(new Node(r - 1, c, moves, UP));
            else {
                moveRightLeft(q,r,c,moves);
            }
        } else if (state.equals(DOWN) && r + 1 < R) {
            if (map[r + 1][c] != WALL) q.push(new Node(r + 1, c, moves, DOWN));
            else {
                moveRightLeft(q,r,c,moves);
            }
        }
    }

    private void moveUpDown(Stack<Node> q,int r, int c, int moves){
        if(r+1<R && map[r+1][c]!=WALL) q.push(new Node(r+1,c,moves+1,DOWN));
        if(r-1>=0 && map[r-1][c]!=WALL) q.push(new Node(r-1, c , moves+1, UP));
    }
    private void moveRightLeft(Stack<Node> q,int r, int c, int moves){
        if(c+1<C && map[r][c+1]!=WALL) q.push(new Node(r, c+1, moves + 1, RIGHT));
        if(c-1>=0 && map[r][c-1]!=WALL) q.push(new Node(r, c-1, moves + 1, LEFT));
    }
}
