import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.io.*;

class Driver {
	public static void main(String[] args) {

		BasicGraph graph1 = new BasicGraph();
		int num = 0;
		boolean done = false;

		// The name of the file to open.
		String fileName = "p2graphData.txt"; // this is
																// default path,
																// meaning it
		// will look
		// for the file in the "current"
		// directory...
		// meaning whatever
		// directory/folder the java
		// program is run in

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while (1 != 0) {
				line = bufferedReader.readLine();
				Node start = null;
				boolean doTopo = false;
//				boolean dodijkstra = false;
				if (line == null || line.equals("//====================")) {
					doTopo = true;
//					dodijkstra = true;
				}
				else if (line.contains("-1")) {
//					line = bufferedReader.readLine();
//					for (Node k : graph1.graph.keySet()) {
//						if (line.contains(k.name)) {
//							start = k;
//						}
//					}
					done = true;
				} else if (done) {
					done = false;
				} else {
					String delims = "[ ]+";
					String[] tokens = line.trim().split(delims);
					boolean check1 = false;
					boolean check2 = false;
					Node o1 = null;
					Node o2 = null;
					for (Node n : graph1.graph.keySet()) {
						if (n.name.equals(tokens[0])) {
							check1 = true;
							o1 = n;
							break;
						}
					}
					for (Node k : graph1.graph.keySet()) {
						if (k.name.equals(tokens[1])) {
							check2 = true;
							o2 = k;
							break;
						}
					}
					if (!check1 && !check2) {
						Node new1 = new Node(tokens[0], num);
						num++;
						Node new2 = new Node(tokens[1], num);
						num++;
						graph1.addNode(new1);
						graph1.addNode(new2);
						int weight = Integer.parseInt(tokens[2]);
						graph1.addEdge(new1, new2, weight);
					} else if (!check1 && check2) {
						Node new1 = new Node(tokens[0], num);
						num++;
						graph1.addNode(new1);
						int weight = Integer.parseInt(tokens[2]);
						graph1.addEdge(new1, o2, weight);
					} else if (check1 && !check2) {
						Node new2 = new Node(tokens[1], num);
						num++;
						graph1.addNode(new2);
						int weight = Integer.parseInt(tokens[2]);
						graph1.addEdge(o1, new2, weight);
					} else {
						int weight = Integer.parseInt(tokens[2]);
						graph1.addEdge(o1, o2, weight);
					}
				}
				if (line != null) {
				System.out.println(line);
				}
				if (doTopo) {
					doTopo = false;
					System.out
							.println("########################################");
					graph1.printGraph();
					System.out.println();
					System.out.println("Topological Sort");
					List<Node> sorted = graph1.topo();
					if (graph1.topo() == null) {
						System.out.println("Cycle found... no sort possible");
					} else {
						for (int i = 0; i < sorted.size(); i++) {
							System.out.println("(" + sorted.get(i).idnum + ")"
									+ sorted.get(i).name);
						}
					}
					if (line == null) {break;}
					System.out.println("########################################");
					graph1 = new BasicGraph();
					num = 0;
				}
//				if(dodijkstra) {
//					dodijkstra = false;
//					System.out
//					.println("########################################");
//					graph1.printGraph();
//					System.out.println();
//					Set<Node> dij = graph1.findShortest(start);
//					System.out.println("Shortest Paths from source node:");
//					for(Node w : dij) {
//						if(w.weight != 0) {
//						System.out.println("(" + w.idnum + ") " + w.name + ": " + w.weight);
//						}
//						else {
//							System.out.println("(" + w.idnum + ") " + w.name + ": no path");
//						}
//						}
//					if (line == null) {break;}
//					System.out.println("########################################");
//					graph1 = new BasicGraph();
//					num = 0;
//				}
			}

			// Always close files.
			bufferedReader.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}

class Node {
	int idnum;
	String name;
	int indeg;
	int outdeg;
	int edgelabel;
	String edgename;
	int weight;
	List path = new ArrayList<String>();
	
	public Node(String s, int num) {
		name = s;
		idnum = num;
	}
}

class BasicGraph {

	Map<Node, List<Node>> graph = new HashMap<Node, List<Node>>();
	int idnum;
	int nodenum = 0;
	int edgenum = 0;

	

	public boolean addNode(Node n) {
		if (graph.containsKey(n)) {
			return false;
		}
		graph.put(n, new ArrayList<Node>());
		nodenum++;
		return true;
	}

