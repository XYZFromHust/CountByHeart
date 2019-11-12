package exp1_MST;
import java.util.Comparator;
import java.util.List;

public class Kruskal extends MST{
//    构造函数
    public Kruskal(Graph graph) {
        super(graph);
    }
    @Override
    public void getMST(){
        List<Edge> edges = graph.getEdges();
//       根据边的权重自小到大排序 -----O(nlogn)(n = |V|)
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost-o2.cost;
            }
        });
        long s = System.nanoTime();

//      获得UF数据结构
        UF uf = new UF(graph.getV());
//      当边集为空 或者mst的大小为 n - 1 时停止
        while (!edges.isEmpty() && mst.size() < graph.getV() - 1){
//            获得最小边
            Edge edge = edges.remove(0);
            int u = edge.u;
            int v = edge.v;
//          获得u 、 v的根节点
            int h1 = uf.find(u);
            int h2 = uf.find(v);
            if (h1 != h2){
                uf.union(h1,h2);
//                mst中添加该边，并且增加weight
                mst.add(edge);
                weight+=edge.cost;
            }
        }
        long f = System.nanoTime();
        System.out.println("cost time(ns) :"+(f-s));
    }
}
