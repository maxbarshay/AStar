public class Node{

	private Point location;
	private int g;
	private int h;
	private int f;
	private Node prior;

	public Node(Point location, int g, int h, Node prior){
		this.location = location;
		this.g = g;
		this.h = h;
		this.f = g + h;
		this.prior = prior;
	}

	// public Node(){

	// }

	public Point getLocation(){ return location; }
	public int getG(){ return g; }
	public int getH(){ return h; }
	public int getF(){ return f; }
	public Node getPrior(){ return prior; }

	public String toString(){
		return " g: " + g + " h: " + h + " f: " + f + " point: " + location;
	}

	// public int compareTo(Node n){
	// 	if (this.getF() > n.getF()){
	// 		return 1;
	// 	} else if (this.getF() < n.getF()){
	// 		return -1;
	// 	} else {
	// 		return 0;
	// 	}
	// }

	public boolean equals(Object o){
		if (o == null) return false;
		else if (getClass() != o.getClass()) return false;
		else {
		Node other = (Node)o;
		return this.location == other.location;
		
		//}	
	}



}
}