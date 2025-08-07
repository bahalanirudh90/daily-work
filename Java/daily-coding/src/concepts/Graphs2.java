package concepts;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs2 {
    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i< graph.length ; i++) {
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
    static void BFS(ArrayList<Edge> graph[]){
        boolean[] vis = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                BFSUTIL(graph,vis);
            }
        }
    }
    static void BFSUTIL(ArrayList<Edge>[] graph, boolean[] vis){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); //source = 0

        while(!queue.isEmpty()){
            int current = queue.remove();
            if(!vis[current]){
                System.out.print(current+" ");
                vis[current] = true;

                for(int i = 0; i < graph[current].size(); i++){
                    Edge e = graph[current].get(i);
                    queue.add(e.dest);
                }
            }
        }

    }
    public static void DFS(ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            DFSUTIL(graph,i,vis);
        }
    }
    static void DFSUTIL(ArrayList<Edge> graph[],int curr,boolean vis[]){
        //visit
        System.out.print(curr+" ");
        vis[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                DFSUTIL(graph, e.dest, vis);
            }
        }
    }
    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        BFS(graph);
        //DFS(graph,0,new boolean[V]);

    }

}



