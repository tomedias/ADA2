import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String STUCK = "Stuck";

    private static final String STILL = "still";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = in.readLine().split(" ");
        int R = Integer.parseInt(inputs[0]);
        int C = Integer.parseInt(inputs[1]);
        int T = Integer.parseInt(inputs[2]);
        char[][] map = new char[R][C];
        for(int i = 0;i<R;i++){
            String line = in.readLine();
            map[i] = line.toCharArray();
        }
        Dream dream = new Dream(map, R, C);
        for(int i=0;i<T;i++) {
            String[] pos = in.readLine().split(" ");
            int r = Integer.parseInt(pos[0])-1;
            int c = Integer.parseInt(pos[1])-1;
            int sol = dream.solve(new Node(r,c,0,STILL));
            System.out.println(sol==-1 ? STUCK : sol);
        }
    }
}



