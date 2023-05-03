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
            moveUpDown(q,r,c,moves);
        } else if (state.equals(LEFT) && c - 1 >= 0) {
            moveUpDown(q,r,c,moves);
        } else if (state.equals(UP) && r - 1 >= 0) {
            moveRightLeft(q,r,c,moves);
        } else if (state.equals(DOWN) && r + 1 < R) {
            moveRightLeft(q,r,c,moves);
        }
    }

    private void moveUpDown(Stack<Node> q,int r, int c, int moves){
        int r1 = r;
        int r2=r;
        while(r1+1<R && map[r1+1][c]!=WALL && map[r1][c]!=END)
            r1++;
        if(r1+1<R && r1!=r)
            q.push(new Node(r1,c,moves+1,DOWN));
        while(r2-1>=0 && map[r2-1][c]!=WALL && map[r2][c]!=END)
            r2--;
        if(r2-1>=0 && r2!=r)
            q.push(new Node(r2, c , moves+1, UP));

    }
    private void moveRightLeft(Stack<Node> q,int r, int c, int moves){
        int c1=c;
        int c2 = c;
        while(c1+1<C && map[r][c1+1]!=WALL && map[r][c1]!=END)
            c1++;
        if(c1+1<C && c1!=c)
            q.push(new Node(r, c1, moves + 1, RIGHT));
        while(c2-1>=0 && map[r][c2-1]!=WALL && map[r][c2]!=END)
            c2--;
        if(c2-1>=0 && c2!=c)
            q.push(new Node(r, c2, moves + 1, LEFT));
    }
}
