import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void addEdge()  {
        Graph<Integer> gr = new Graph<>(4);
        gr.addEdge(1,2,4);
        gr.addEdge(2,4,5);
        gr.addEdge(4,5,3);
        gr.addEdge(4,3,1);

        assertEquals(4, gr.getNumberOfVertices());
        assertEquals(4,gr.getNumberOfEdges());

    }
    @Test
    void bigGraphMST(){
        Graph<Integer> g = new Graph<>(14);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        System.out.println(g.minimumSpanningTree());
        //Assertions.assertTrue(g.toString().contains("(5) w:4") && g.toString().contains("(2) w:4"));
    }

    @Test
    void minimumSpanningTree() {
        Graph<String> gr = new Graph<>(13);
        gr.addEdge("a", "d", 2);
        gr.addEdge("a", "b", 4);
        gr.addEdge("b", "c", 2);
        gr.addEdge("c", "f", 9);
        gr.addEdge("f", "h", 7);
        gr.addEdge("g", "h", 6);
        gr.addEdge("g", "d", 5);
        gr.addEdge("a", "e", 3);
        gr.addEdge("e", "h", 1);
        gr.addEdge("g", "e", 5);
        gr.addEdge("e", "b", 3);
        gr.addEdge("b", "h", 4);
        gr.addEdge("b", "f", 8);
        assertEquals(23,gr.minimumSpanningTree());
    }

    @Test
    void smallGraphStringMST(){
        Graph<String> gs = new Graph<>(13);
        gs.addEdge("A","B",2);
        gs.addEdge("B","D",2);
        gs.addEdge("C","D",3);
        gs.addEdge("A","D",1);
        gs.addEdge("D","E",8);
        assertEquals(14,gs.minimumSpanningTree());
    }

    @Test
    void minimumSpanningTreeInteger()  {
        Graph<Integer> gr = new Graph<>(6);
        gr.addEdge(2, 3, 1);
        gr.addEdge(6, 5, 1);
        gr.addEdge(2, 5, 2);
        gr.addEdge(7, 2, 2);
        gr.addEdge(1, 2, 4);
        gr.addEdge(4, 3, 6);

        assertEquals(16,gr.minimumSpanningTree());
    }

    @Test
    void addNullValueException() {
        Graph<Integer> gr = new Graph<>(6);
        gr.addEdge(2, 3, 1);
        gr.addEdge(6, 5, 1);
        gr.addEdge(2, 5, 2);
        gr.addEdge(7, 2, 2);
        gr.addEdge(1, 2, 4);
        gr.addEdge(4, 3, 6);
        assertThrows(NoSuchElementException.class, () -> gr.addEdge(null,3,2));
    }

    @Test
    void singleSourceShortestPathsTest() {
        Graph<String> gr = new Graph<>(13);
        gr.addEdge("a", "d", 2);
        gr.addEdge("a", "b", 4);
        gr.addEdge("b", "c", 2);
        gr.addEdge("c", "f", 9);
        gr.addEdge("f", "h", 7);
        gr.addEdge("g", "h", 6);
        gr.addEdge("g", "d", 5);
        gr.addEdge("a", "e", 3);
        gr.addEdge("e", "h", 1);
        gr.addEdge("g", "e", 2);
        gr.addEdge("e", "b", 3);
        gr.addEdge("b", "h", 4);
        gr.addEdge("b", "f", 8);

        assertEquals(5,gr.singleSourceShortestPaths("a","g"));
        assertEquals(4,gr.singleSourceShortestPaths("a","h"));
        assertEquals(6,gr.singleSourceShortestPaths("b","d"));
        assertEquals(8,gr.singleSourceShortestPaths("f","e"));

    }

    @Test
    void findNullValueException(){
        Graph<String> gr = new Graph<>(13);
        gr.addEdge("a", "d", 2);
        gr.addEdge("a", "b", 4);
        gr.addEdge("b", "c", 2);
        gr.addEdge("c", "f", 9);
        gr.addEdge("f", "h", 7);
        gr.addEdge("g", "h", 6);
        gr.addEdge("g", "d", 5);
        gr.addEdge("a", "e", 3);
        gr.addEdge("e", "h", 1);
        gr.addEdge("g", "e", 2);
        gr.addEdge("e", "b", 3);
        gr.addEdge("b", "h", 4);
        gr.addEdge("b", "f", 8);

        assertThrows(NoSuchElementException.class, () -> gr.singleSourceShortestPaths("w","c"));


    }
}