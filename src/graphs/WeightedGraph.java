package graphs;

import java.util.*;


public class WeightedGraph extends Graph {

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
}