import java.util.*;

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
        Queue<Node> q = new LinkedList<>();
        list.add(root);
        int r = root.getR();
        int c = root.getC();
        int moves = root.getMoves();

        if(map[r][c]==END) return moves;
        moveUpDown(q, r, c, moves);
        moveRightLeft(q, r, c, moves);
        int bestMove = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Node cur = q.poll();
            r = cur.getR();
            c = cur.getC();
            moves = cur.getMoves();
            String state = cur.getState();
            if(map[r][c]== END) bestMove = Math.min(bestMove, cur.getMoves());
            if(list.contains(cur)) continue;
            list.add(cur);
            move(q,r,c,moves,state);
        }

        return bestMove==Integer.MAX_VALUE ? -1 : bestMove;
    }

    public void move(Queue<Node> q,int r, int c,int moves, String state) {

        if (state.equals(RIGHT) || state.equals(LEFT)){
            moveUpDown(q,r,c,moves);
        } else if (state.equals(UP) || state.equals(DOWN)) {
            moveRightLeft(q,r,c,moves);
        }
    }

    private void moveUpDown(Queue<Node> q,int r, int c, int moves){
        int r1 = r;
        int r2=r;
        while(r1+1<R && map[r1+1][c]!=WALL && map[r1][c]!=END)
            r1++;
        if((r1+1<R ) || map[r1][c]==END)
            q.add(new Node(r1,c,moves+1,DOWN));
        while(r2-1>=0 && map[r2-1][c]!=WALL && map[r2][c]!=END)
            r2--;
        if((r2-1>=0) || map[r2][c]==END)
            q.add(new Node(r2, c , moves+1, UP));

    }
    private void moveRightLeft(Queue<Node> q,int r, int c, int moves){
        int c1=c;
        int c2 = c;
        while(c1+1<C && map[r][c1+1]!=WALL && map[r][c1]!=END)
            c1++;
        if((c1+1<C) || map[r][c1]==END)
            q.add(new Node(r, c1, moves + 1, RIGHT));
        while(c2-1>=0 && map[r][c2-1]!=WALL && map[r][c2]!=END)
            c2--;
        if((c2-1>=0) || map[r][c2]==END)
            q.add(new Node(r, c2, moves + 1, LEFT));
    }
}
