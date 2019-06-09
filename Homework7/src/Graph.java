import java.util.*;

public class Graph {

    private final List<Vertex> vertexes = new ArrayList<>();
    private boolean[][] adjMat;

    private int size;

    public Graph(int maxVertexCount) {
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexes.add(new Vertex(label));
        size++;
    }

    public void addEdges(String start, String second, String... others) {
        addEdge(start, second);

        for (String other : others) {
            addEdge(start, other);
        }
    }

    public void addEdge(String start, String finish) {
        int startIndex  = indexOf(start);
        int finishIndex = indexOf(finish);

        if ( startIndex == -1 || finishIndex == -1 ) {
            throw new IllegalArgumentException("Invalid label for vertex");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;

    }

    private int indexOf(String label) {
        for (int i = 0; i < size; i++) {
            if ( vertexes.get(i).getLabel().equals(label) ) {
                return i;
            }
        }

        return -1;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(vertexes.get(i));
            for (int j = 0; j < size; j++) {
                if ( adjMat[i][j] ) {
                    System.out.print(" -> " + vertexes.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. breadth-first search, BFS
     * @param startLabel
     */
    public void bfs(String startLabel) {
        if ( indexOf(startLabel) == -1 ) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Vertex vertex = vertexes.get(indexOf(startLabel));

        Queue<Vertex> queue = new LinkedList<>();
        visitVertex(queue, vertex);

        while ( !queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            }
            else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    /**
     * англ. Depth-first search, DFS
     * @param startLabel
     */
    public void dfs(String startLabel) {
        if ( indexOf(startLabel) == -1 ) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }
        Vertex vertex = vertexes.get(indexOf(startLabel));

        Stack<Vertex> stack = new Stack<>();

        visitVertex(stack, vertex);

        while ( !stack.isEmpty() ) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    private void resetVertexState() {
        for (int i = 0; i < size; i++) {
            vertexes.get(i).setWasVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexes.indexOf(peek);
        for (int i = 0; i < size; i++) {
            if (adjMat[peekIndex][i] && !vertexes.get(i).isWasVisited()) {
                return vertexes.get(i);
            }
        }

        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setWasVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        displayVertex(vertex);
        queue.add(vertex);
        vertex.setWasVisited(true);
    }

    private void displayVertex(Vertex vertex) {
        System.out.println(vertex);
    }

    //Проверяем две вершины
    public Stack<String> findShortPathViaBfs(String startLabel, String finishLabel) {
        int startIndex  = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }
        if (finishIndex == -1) {
            throw new IllegalArgumentException("Invalid finishLabel: " + finishLabel);
        }

        Queue<Vertex> queue = new ArrayDeque<>();

        Vertex vertex = vertexes.get(startIndex);
        visitVertex(queue, vertex);

        while ( !queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            }
            else {
                visitVertex(queue, vertex);
                vertex.setPreviousVertex(queue.peek()); //устанавливаем предыдущую вершину
                if (vertex.getLabel().equals(finishLabel)) {
                    return buildPath(vertex);
                }
            }
        }

        resetVertexState();
        return null;
    }

    private Stack<String> buildPath(Vertex vertex) {
        Stack<String> stack = new Stack<>();
        Vertex current = vertex;
        while (current != null) {
            stack.push(current.getLabel());
            current = current.getPreviousVertex();
        }

        return stack;
    }
}
