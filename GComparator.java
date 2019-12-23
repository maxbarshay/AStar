import java.util.*;

public class GComparator implements Comparator<Node>{
	
	public int compare(Node n1, Node n2){
		if (n1.getG() > n2.getG()){
			return -1;
		} else if (n1.getG() < n2.getG()){
			return 1;
		} else {
			return 0;
		}
	}

}