import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;

public class AStarPathingStrategy
        implements PathingStrategy
{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
    	Comparator<Node> fComp = new FComparator();
        Comparator<Node> gComp = new GComparator();
        Comparator<Node> pxComp = (n1,n2) -> n1.getLocation().x - n2.getLocation().x;
        Comparator<Node> pyComp = (n1,n2) -> n1.getLocation().y - n2.getLocation().y;
        Comparator<Node> nodeComp = fComp.thenComparing(gComp).thenComparing(pxComp).thenComparing(pyComp);
        HashMap<Point, Node> openList = new HashMap<Point, Node>();
        TreeSet<Node> openSet = new TreeSet<>(nodeComp);
        HashMap<Point, Node> closedList = new HashMap<Point, Node>();
        List<Point> path = new LinkedList<Point>();
        Node startNode = new Node(start, 0, Math.abs(start.x - end.x) + Math.abs(start.y - end.y), null);
        openList.put(start, startNode);
        openSet.add(startNode);
        Node current = startNode;
        while (openSet.size() > 0 && !withinReach.test(current.getLocation(), end)){
            Predicate<Point> isNotClosed = p -> (!closedList.containsKey(p));
            List<Point> validSquares = 
            potentialNeighbors.apply(current.getLocation()). 
            filter(canPassThrough).
            filter(isNotClosed).
            collect(Collectors.toList());
            for (Point square : validSquares){
                if (!openList.containsKey(square)){
                    openList.put(square, new Node(square,
                            current.getG() + 1,
                            Math.abs(square.x - end.x) + Math.abs(square.y - end.y),
                            current));    
                    openSet.add(new Node(square,
                            current.getG() + 1,
                            Math.abs(square.x - end.x) + Math.abs(square.y - end.y),
                            current));
                }
                else if (openList.containsKey(square) && 
                    (openList.get(square).getG() > (current.getG() + 1))){
                        openList.remove(square);
                        openList.put(square, new Node(square,
                        current.getG() + 1,
                        Math.abs(square.x - end.x) + Math.abs(square.y - end.y),
                        current));
                }   
        }   
            openList.remove(current.getLocation());
            openSet.remove(current);
            closedList.put(current.getLocation(), current); 

            if (openSet.size() > 0){
            	current = openSet.first();
            }   
        }
        if (withinReach.test(current.getLocation(), end)){ 
            while (!current.getLocation().equals(start)){
                path.add(0, current.getLocation());
                current = current.getPrior();
            }
        }
        return path;
    }
}
