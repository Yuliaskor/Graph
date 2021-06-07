public class Edge<T> implements Comparable<Edge<T>> {
    private final T src;
    private final T dst;
    private final int weight;

    public Edge(T src, T dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public T getEitherVertex() {
        return src;
    }

    public T getOtherVertex(T vertex) {
        if (vertex == src) {
            return dst;
        } else if (vertex == dst) {
            return src;
        }
        throw new IllegalArgumentException("Wrong vertex!");
    }


    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge arg) {
        if (getWeight() < arg.getWeight()) {
            return -1;
        } else if (getWeight() > arg.getWeight()) {
            return 1;
        }
        return 0;
    }
}
