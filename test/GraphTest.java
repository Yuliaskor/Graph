import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void addNode() throws ElementAlreadyExistException {
        Graph<Integer> gr = new Graph<>();
        gr.addNode(12);
        gr.addNode(15);
        gr.addNode(2);

        assertEquals(3,gr.nodes.size());
    }

    @Test
    void addSameValueException() throws ElementAlreadyExistException {

        Graph<Integer> gr = new Graph<>();
        gr.addNode(12);
        gr.addNode(15);
        gr.addNode(2);
        assertThrows(ElementAlreadyExistException.class, () -> gr.addNode(2));
        assertEquals(3,gr.nodes.size());
    }


    @Test
    void addEdge() throws ElementAlreadyExistException {
        Graph<Integer> gr = new Graph<>();
        gr.addNode(12);
        gr.addNode(15);
        gr.addNode(2);
        gr.addEdge(12,15,4);
        assertEquals(4, gr.findNodeByValue(12).edge.weight);
       // assertEquals(1, gr.findNodeByValue(15).neighbors.size());
       // assertEquals(0, gr.findNodeByValue(2).neighbors.size());
    }

    @Test
    void SSSP() throws ElementAlreadyExistException {
        Graph<String> gr = new Graph<>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        gr.addNode("e");
        gr.addNode("f");
        gr.addNode("g");
        gr.addNode("h");
        gr.addEdge("a","b",2);
        gr.addEdge("a","c",5);
        gr.addEdge("c","f",6);
        gr.addEdge("f","g",7);
        gr.addEdge("g","h",1);
        gr.addEdge("e","h",2);
        gr.addEdge("b","e",4);
        gr.addEdge("b","d",3);
        gr.addEdge("d","c",5);
        gr.addEdge("d","f",1);
        gr.addEdge("d","e",3);
        gr.addEdge("e","f",4);
        gr.addEdge("e","g",8);

        assertEquals(9, gr.SSSP("a","h"));
        // assertEquals(0, gr.findNodeByValue(2).neighbors.size());
    }
}