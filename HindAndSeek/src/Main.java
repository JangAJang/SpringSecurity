import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int least = Integer.MAX_VALUE;
    static int sister;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());
        System.out.println(least);
    }

    static void dfs(int subin, int count){
        
    }
}
