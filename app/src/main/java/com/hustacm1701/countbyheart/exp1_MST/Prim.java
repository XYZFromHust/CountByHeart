package exp1_MST;

public class Prim extends MST{
    protected Prim(Graph graph) {
        super(graph);
    }
    private boolean[] marked = new boolean[graph.getV()+1];
    private IndexMinPQ<Integer> pq = new IndexMinPQ<>(graph.getV()+1);
    private int distTo[] = new int[graph.getV()+1];
    private Edge[] edgeTo = new Edge[graph.getV()+1];
    @Override
    public void getMST() {
        for (int i = 1;i<=graph.getV();i++){
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[1] = 0;
        pq.insert(1,0);

        while (!pq.isEmpty()){
            int v = pq.delMin();
            weight+=distTo[v];
            mst.add(edgeTo[v]);

            marked[v] = true;
            for (Edge e:graph.adj(v)){
                int w = e.getAnother(v);
                if (marked[w])
                    continue;
                if (e.cost < distTo[w]){
                    edgeTo[w] = e;
                    distTo[w] = e.cost;
                    if (pq.contains(w)) pq.changeKey(w,distTo[w]);
                    else pq.insert(w,distTo[w]);
                }
            }

        }
    }
}
