package edu.nyu.pqs.graph;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.graph.Graph.Edge;

public class GraphTest {

  @Test
  public final void testIterator() {
    Graph<String> graph = new Graph<String>(); 
    graph.addNode("MaryEileen", "student");
    graph.addNode("Dr. Scott", "professor");
    graph.addNode("Tavi", "student");
    graph.addNode("Kelly", "student");
    graph.addNode("Dr. Myra", "professor");
    graph.connect("Dr. Scott", "MaryEileen", "student teacher");
    graph.connect("Dr. Scott", "Tavi", "student teacher");
    graph.connect("Dr. Myra", "Kelly", "student teacher");
    graph.setStartingNode(graph.getNode("MaryEileen"));
    assertTrue(graph.iterator().next() == "student");
    assertTrue(graph.iterator().hasNext());
    graph.setStartingNode(graph.getNode("Dr. Myra"));
    assertTrue(graph.iterator().next() == "professor");
  }

  @Test
  public final void testGraph() {
    Graph<String> graph = new Graph<String>();
    graph.addNode("MaryEileen", "student");
  }

  @Test
  public final void testHasKey() {
    Graph<String> graph = new Graph<String>();
    graph.addNode("A", "Roger");
    graph.addNode("B", "Harmony");
    graph.connect("A", "B", "best friends");
    assertFalse(graph.hasKey("Roger"));
    assertFalse(graph.hasKey("C"));
    assertTrue(graph.hasKey("A"));
  }
  
  @Test
  public final void testAddNode() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("A", 15);
    assertEquals(graph.getNumNodes(), 1);
    assertTrue(graph.getNode("A") != null);
    assertFalse(graph.addNode("A", 193));
    graph.addNode("B", 25);
    assertTrue(graph.getNode("B") != null);
    assertFalse(graph.addNode("B", 22));
    assertEquals(graph.getNumNodes(), 2);
    graph.addNode("B", 19);
    assertEquals(graph.getNumNodes(), 2);
    assertTrue(graph.addNode("F", 192));
    assertEquals(graph.getNumNodes(), 3);
    assertTrue(graph.hasNode("A"));
    assertTrue(graph.hasNode("B"));
  }
  
  @Test
  public final void testHasNode() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("A", 15);
    graph.addNode("B", 25);
    assertTrue(graph.hasNode("A"));
    assertEquals(graph.getNode("A").getLabel(), "A");
    assertFalse(graph.hasNode("C"));
    assertTrue(graph.hasNode("B"));
  }
  
  @Test
  public final void testRemoveNode() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("PL", 99);
    graph.addNode("MQ", 65);
    graph.addNode("FG", 0);
    graph.connect("PL", "FG", "edge1");
    graph.connect("FG", "MQ", "edge2");
    assertEquals(graph.getNumNodes(), 3);
    graph.removeNode("PL");
    assertEquals(graph.getNumNodes(), 2);
  }

  @Test
  public final void testConnect() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("B", 33);
    graph.addNode("P", 19);
    graph.addNode("M", 101);
    assertEquals(graph.getNumNodes(), 3);
    assertEquals(graph.getNode("B").getEdgeNeighbors().size(), 0);
    graph.connect("B", "P", "B to P");
    assertEquals(graph.getNode("B").getEdgeNeighbors().size(), 1);
    assertEquals(graph.getNode("P").getEdgeNeighbors().size(), 1);
    assertEquals(graph.getNode("B").getNodeNeighbors().size(), 1);
    assertEquals(graph.getNode("P").getNodeNeighbors().size(), 1);
    assertTrue(graph.getNode("P").getNodeNeighbors().contains(graph.getNode("B")));
    assertTrue(graph.getNode("B").getNodeNeighbors().contains(graph.getNode("P")));
    graph.connect("B", "M", "B to M");
    assertFalse(graph.getNode("P").getNodeNeighbors().contains(graph.getNode("M")));
    
  }

  @Test
  public final void testGetAdjacentNodes() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("PL", 99);
    graph.addNode("MQ", 65);
    graph.addNode("FG", 0);
    graph.connect("PL", "FG", "edge1");
    graph.connect("FG", "MQ", "edge2");
  }
  
  @Test
  public final void testCheckConnectivity() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("L", 10000);
    graph.addNode("M", 101);
    graph.connect("M", "L", "M to L");
    assertEquals(graph.getNode("M").getEdgeNeighbors().size(), 1);
  }

  @Test
  public final void testGetEdge() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("Kelly", 100);
    graph.addNode("Rachel", 65);
    graph.connect("Kelly", "Rachel", "range");
    assertEquals(graph.getEdge("Kelly", "Rachel").getLabeL(), "range");
  }

  @Test
  public final void testGetNumEdges() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("Kelly", 100);
    graph.addNode("Rachel", 65);
  }
  @Test
  public final void testToString() {
    Graph<Integer> graph = new Graph<Integer>();
    graph.addNode("B", 100);
    graph.addNode("P", 99);
    graph.addNode("M", 101);
    graph.connect("B", "P", "B to P");
    graph.connect("P", "M", "P to M");
    StringBuilder expected = new StringBuilder();
    expected.append("P(99):B(100)M(101)").append("\n").
      append("B(100):P(99)").append("\n").
      append("M(101):P(99)").append("\n");
    assertEquals(expected.toString(), graph.toString());
  }

}
