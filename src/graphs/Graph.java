package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Graph {

    private int numOfNodes;
    private int[][] adjMatrix;

    public Graph() {

        System.out.print("How many nodes are there in the graph: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            this.numOfNodes = Integer.parseInt(reader.readLine());
            this.adjMatrix = new int[this.numOfNodes][this.numOfNodes];

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter edges of the graph. One edge per line, a space seperating two vertices of the edge...");
        try {
            String line;

            while (true) {
                line = reader.readLine();
                if (line.length() == 0)
                    break;

                String[] strVertices = line.split(" ", 2);
                int vertexA = Integer.parseInt(strVertices[0]);
                int vertexB = Integer.parseInt(strVertices[1]);

                this.adjMatrix[vertexA][vertexB] = 1;
                this.adjMatrix[vertexB][vertexA] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Graph(int numOfNodes, int[][] adjMatrix) {
        this.numOfNodes = numOfNodes;
        this.adjMatrix = adjMatrix;
    }

    public List<Integer> getNeighbors(int vertex) {


        List<Integer> retList = new ArrayList<>();
        for (int i = 0; i < this.numOfNodes; i++) {

            if (this.adjMatrix[vertex][i] > 0) {
                retList.add(i);
            }
        }
        return retList;
    }

    public void BFSTraversal() {

        this.BFSTraversal(1);
    }

    public void DFSTraversal() {

        this.DFSTraversal(1);
    }

    public void BFSTraversal(int startingVertex) {

        Queue<Integer> queue = new LinkedList<>();
        // decrement startingVertex by 1 to be compatible with 0 based indexing
        startingVertex--;
        Set<Integer> visitedVertices =  new HashSet<>();

        while (visitedVertices.size() < this.numOfNodes) {

            if (!visitedVertices.contains(startingVertex)) {
                System.out.print(startingVertex + " ");
                visitedVertices.add(startingVertex);
                queue.addAll(this.getNeighbors(startingVertex));
            }
            startingVertex = queue.poll();
        }
        System.out.println();
    }

    public void DFSTraversal(int startingVertex) {

        Stack<Integer> stack = new Stack<>();
        // decrement startingVertex by 1 to be compatible with 0 based indexing
        startingVertex--;
        Set<Integer> visitedVertices =  new HashSet<>();

        while (visitedVertices.size() < this.numOfNodes) {

            if (!visitedVertices.contains(startingVertex)) {
                System.out.print(startingVertex + " ");
                visitedVertices.add(startingVertex);
                stack.addAll(this.getNeighbors(startingVertex));

            }
            startingVertex = stack.pop();
        }
        System.out.println();
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public boolean containsEdgeBetweenVertices (int u, int v) {

        int[][] adjMatrix = this.getAdjMatrix();
        return adjMatrix[u][v] > 0;
    }
}
