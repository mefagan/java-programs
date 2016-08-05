package edu.nyu.pqs.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author maryeileenfagan
 *
 * @param <T>
 * Class implements a graph interface with node and edge classes.
 * Supports adding and removing nodes, connecting nodes with an edge, iterating
 * over nodes with a depth first search iterator. 
 * Composite predicates are implemented for filtering the DFS iteration.
 * 
 */
public class Graph<T> implements Iterable<T> {
  private Map<String, Node> nodesMap;
  private Node startingNode;
  private int numNodes;
  private int numEdges;
  
  @Override
  public Iterator<T> iterator() {
    return new DFSIterator(startingNode);
  }
  
  /**
   * @param startingNode where DFS search begins
   * Sets starting node for DFS iterator.
   * DFS iterator starts at a chosen node and only iterates over nodes connected to 
   * that starting node.
   */
  public void setStartingNode(Node startingNode) {
    this.startingNode = startingNode;
  }
  
  /**
   *Implements node for graph structure. 
   *Has required value and required label members and holds lists of 
   *adjacent nodes and edges. 
   */
  public class Node {
    private T value;
    private String label;
    private List<Edge> edgeNeighbors;
    private List<Node> nodeNeighbors;
    
    /**
     * @param label identifier of the node
     * @param value what the node holds
     * Constructor for node structure requiring a label and a value.
     * Initializes empty lists for neighboring nodes and edges.
     * Identifier for nodes is unique, but multiple nodes may have the same value.
     */
    Node(String label, T value){
      this.label = label;
      this.value = value;
      edgeNeighbors = Collections.synchronizedList(new ArrayList<Edge>());
      nodeNeighbors = Collections.synchronizedList(new ArrayList<Node>());
    }
    /**
     * @return label
     */
    public String getLabel() {
      return label;
    }
    /**
     * @return value of the node
     */
    public T getValue() {
      return value;
    }
    /**
     * @param value 
     * Sets the value of the node
     */
    public void setValue(T value) {
      this.value = value;
    }
    /**
     * @param label
     * Sets the label or identifier of the node
     */
    public void setLabel(String label) {
      this.label = label;
    }
    /**
     * @return list of edges stemming from the node
     */
    public ArrayList<Edge> getEdgeNeighbors() {
      return new ArrayList<Edge>(edgeNeighbors);
    }
    /**
     * @return list of nodes that neighbor the node through an edge connection
     */
    public ArrayList<Node> getNodeNeighbors() {
      return new ArrayList<Node>(nodeNeighbors);
    }
    /**
     * @param label of the edge connecting two nodes
     * @return true if a neighboring edge is added successfully to a node's 
     * list of neighboring edges
     * @returns false if a neighboring edge is already contained
     * in a node list of neighboring edges and so will not be added
     * Adds an edge to a node's list of neighboring edges only if the
     * node doesn't already have that neighboring edge
     */
    public boolean addEdgeNeighbor(String label) {
      Edge edge = new Edge(label);
      if (edgeNeighbors.contains(edge)) {
        return false;
      }
      edgeNeighbors.add(edge);
      return true;
    }
    public boolean addNodeNeighbor(Node node) {
      if (node == null) {
        throw new NullPointerException("Node can't be null.");
      }
      nodeNeighbors.add(node);
      return true;
    }
    @Override 
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append(label).append("(").append(value).append(")");
      return result.toString();
    }
  }
  public class Edge {
    private Node nodea;
    private Node nodeb;
    private String label;
    Edge(String label) {
      this.label = label;
    }
    public String getLabeL(){
      return label;
    }
    public void setLabel(String value){
      label = value;
    }
    public Node getNodeA() {
      return nodea;
    }
    public void setNodeA(Node nodea) {
      this.nodea = nodea;
    }
    public void setNodeB(Node nodeb) {
      this.nodeb = nodeb;
    }
    public Node getNodeB() {
      return nodeb;
    }
    @Override 
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append(label).append("<").append(nodea.label).append(nodeb.label).append(">").append("/n");
      return result.toString();
    }
  }  
  public Graph(){
    numNodes = 0;
    numEdges = 0;
    nodesMap = new HashMap<String, Node>();
  }
  public int getNumNodes() {
    return numNodes;
  }
  public int getNumEdges() {
    return numEdges;
  }
  public boolean hasKey(T key) {
    return nodesMap.containsKey(key);
  }
  public boolean hasNode(String label) {
    return nodesMap.containsKey(label);
  }
  public boolean addNode(String label, T value) {
    Node newNode;
    newNode = nodesMap.get(label);
    if (newNode == null) {
      newNode = new Node(label, value);
      nodesMap.put(label, newNode);
      validateNode(newNode);
      numNodes += 1;
      return true;
    }
    return false;
  }
  public Node getNode(String label) {
    return nodesMap.get(label);
  }
  public boolean removeNode(String label) {
    Node removedNode = nodesMap.get(label);
    if (removedNode == null) {
      return false;
    }
    validateNode(removedNode);
    removedNode.getEdgeNeighbors().clear();
    for (Node adjNode : removedNode.getNodeNeighbors()) {
      adjNode.getNodeNeighbors().remove(removedNode);
      for (Edge edge : adjNode.getEdgeNeighbors()) {
        if (edge.getNodeA() == removedNode || edge.getNodeB() == removedNode) {
          adjNode.getEdgeNeighbors().remove(edge);
        }
      }
    }
    nodesMap.remove(removedNode.label);
    numNodes = numNodes - 1;
    return true;
  }

  private void validateEdge(Edge edge) {
    if (edge == null) {
      throw new NullPointerException("Edge cannot be null.");
    }
    Node nodeA = edge.getNodeA();
    Node nodeB = edge.getNodeB();
    if (!(nodeA.getEdgeNeighbors().contains(edge)) && 
            (nodeB.getEdgeNeighbors().contains(edge))) {
      throw new IllegalArgumentException("Nodes don't contain the edge");
    }
    if (!(nodeA.getNodeNeighbors().contains(nodeB) &&
            nodeB.getNodeNeighbors().contains(nodeA))) {
      throw new IllegalArgumentException();
    }
  }
  private void validateNode(Node node) {
    if (!(nodesMap.containsValue(node))) {
      throw new IllegalArgumentException(node.label + " is not a vertex in the graph.");
    }
    if (node == null) {
      throw new NullPointerException("Node cannot be null.");
    }
  }
  
  public void connect(String nodeALabel, String nodeBLabel, String label){
    validateNode(getNode(nodeALabel));
    validateNode(getNode(nodeBLabel));
    Edge edge = new Edge(label);
    edge.setNodeA(getNode(nodeALabel));
    edge.setNodeB(getNode(nodeBLabel));
    getNode(nodeALabel).addEdgeNeighbor(edge.getLabeL());
    getNode(nodeBLabel).addEdgeNeighbor(edge.getLabeL());
    getNode(nodeALabel).addNodeNeighbor(getNode(nodeBLabel));
    getNode(nodeBLabel).addNodeNeighbor(getNode(nodeALabel));
    validateEdge(edge);
    numEdges += 1;
  } 
  
  public boolean checkConnectivity(Node nodeA, Node nodeB) {
    return ((nodeA.getNodeNeighbors().contains(nodeB)) &&
            (nodeB.getNodeNeighbors().contains(nodeA)));
  }
  public Edge getEdge(String label1, String label2) {
    Node nodeA = getNode(label1);
    Node nodeB = getNode(label2);
    if (!(checkConnectivity(nodeA, nodeB))) {
      throw new IllegalArgumentException("Nodes must be connected.");
    }
    validateNode(nodeA);
    validateNode(nodeB);
    Set<Edge> intersectionSet = new HashSet<Edge>();
    Iterator<Edge> iterator = intersectionSet.iterator();
    for (Edge edge : nodeA.getEdgeNeighbors()) {
      if (nodeB.getEdgeNeighbors().contains(edge)) {
        intersectionSet.add(edge);
      }
    }
    if (intersectionSet.size() == 0) {
      throw new RuntimeException("Nodes don't share an edge.");
    }
    Edge edge = iterator.next();
    if (iterator.hasNext()) {
      throw new RuntimeException("Nodes are connected by more than one edge.");
    }
    return edge;
  }
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Node node  : nodesMap.values()) {
      result.append(node).append(":");
      for (Node adjNode : node.getNodeNeighbors()) {
        result.append(adjNode);
      }
      result.append("\n");
    }
    return result.toString();  
  }
  
  private class DFSIterator implements Iterator<T> {
    private Set<Node> visited = new HashSet<Node>(); 
    private Stack<Node> stack = new Stack<Node>();
    
    DFSIterator(Node node) {
      stack.push(node);
      visited.add(node);
    }
    @Override
    public boolean hasNext() {
      return stack.size() > 0;
    }
    @Override
    public T next() {
      if (stack.isEmpty()) {
        throw new NoSuchElementException();
      }
      Node node = stack.pop();
      for (Node adjNode : node.getNodeNeighbors()) {
        if (!visited.contains(adjNode)) {
          stack.push(adjNode);
          visited.add(adjNode);
        }
      }
      return node.value;
    }
  }
}
