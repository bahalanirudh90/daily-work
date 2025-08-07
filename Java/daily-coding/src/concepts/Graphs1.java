package concepts;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs1 {
    static class Edge{
        int src,dest,wt;
        public Edge(int src,int dest,int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }
    static void create(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,4,1));

        graph[3].add(new Edge(3, 1,1));
        graph[3].add(new Edge(3, 4,1));
        graph[3].add(new Edge(3, 5,1));

        graph[4].add(new Edge(4, 2,1));
        graph[4].add(new Edge(4, 3,1));
        graph[4].add(new Edge(4, 5,1));

        graph[5].add(new Edge(5, 3,1));
        graph[5].add(new Edge(5, 4,1));
        graph[5].add(new Edge(5, 6,1));

        graph[6].add(new Edge(6, 5,1));
    }
    static void BFS(ArrayList<Edge>[] graph){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        queue.add(0);

        while(!queue.isEmpty()){
            int current = queue.remove();
            if(!visited[current]){
                System.out.print(current+" ");
                visited[current] = true;

                for(int i=0;i<graph[current].size();i++){
                    Edge e = graph[current].get(i);
                    queue.add(e.dest);
                }
            }
        }
    }
    static void DFS(ArrayList<Edge>[] graph,int current,boolean[] visited){
        System.out.print(current+" ");
        visited[current] = true;
        for(int i=0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(!visited[e.dest]){
                DFS(graph,e.dest,visited);
            }
        }
    }
    static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited){
        if(src==dest){
            return true;
        }
        visited[src] = true;
        for(int i=0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            if(!visited[e.dest]&& hasPath(graph,e.dest,dest,visited)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        create(graph);
        BFS(graph);
        System.out.println();
        DFS(graph,0,new boolean[V]);
        System.out.println();
        System.out.println(hasPath(graph, 0, 5, new boolean[V]));
    }
}

