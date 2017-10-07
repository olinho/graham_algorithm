import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Area {
	List<Point> points = new ArrayList<>();
	
	public Area() {
		createPoints();
	}
	
	public Area(List<Point> points) {
		this.points = points;
	}
	
	
	
	public void takeFromHead() {
		points.remove(0);
	}
	
	public void putOnHead(Point p) {
		points.add(0, p);
	}
	
	
	/**
	 * sort points and remove points which are linear.
	 * Leave the most farthest points from each list of points, which lie on same line.
	 * @param mostRightBottomPoint
	 */
	public void sortAndRemoveLinearPoints(Point mostRightBottomPoint) {
//		nothing to sort
		if (points.size() == 1)
			return;
		
		sortPointsByAxis(mostRightBottomPoint);
		/*
		 * now we assume that we have sorted 'points' list and all points are unique
		 */
		List<Point> newList = new ArrayList<>();		
		
		int j=0;
		Point b = points.get(0);
		Point c;
		while( j <= points.size()-1 ){
			c = points.get(j);
			if (arePointsLinear(mostRightBottomPoint, b, c)) {
				double distAB = getDistanceBetweenPoints(mostRightBottomPoint, b);
				double distAC = getDistanceBetweenPoints(mostRightBottomPoint, c);
				if (distAC > distAB) {
					b = c;
				}
			}
			else {
				newList.add(b);
				b=c;
			}
			j++;
		}
		newList.add(b);
		points.clear();
		points = newList;
		
	}
	
	public double getDistanceBetweenPoints(Point a, Point b) {
		return Math.sqrt(Math.pow((b.x-a.x), 2) + Math.pow((b.y-a.y), 2)); 
	}
	
	public boolean arePointsLinear(Point a, Point b, Point c) {
		if (!isLeft(a,b,c) && !isRight(a, b, c))
			return true;
		else return false;
	}
	
	/**
	 * sort points from list.
	 * Check subsequently found points in the opposite direction of the clockwise direction.
	 */
	public void sortPointsByAxis(Point mostRightBottomPoint) {
		Collections.sort(points, (b,c) -> isLeft(mostRightBottomPoint, b, c) == true ? -1 : 1);
	}
	
	/**
	 * check if point c is on the left side of the line formed by points a and b
	 * @param a
	 * @param b
	 * @param c test point
	 * @return
	 */
	public boolean isLeft(Point a, Point b, Point c) {
		double d = (c.x-a.x)*(b.y-a.y) - (c.y-a.y)*(b.x-a.x);
		return d<0;
	}
	
	public boolean isRight(Point a, Point b, Point c) {
		double d = (c.x-a.x)*(b.y-a.y) - (c.y-a.y)*(b.x-a.x);
		return d>0;
	}
	
	
	public List<Point> getPointsWithoutMostRightBottom() {
		List<Point> newList= new ArrayList<>();
		newList = points;
		List<Point> listWithOnlyMostRightBottom = new ArrayList<>();
		listWithOnlyMostRightBottom.add(getMostRightBottomPoint());
		newList.removeAll(listWithOnlyMostRightBottom);
		return newList;
	}
	
	/**
	 * 
	 * @return most right bottom point from list
	 * @throws Exception 
	 */
	public Point getMostRightBottomPoint(){
		
		if (points.size() == 0) return null;
		
		Point bestPoint = points.get(0);
		
		for (Point p : points) {
			if (p.x > bestPoint.x) { bestPoint = p;}
			else if (p.x == bestPoint.x) if (p.y < bestPoint.y) { bestPoint = p;}
		}
		
		return bestPoint;
	}
	
	
	/**
	 * get unique points from list
	 * @return
	 */
	public List<Point> getUniquePoints() {
		List<Point> newList = new ArrayList<>();
		
		for (Point p : points) {
			if (!newList.contains(p)) newList.add(p);
		}
		return newList;
	}
	
	/**
	 * add random points to list
	 */
	public void createPoints() {
		points.add(new Point(2,1));
		points.add(new Point(0,2));
		points.add(new Point(-1,-1));
		points.add(new Point(1,2));
		
		points.add(new Point(2,3));
		points.add(new Point(0,3));
		points.add(new Point(3,0));
		points.add(new Point(-4,-5));
//		points.add(new Point(-2,-3));
//		points.add(new Point(2,2));
//		points.add(new Point(1,0));
//		points.add(new Point(4,3));
//		points.add(new Point(1,0));
//		points.add(new Point(1,10));
//		points.add(new Point(-2,-5));
	}
	
	public void showPoints() {
		for (Point p : points) {
			p.show(); System.out.print(" | ");
		}
		System.out.println();
	}
	
	public void showPoints(int n) {
		int lastN = n;
		if (n > points.size()-1) {
			lastN = points.size() - 1;
		}
		for (int i=0; i<lastN; i++) {
			points.get(i).show(); System.out.print(" | ");
		}
		System.out.println();
	}
	
	/**
	 * 
	 */
	public void addPoint(Point p) {
		points.add(p);
	}
}
