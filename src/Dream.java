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
    private boolean[][] processed;
    public Dream(char[][] map, int R, int C) {
        this.map = map;
        this.R = R;
        this.C = C;
        this.processed = new boolean[R][C];
    }

    public int solve(Node root){
        this.processed = new boolean[R][C];
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.add(root);
        int i =0;
        while(!q1.isEmpty()){
            i++;
            do{
                Node cur = q1.poll();
                int r = cur.getR();
                int c = cur.getC();
                if(processed[r][c]) continue;
                processed[r][c] = true;
                String state = cur.getState();
                Node n = move(q2,cur,state);
                if(n!=null) return i;
            }while(!q1.isEmpty());
            q1 = q2;
            q2 = new LinkedList<>();
        }
        return -1;
    }

    public Node move(Queue<Node> q,Node cur ,String state) {
        if (state.equals(RIGHT) || state.equals(LEFT)){
            return moveUpDown(q,cur);
        } else if (state.equals(UP) || state.equals(DOWN)) {
            return moveRightLeft(q,cur);
        }else{
            return moveRightLeft(q,cur)==null ? moveUpDown(q,cur) : moveRightLeft(q,cur);
        }
    }
    private Node moveUpDown(Queue<Node> q,Node cur){
        int r = cur.getR();
        int c = cur.getC();
        int r1 = r;
        int r2=r;
        while (r1 + 1 < R && map[r1 + 1][c] != WALL) {
            r1++;
            if(map[r1][c]==END) return new Node(r1,c,DOWN);
        }
        if(r1+1<R && !processed[r1][c]){
            q.add(new Node(r1,c,DOWN));
        }
        while(r2-1>=0 && map[r2 - 1][c] != WALL){
            r2--;
            if(map[r2][c]==END) return new Node(r2,c,UP);
        }
        if(r2-1>=0 && !processed[r2][c]){
            q.add(new Node(r2, c ,  UP));
        }
        return null;
    }
    private Node moveRightLeft(Queue<Node> q,Node cur){
        int r = cur.getR();
        int c = cur.getC();
        int c1=c;
        int c2 = c;
        while(c1+1<C && map[r][c1+1]!=WALL){
            c1++;
            if(map[r][c1]==END) return new Node(r,c1,RIGHT);
        }
        if((c1+1<C) && !processed[r][c1]){
            q.add(new Node(r, c1, RIGHT));
        }
        while(c2-1>=0 && map[r][c2-1]!=WALL){
            c2--;
            if(map[r][c2]==END) return new Node(r,c2,LEFT);
        }
        if(c2-1>=0 && !processed[r][c2]){

            q.add(new Node(r, c2,LEFT));
        }
        return null;
    }
}