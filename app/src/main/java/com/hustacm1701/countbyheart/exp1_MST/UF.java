package exp1_MST;

public class UF {
    private int[] parent;
    private int[] size;
//  构造并初始化
    public UF(int V) {
        parent = new int[V+1];
        size = new int[V+1];
        for (int i = 1;i<=V;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
//  find
    public int find(int v){
        int save = v;
        while (parent[v]!=v){
            parent[v] = parent[parent[v]]; // 折半路径压缩
            v = parent[v];
        }
        // 全路径压缩
        int t;
        for (int i = save;i!=parent[i];i=t){
            t = parent[i];
            parent[i] = v;
        }
        return v;
    }
//    union 两个 head
    public void union(int h1,int h2){
        if (size[h1] > size[h2]){
            parent[h2] = h1;
            size[h1] += size[h2];
        }else {
            parent[h1] = h2;
            size[h2]+=size[h1];
        }
    }
}
