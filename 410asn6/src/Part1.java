// class Driver {
// public static void main(String[] args) {
// Scanner s = new Scanner(System.in);
// BasicGraph graph1 = new BasicGraph();
// int idnum = 0;
// for (;;) {
// System.out.println("command (q,an,ae,dn,de,s,pn,pg)?");
// String result = s.nextLine();
// if (result.equals("q")) {
// break;
// } else if (result.equals("an")) {
// System.out.println("Type a name for the node");
// String name = s.nextLine();
// boolean comp = true;
// for (Node n : graph1.graph.keySet()) {
// if (name.equals(n.name)) {
// System.out.println("False: duplicate name");
// comp = false;
// break;
// }
// }
// if (comp) {
// Node newnode = new Node(name, idnum);
// idnum++;
// graph1.addNode(newnode);
// } else {
// }
// } else if (result.equals("ae")) {
// System.out
// .println("Enter the name of the node where you want the edge to start");
// String s1 = s.nextLine();
// System.out
// .println("Enter the name of the node where you want the edge to end");
// String s2 = s.nextLine();
// Node np = null;
// Node nq = null;
// for (Node n : graph1.graph.keySet()) {
// if (n.name.equals(s1)) {
// np = n;
// }
// if (n.name.equals(s2)) {
// nq = n;
// }
// }
// if (np == null || nq == null) {
// System.out.println("False");
// } else {
// System.out
// .println("Would you like name the edge? ('yes' or 'no')");
// String ans = s.nextLine();
// String en = null;
// if (ans.equals("yes")) {
// System.out
// .println("What would you like to name the edge?");
// en = s.nextLine();
// } else {
// en = null;
// }
// graph1.addEdge(np, nq, en);
// System.out.println("True");
// }
//
// } else if (result.equals("dn")) {
// System.out
// .println("Enter the name of the node where you want the edge to remove");
// String rem = s.nextLine();
// for (Node n : graph1.graph.keySet()) {
// if (n.name.equals(rem)) {
// graph1.deleteNode(n);
// System.out.println("True");
// break;
// }
// }
// } else if (result.equals("de")) {
// System.out
// .println("Enter the name of the starting node of the edge you want to delete");
// String s1 = s.nextLine();
// System.out
// .println("Enter the name of the ending node of the edge you want to delete");
// String s2 = s.nextLine();
// Node np = null;
// Node nq = null;
// for (Node n : graph1.graph.keySet()) {
// if (n.name.equals(s1)) {
// np = n;
// }
// if (n.name.equals(s2)) {
// nq = n;
// }
// }
// if (np == null || nq == null) {
// System.out.println("False");
// } else {
// graph1.deleteEdge(np, nq);
// System.out.println("True");
// }
// } else if (result.equals("s")) {
// System.out.println("Number of nodes: " + graph1.nodenum);
// System.out.println("Number of edges: " + graph1.edgenum);
// } else if (result.equals("pg")) {
// graph1.printGraph();
// } else if (result.equals("pn")) {
// System.out
// .println("What is the name of the node you would like to print?");
// String ok = s.nextLine();
// Node nz = null;
// for (Node n : graph1.graph.keySet()) {
// if (n.name.equals(ok)) {
// nz = n;
// break;
// }
// }
// if (nz == null) {
// System.out.println("Not a node in the graph");
// } else {
// graph1.printNode(nz);
// }
// } else {
// System.out.println("Not a command");
// }
// }
//
// }
// }