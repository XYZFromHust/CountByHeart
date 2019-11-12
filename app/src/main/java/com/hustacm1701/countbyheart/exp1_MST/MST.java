package exp1_MST;

import java.util.ArrayList;
import java.util.List;

public abstract class MST {
    protected Graph graph;
    //    用于记录最小生成树
    protected List<Edge> mst = new ArrayList<>();
    //    用于记录最小生成树的weight
    protected int weight = 0;

    protected MST(Graph graph){
        this.graph = graph;
    }

    //    需要不同算法的实现
    public abstract void getMST();

    public List<Edge> getMst() {
        return mst;
    }

    public int getWeight() {
        return weight;
    }
}
