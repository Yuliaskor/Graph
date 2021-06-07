import java.util.ArrayList;
import java.util.Comparator;

public class Node<T> implements Comparator<Node<T>> {

    public T element;
    public final ArrayList<Neighbors<T>> neighbors = new ArrayList<>();
    public int prise;
    public boolean isChecked;

    public Node(T element, Neighbors<T> neighbors) {
        this.element = element;
        this.neighbors.add(neighbors);
        prise = Integer.MAX_VALUE;
        this.isChecked = false;

    }

    public Node(T element, int prise) {
        this.element = element;
        this.prise = prise;
    }

    public Node() {

    }

    @Override
    public int compare(Node<T> o1, Node<T> o2) {
        return Integer.compare(o1.prise, o2.prise);
    }
}

