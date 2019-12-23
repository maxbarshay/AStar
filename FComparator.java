import java.util.*;

public class FComparator implements Comparator<Node>{
	public int compare(Node n1, Node n2){
		if (n1.getF() > n2.getF()){
			return 1;
		} else if (n1.getF() < n2.getF()){
			return -1;
		} else {
			return 0;
		}
	}
}