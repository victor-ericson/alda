//Victor Ericson vier1798
package alda.graph;

import java.util.*;

public class MyUndirectedGraph<T> implements UndirectedGraph<T> {

    private final Map<T, Map<T, Integer>> nodes;

    public MyUndirectedGraph() {
        nodes = new HashMap<>();
    }

    @Override
    public int getNumberOfNodes() {
        return nodes.size();
    }

    @Override
    public int getNumberOfEdges() {
        return (int) nodes.values().stream().mapToLong(m -> m.values().size()).sum() / 2;
    }

    @Override
    public boolean add(T newNode) {
        return nodes.putIfAbsent(newNode, new HashMap<>()) == null;
    }

    @Override
    public boolean connect(T node1, T node2, int cost) {
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2) || cost <= 0) {
            return false;
        }

        Map<T, Integer> node1Edges = nodes.get(node1);
        Map<T, Integer> node2Edges = nodes.get(node2);

        node1Edges.put(node2, cost);
        nodes.put(node1, node1Edges);

        node2Edges.put(node1, cost);
        nodes.put(node2, node2Edges);

        return true;
    }

    @Override
    public boolean isConnected(T node1, T node2) {
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
            return false;
        }
        return nodes.get(node1).containsKey(node2);
    }

    @Override
    public int getCost(T node1, T node2) {
        if (!isConnected(node1, node2)) {
            return -1;
        }
        return nodes.get(node1).get(node2);
    }

    @Override
    public List<T> depthFirstSearch(T start, T end) {
        Set<T> visited = new HashSet<>();
        Map<T, T> parentMap = new HashMap<>();
        dfsHelper(start, visited, parentMap);
        return getPath(start, end, parentMap);
    }

    @Override
    public List<T> breadthFirstSearch(T start, T end) {
        Map<T, T> parentMap = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            T current = queue.poll();
            if (current.equals(end)) {
                return getPath(start, end, parentMap);
            }
            for (T neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public UndirectedGraph<T> minimumSpanningTree() {
        UndirectedGraph<T> minimumSpanningTree = new MyUndirectedGraph<>();
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>();
        Set<T> visited = new HashSet<>();
        T startNode = nodes.keySet().iterator().next();
        visited.add(startNode);
        for (T neighbor : getNeighbors(startNode)) {
            queue.offer(new Edge<>(startNode, neighbor, getCost(startNode, neighbor)));
        }
        while (!queue.isEmpty()) {
            Edge<T> edge = queue.poll();
            if (visited.contains(edge.to)) {
                continue;
            }
            minimumSpanningTree.add(edge.from);
            minimumSpanningTree.add(edge.to);
            minimumSpanningTree.connect(edge.from, edge.to, edge.weight);
            visited.add(edge.to);
            for (T neighbor : getNeighbors(edge.to)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(new Edge<>(edge.to, neighbor, getCost(edge.to, neighbor)));
                }
            }
        }
        return minimumSpanningTree;
    }

    private void dfsHelper(T current, Set<T> visited, Map<T, T> parentMap) {
        visited.add(current);
        for (T neighbor : nodes.get(current).keySet()) {
            if (!visited.contains(neighbor)) {
                parentMap.put(neighbor, current);
                dfsHelper(neighbor, visited, parentMap);
            }
        }
    }

    private List<T> getPath(T start, T end, Map<T, T> parentMap) {
        List<T> path = new ArrayList<>();
        T current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private List<T> getNeighbors(T node) {
        List<T> neighbors = new ArrayList<>();
        for (T n : nodes.keySet()) {
            if (isConnected(node, n)) {
                neighbors.add(n);
            }
        }
        return neighbors;
    }

    private static class Edge<T> implements Comparable<Edge<T>> {
        T from;
        T to;
        int weight;

        public Edge(T from, T to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge<T> other) {
            return Integer.compare(weight, other.weight);
        }
    }
}
