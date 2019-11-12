package exp1_MST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new File("E:\\Java\\AlgorithmExperiment\\src\\exp1_MST\\1.mem"));
//        int V = scanner.nextInt();
        int V = 500;
//        int E = scanner.nextInt();
        List<Edge> edges = new ArrayList<>();
        Random random = new Random();
        for (int i = 1;i<=V;i++){
            for (int j = 1;j<=V;j++){
                edges.add(new Edge(i,j,random.nextInt(1000)+1));
            }
        }
//        for (int i = 0;i<E;i++){
//            edges.add(new Edge(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
//        }
        Graph graph = new Graph(V);
        graph.addEdge(edges);
//      test kruskal
        long s = System.nanoTime();
        Kruskal kruskal = new Kruskal(graph);
        kruskal.getMST();
        long f = System.nanoTime();
        System.out.println("kruskal weight :"+kruskal.getWeight());
//        System.out.println("mst :");
//        for (Edge edge:kruskal.getMst()){
//            System.out.println(edge);
//        }
        System.out.println("cost time(ns) :"+(f-s)+"\n--------------------");
//      test Prim
        s = System.nanoTime();
        Prim prim = new Prim(graph);
        prim.getMST();
        f = System.nanoTime();
        System.out.println("Prim weight :"+prim.getWeight());
//        System.out.println("mst :");
//        for (Edge edge:prim.getMst()){
//            System.out.println(edge);
//        }
        System.out.println("cost time(ns) :"+(f-s));
    }
}
