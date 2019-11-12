package exp1_MST;

class Edge{
//    边的两个结点
    int u,v;
    int cost;
    public Edge(int u,int v,int cost){
        this.cost = cost;
        this.u = u;
        this.v = v;
    }
    public int getAnother(int one){
        return u==one?v:u;
    }

    @Override
    public String toString() {
        return u+"<--->"+v +" cost : "+cost;
    }
}
