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
        Node n = moveUpDown(q, r, c, moves);
        if(n!=null) return n.getMoves();
        n = moveRightLeft(q, r, c, moves);
        if(n!=null) return n.getMoves();
        while(!q.isEmpty()){
            Node cur = q.poll();
            r = cur.getR();
            c = cur.getC();
            moves = cur.getMoves();
            String state = cur.getState();
            if(list.contains(cur)) continue;
            list.add(cur);
            n =move(q,r,c,moves,state);
            if(n!=null) return n.getMoves();
        }
        return -1;
    }

    public Node move(Queue<Node> q,int r, int c,int moves, String state) {
        if (state.equals(RIGHT) || state.equals(LEFT)){
            return moveUpDown(q,r,c,moves);
        } else if (state.equals(UP) || state.equals(DOWN)) {
            return moveRightLeft(q,r,c,moves);
        }
        return null;
    }

    private Node moveUpDown(Queue<Node> q,int r, int c, int moves){
        int r1 = r;
        int r2=r;
        while (r1 + 1 < R && map[r1 + 1][c] != WALL) {
            r1++;
            if(map[r1][c]==END) return new Node(r1,c,moves+1,"Still");
        }

        if(r1+1<R)
            q.add(new Node(r1,c,moves+1,DOWN));
        while(r2-1>=0 && map[r2 - 1][c] != WALL){
            r2--;
            if(map[r2][c]==END) return new Node(r2,c,moves+1,"Still");
        }
        if(r2-1>=0)
            q.add(new Node(r2, c , moves+1, UP));
        return null;

    }
    private Node moveRightLeft(Queue<Node> q,int r, int c, int moves){
        int c1=c;
        int c2 = c;
        while(c1+1<C && map[r][c1+1]!=WALL){
            c1++;
            if(map[r][c1]==END) return new Node(r,c1,moves+1,"Still");
        }
        if((c1+1<C) || map[r][c1]==END)
            q.add(new Node(r, c1, moves + 1, RIGHT));
        while(c2-1>=0 && map[r][c2-1]!=WALL){
            c2--;
            if(map[r][c2]==END) return new Node(r,c2,moves+1,"Still");
        }
        if(c2-1>=0)
            q.add(new Node(r, c2, moves + 1, LEFT));
        return null;
    }
}