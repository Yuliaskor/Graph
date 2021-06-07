import java.util.*;

class Graph<T extends Comparable<T>> {
    private final int countValue;
    private int countEdge;

    private final ArrayList<Edge<T>> edges = new ArrayList<>();         //ArrayList containing all the edges ( src, dst, weight)
    private final ArrayList<Node<T>> nodes = new ArrayList<>();         //ArrayList containing all the nodes ( src, dst, weight)

    private final ArrayList<ArrayList<T>> fathers = new ArrayList<>();  //Auxiliary array for MinimumSpanningTree


    public Graph(int countValue) {
        this.countValue = countValue;
        this.countEdge = 0;
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    public int getNumberOfVertices() {
        return countValue;
    }

    private void addEdge(Edge<T> edge) {
        edges.add(edge);
        countEdge++;
    }

    public void addEdge(T src, T dst, int weight)  {
        if (src == null || dst == null) {
            throw new NoSuchElementException();
        }
        addEdge(new Edge<>(src, dst, weight));
    }

    private void makeNodes() {
        for (Edge<T> edge : edges) {

            T src = edge.getEitherVertex();
            T dst = edge.getOtherVertex(src);
            if (findPositionInNodes(src) == -1) {
                nodes.add(new Node<>(src, new Neighbors<>(edge.getWeight(), dst)));
            }
            if (findPositionInNodes(dst) == -1) {
                nodes.add(new Node<>(dst, new Neighbors<>(edge.getWeight(), src)));
            }

            addNodesToArray(edge, src, dst);
            addNodesToArray(edge, dst, src);
        }
    }

    private void addNodesToArray(Edge<T> edge, T src, T dst) {
        if (findPositionInNodes(src) != -1) {
            boolean isWrite = false;
            for (int i = 0; i < nodes.get(findPositionInNodes(src)).neighbors.size(); i++) {
                if (Objects.equals(nodes.get(findPositionInNodes(src)).neighbors.get(i).value, dst)) {
                    isWrite = true;
                }
            }
            if (!isWrite) {
                nodes.get(findPositionInNodes(src)).neighbors.add(new Neighbors<>(edge.getWeight(), dst));
            }
        }
    }

    private void unite(T x, T y) {
        int positionX = findInFather(x);
        int positionY = findInFather(y);
        if (positionX == -1) {
            fathers.get(positionY).add(x);
        }
        if (positionY == -1) {
            fathers.get(positionX).add(y);
        } else if ((positionX < positionY) && (positionX != -1)) {
            ArrayList<T> addListY = new ArrayList<>(fathers.get(positionY));
            fathers.get(positionX).addAll(addListY);
            fathers.get(positionY).clear();
        } else if (positionX > positionY) {
            ArrayList<T> addListX = new ArrayList<>(fathers.get(positionX));
            fathers.get(positionY).addAll(addListX);
            fathers.get(positionX).clear();
        }
    }

    public int minimumSpanningTree() {
        int mst_weight = 0;
        int mst_edges = 0;
        int mst_ni = 0;

        edges.sort(Comparator.comparingInt(Edge::getWeight));
        while ((mst_edges < countValue - 1) && (mst_ni < countEdge)) {
            T src = edges.get(mst_ni).getEitherVertex();
            T dst = edges.get(mst_ni).getOtherVertex(src);
            int weight = edges.get(mst_ni).getWeight();

            if ((findInFather(src) == findInFather(dst)) && (findInFather(src) == -1)) {
                ArrayList<T> addList = new ArrayList<>();
                addList.add(src);
                addList.add(dst);
                fathers.add(addList);
                mst_weight += weight;

            }

            if ((findInFather(src) != findInFather(dst))) {
                unite(src, dst);
                mst_weight += weight;
                mst_edges++;
            }
            mst_ni++;
        }
        return mst_weight;
    }

    private int singleSourceShortestPath(T start, T end, PriorityQueue<Node<T>> pq ) {
        int isCheckedSum = 0;

        nodes.get(findPositionInNodes(start)).prise = 0;
        pq.add(new Node<>(start, 0));
        while (isCheckedSum != countValue) {
            T u = pq.remove().element;
            nodes.get(findPositionInNodes(u)).isChecked = true;
            isCheckedSum++;

            e_Neighbours(u, pq);
        }
        return nodes.get(findPositionInNodes(end)).prise;
    }

    public int singleSourceShortestPaths(T start, T end) {
        makeNodes();
        nodesUpdate();
        PriorityQueue<Node<T>> pq; //Auxiliary priorityQueue for singleSourceShortestPaths
        pq = new PriorityQueue<>(countValue, new Node<>());

        if ((findPositionInNodes(start) == -1) || (findPositionInNodes(end) == -1)) {
            throw new NoSuchElementException();
        }

        return singleSourceShortestPath(start, end, pq);
    }

    private void nodesUpdate(){
        for (Node<T> node : nodes) {
            node.isChecked = false;
            node.prise = Integer.MAX_VALUE;
        }
    }

    private void e_Neighbours(T element, PriorityQueue<Node<T>> pq) {
        int position = findPositionInNodes(element);
        int edgeDistance;

        int newDistance ;

        for (int i = 0; i < nodes.get(position).neighbors.size(); i++) {
            Neighbors<T> v = nodes.get(position).neighbors.get(i);
            if (!nodes.get(findPositionInNodes(v.value)).isChecked) {
                edgeDistance = v.weight;
                newDistance = nodes.get(position).prise + edgeDistance;

                if (newDistance < nodes.get(findPositionInNodes(v.value)).prise) {
                    nodes.get(findPositionInNodes(v.value)).prise = newDistance;
                }
                pq.add(new Node<>(v.value, nodes.get(findPositionInNodes(v.value)).prise));
            }
        }
    }

    private int findPositionInNodes(T codeIsIn) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).element == codeIsIn) {
                return i;
            }
        }
        return -1;
    }

    private int findInFather(T value) {
        for (int i = 0; i < fathers.size(); i++) {
            for (int j = 0; j < fathers.get(i).size(); j++) {
                if (fathers.get(i).get(j) == value) {
                    return i;
                }
            }
        }
        return -1;
    }
}




















