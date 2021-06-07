import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Graph<T extends Comparable<T>> implements GraphInterface<T> {
    public ArrayList<Node> nodes = new ArrayList<>();

    class Node {
        T value;
       Edge edge;
       int prise;
       boolean isChecked;
        public Node(T value, Edge edge) {
            this.value = value;
            this.edge = edge;
            prise = Integer.MAX_VALUE;
            if(edge!=null){
                edge.neighbors = new ArrayList<>();
            }
            isChecked = false;
        }
        public void addEge(Edge edge){
                this.edge = edge;
                edge.neighbors = new ArrayList<>();
        }
    }
    class Edge{
        int weight;
        ArrayList<Node> neighbors;

        public Edge(int weight){
            this.weight= weight;
        }

        public void addNeighbor(Node node) {
            neighbors.add(node);
        }
    }


    public void addNode(T value) throws ElementAlreadyExistException {
        for (Node node : nodes) {
            if (node.value == value) {
                throw new ElementAlreadyExistException();
            }
        }
        nodes.add(new Node(value,null));
    }

    public void addEdge(T src, T dst, int weight) throws NoSuchElementException {
        Node srcNode = findNodeByValue(src);
        Node dstNode = findNodeByValue(dst);
        if (srcNode == null || dstNode == null) {
            throw new NoSuchElementException();
        }
        srcNode.addEge(new Edge(weight));
        dstNode.addEge(new Edge(weight));
        srcNode.edge.addNeighbor(dstNode);
        dstNode.edge.addNeighbor(srcNode);

    }

    public Node findNodeByValue(T value) {
        for (Node node : nodes) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }

    public int findNodePosition(T value){
        for(int i=0; i<nodes.size(); i++){
            if(nodes.get(i).value == value){
                return i;
            }
        }
        return -1;
    }

    public int SSSP(T start, T end){
        int position = findNodePosition(start);
        int prise = 0;
        T minNeighbersValue =nodes.get(position).edge.neighbors.get(0).value;
        for (int i=0; i<nodes.get(position).edge.neighbors.size(); i++){
            if(nodes.get(position).edge.neighbors.get(i).prise > (prise + nodes.get(position).edge.weight) && (!nodes.get(position).isChecked)){
                nodes.get(position).edge.neighbors.get(i).prise = (prise + nodes.get(position).edge.weight );
            }
            if(nodes.get(position).edge.neighbors.get(i).value == end){
                return nodes.get(position).edge.neighbors.get(i).prise;
            }
            if(nodes.get(position).edge.neighbors.get(i).value.compareTo(minNeighbersValue)>0){
                minNeighbersValue =nodes.get(position).edge.neighbors.get(i).value;
            }

        }
        return SSSP(minNeighbersValue,end);
    }
}
