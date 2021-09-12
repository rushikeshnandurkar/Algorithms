package graphs;

import java.util.*;

public class Graph2 {

    public static class Edge implements Comparable<Edge> {
        private final int src;
        private final int destination;
        private final int weight;

        public Edge(int src, int destination, int weight) {
            this.src = src;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.getWeight() - edge.getWeight();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", destination=" + destination +
                    ", weight=" + weight +
                    '}';
        }

        public int getSrc() {
            return src;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    private final int numOfVertices;
    private final int numOfEdges;
    private final List<Edge> edges;

    public Graph2(int numOfVertices, int numOfEdges, List<Edge> edges) {
        this.numOfVertices = numOfVertices;
        this.numOfEdges = numOfEdges;
        Collections.sort(edges);
        this.edges = edges;
    }
    
    public List<Edge> findMST(boolean findByKruskals) {
        
        if (findByKruskals)
            return this.kruskalsAlgoToFindMST();
        return this.primsAlgoToFindMST();
    }

    private List<Edge> primsAlgoToFindMST() {
        return new ArrayList<>();
    }

    private List<Edge> kruskalsAlgoToFindMST() {
        // The Edges are already sorted in the constructor itself.

        int weightOfMST = 0;
        Set<Edge> MST = new HashSet<>();
        Iterator<Edge> iterator = this.getEdges().iterator();
        while (MST.size() < this.getNumOfVertices()-1 && iterator.hasNext()) {

            Edge nextEdge = iterator.next();
            if (this.isFormingCycle(MST,nextEdge))
                continue;

            MST.add(nextEdge);
            weightOfMST += nextEdge.getWeight();
        }

        System.out.println("The cost of the MST is " + weightOfMST);
        return new ArrayList<>(MST);
    }

    private boolean isFormingCycle (Collection<Edge> edges, Edge newEdge) {
        Set<Integer> includedVertices = new HashSet<>();
        List<Edge> edgesList = new ArrayList<>(edges);
        for (Edge edge: edgesList) {
            includedVertices.add(edge.getSrc());
            includedVertices.add(edge.getDestination());
        }

        return (includedVertices.contains(newEdge.getSrc()) &&
                includedVertices.contains(newEdge.getDestination()));
    }

    // getters
    public int getNumOfVertices() {
        return numOfVertices;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
