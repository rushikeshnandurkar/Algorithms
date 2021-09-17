package graphs;

import java.util.*;


public class WeightedGraph extends Graph {
    public final static int INFINITY = 9999;

    public WeightedGraph(int numOfNodes, int[][] adjMatrix) {

        super(numOfNodes, adjMatrix);
    }

    public int getDistanceBetweenVertices(int u, int v) {

        int[][] adjMatrix = this.getAdjMatrix();
        if (this.containsEdgeBetweenVertices(u, v)) {
            return adjMatrix[u][v];
        }

        return -1;
    }


    // calculates the minimum distance from source to every vertex using Dijkstra Algo
    // Time complexity: O(v^2) | v = number of vertices
    // Time complexity can be reduced if graph is represented as an adjacency list instead of adjacency matrix.
    public int[] findDistanceFromSource(int sourceVertex) {

        int[] distances = new int[this.getNumOfNodes()];

        //initialize all distances to infinity but 0 for source vertex
        for (int i = 0; i < this.getNumOfNodes(); i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[sourceVertex] = 0;

        // Declaring sptSet (Shortest Path Tree Set)
        Set<Integer> sptSet = new HashSet<>();

        while (sptSet.size() < this.getNumOfNodes()) {

            int minDist = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < this.getNumOfNodes(); i++) {

                if (!sptSet.contains(i) && distances[i] < minDist) {
                    minDist = distances[i];
                    u = i;
                }
            }

            sptSet.add(u);
            List<Integer> neighbors = this.getNeighbors(u);

            for (int neighbor : neighbors) {

                if (!sptSet.contains(neighbor) && this.containsEdgeBetweenVertices(u, neighbor)) {
                    if (distances[neighbor] > distances[u] + this.getDistanceBetweenVertices(u, neighbor)) {
                        distances[neighbor] = distances[u] + this.getDistanceBetweenVertices(u, neighbor);
                    }
                }
            }
        }

        return distances;
    }

    // Calculates the shortest distance between every pair of vertices using Floyd Warshall's algorithm
    // Time complexity of the Floyd Warshall's algo: O(V^3)
    public int[][] calculateShortestDistanceBetweenAllPairs() {

        int[][] shortestDistances = new int[this.getNumOfNodes()][this.getNumOfNodes()];

        // initializing shortestDistances array
        for (int i = 0; i < this.getNumOfNodes(); i++) {
            for (int j = 0; j < this.getNumOfNodes(); j++) {

                if (i == j) {
                    shortestDistances[i][j] = 0;
                }else if (!this.containsEdgeBetweenVertices(i, j)) {
                    shortestDistances[i][j] = WeightedGraph.INFINITY;
                } else {
                    shortestDistances[i][j] = this.getDistanceBetweenVertices(i, j);
                }
            }
        }

        // Applying Floyd Warshall's Algo
        for (int k = 0; k < this.getNumOfNodes(); k++) {
            for (int i = 0; i < this.getNumOfNodes(); i++) {
                for (int j = 0; j < this.getNumOfNodes(); j++) {

                    if (shortestDistances[i][k] + shortestDistances[k][j] < shortestDistances[i][j]) {
                         shortestDistances[i][j] = shortestDistances[i][k] + shortestDistances[k][j];
                    }
                }
            }
        }

        return shortestDistances;
    }

    // Implementation of Prims Algorithm
    public int[] primsAlgoToFindMST() {

        int[] parent = new int[this.getNumOfNodes()];
        parent[0] = -1;

        Set<Integer> mstSet = new HashSet<>();
        int[] values = new int[this.getNumOfNodes()];
        for (int i = 0; i < this.getNumOfNodes(); i++) {
            values[i] = Integer.MAX_VALUE;
        }
        values[0] = 0;

        while (mstSet.size() < this.getNumOfNodes()) {

            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < this.getNumOfNodes(); i++) {
                if (!mstSet.contains(i) && values[i] < min) {
                    min = values[i];
                    u = i;
                }
            }
            mstSet.add(u);
            for (int v: this.getNeighbors(u)) {

                if (!mstSet.contains(v) && this.getDistanceBetweenVertices(u, v) < values[v]) {
                    parent[v] = u;
                    values[v] = this.getDistanceBetweenVertices(u, v);
                }
            }
        }
        return parent;
    }

    public void printPrimsMST() {
        int[] parent = this.primsAlgoToFindMST();
        for (int i = 1; i < this.getNumOfNodes(); i++)
            System.out.println(parent[i] + " - " + i + "\t" + this.getDistanceBetweenVertices(i, parent[i]));
    }
}