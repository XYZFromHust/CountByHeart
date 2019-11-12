package exp1_MST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
//  记录节点的个数
    private int V;
//  记录边的个数
    private int E=0;
//  记录每个点的边集 index from 1 to V
    private Set<Edge>[] sets;
//  记录边
    private List<Edge> edges = new ArrayList<>();
//  构造图
    public Graph(int V){
        this.V = V;
        sets = new HashSet[V+1];
        for (int i = 1;i<=V;i++){
            sets[i] = new HashSet<>();
        }
    }
// 添加Edge
    public void addEdge(Edge edge){
        sets[edge.u].add(edge);
        sets[edge.v].add(edge);
        edges.add(edge);
        E++;
    }
//  通过list添加edge
    public void addEdge(List<Edge> edges){
        for (Edge edge:edges){
            addEdge(edge);
        }
    }
// 返回点v的临边
    public Set<Edge> adj(int v){
        return sets[v];
    }
//  返回图中所有的边
    public List<Edge> getEdges(){
        return edges;
    }
//  获得点的个数
    public int getV() {
        return V;
    }
//  获得边的个数
    public int getE() {
        return E;
    }
}
