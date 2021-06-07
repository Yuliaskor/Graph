import java.util.NoSuchElementException;

public interface GraphInterface <T> {
    void addNode(T value) throws ElementAlreadyExistException;
    void addEdge(T src, T dst, int weight) throws NoSuchElementException;
}