	public boolean addEdge(Node from, Node to, int weight) {
		if (graph.get(from).contains(to)) {
			return false;
		}
		to.edgelabel = edgenum;
		edgenum++;
		to.weight = weight;
		graph.get(from).add(to);
		return true;
	}

	public boolean deleteNode(Node n) {
		graph.remove(n);
		for (Node nd : graph.keySet()) {
			if (graph.get(nd).contains(n)) {
				graph.get(nd).remove(n);
			}
		}
		nodenum--;
		return true;
	}

	public boolean deleteEdge(Node from, Node to) {

		if (!(graph.containsKey(from) && graph.containsKey(to))) {
			return false;
		}
		if (!(graph.get(from).contains(to))) {
			return false;
		}
		graph.get(from).remove(to);
		edgenum--;
		return true;
	}

	public void printGraph() {
		for (Node n : graph.keySet()) {

			int id = n.idnum;
			String title = n.name;
			System.out.println("(" + id + ")" + title);
			if (n.edgename == null) {
				for (int k = 0; k < graph.get(n).size(); k++) {
					System.out.println("  " + "("
							+ graph.get(n).get(k).edgelabel + ")" + "---->"
							+ graph.get(n).get(k).name);
				}
			} else {
				for (int k = 0; k < graph.get(n).size(); k++) {
					System.out.println("  " + "("
							+ graph.get(n).get(k).edgelabel + ")" + "("
							+ graph.get(n).get(k).edgename + ")" + "---->"
							+ graph.get(n).get(k).name);
				}

			}
		}
	}

	public void printNode(Node n) {
		int id = n.idnum;
		String title = n.name;
		System.out.println("(" + id + ")" + title);
		if (n.edgename == null) {
			for (int k = 0; k < graph.get(n).size(); k++) {
				System.out.println("  " + "(" + graph.get(n).get(k).edgelabel
						+ ")" + "---->" + graph.get(n).get(k).name);
			}
		} else {
			for (int k = 0; k < graph.get(n).size(); k++) {
				System.out.println("  " + "(" + graph.get(n).get(k).edgelabel
						+ ")" + "(" + graph.get(n).get(k).edgename + ")"
						+ "---->" + graph.get(n).get(k).name);
			}

		}
	}
	
	public Map<Node,Integer> inDegree () {
        Map<Node,Integer> result = new HashMap<Node,Integer>();
        for (Node n: graph.keySet()){
        	result.put(n, 0);
        }
        for (Node from: graph.keySet()) {
            for (Node to: graph.get(from)) {
                result.put(to, result.get(to) + 1);
            }
        }
        return result;
    }
    
    public List<Node> topo () {
        Map<Node, Integer> indegree = inDegree();
        Stack<Node> no_in_nodes = new Stack<Node>();
        for (Node n: indegree.keySet()) {
            if (indegree.get(n) == 0) {
            	no_in_nodes.push(n);
            }
        }
        List<Node> result = new ArrayList<Node>();
        while (!no_in_nodes.isEmpty()) {
            Node n = no_in_nodes.pop();
            result.add(n);
            for (Node x: graph.get(n)) {
                indegree.put(x, indegree.get(x) - 1);
                if (indegree.get(x) == 0){
                	no_in_nodes.push(x);
                }
            }
        }
        if (result.size() != graph.size()) {
        	return null;
        }
        return result;
    }
    
    public Set<Node> findShortest(Node start) {
        final Queue<Node> queue = new PriorityQueue<Node>(10, new NodeComparator());

        for (Node n :  graph.keySet()) {
            Node currNode = n;
            if (currNode == start) {
                currNode.weight = 0;
                queue.add(currNode);
            } 
        }

        final Set<Node> doneSet = new HashSet<Node>();

        while (!queue.isEmpty()) {
            Node src = queue.poll();
            
            doneSet.add(src);

            for (Node x : graph.get(src)) {
                Node currentNode = x;

                if (!doneSet.contains(currentNode)) {
                    int newDistance = src.weight + x.weight;
                    if (newDistance < currentNode.weight) {
                        currentNode.weight = newDistance;
                        queue.add(currentNode);
                    } 
                }
            }
        }

        return graph.keySet();
    }
}

 class NodeComparator implements Comparator<Node>  {
    @Override
    public int compare(Node n1, Node n2) {
        if (n1.weight > n2.weight) {
            return 1;
        } else {
            return -1;
        }
    }
}

