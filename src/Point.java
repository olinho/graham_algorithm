import java.util.Comparator;

public class Point {
	double x=0;
	double y=0;
	
	public Point() {}
	
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void show(){
		System.out.print("("+x+","+y+")");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (x == ((Point) obj).x && y == ((Point) obj).y)
			return true;
		return false;
	}
	
	public String getPointAsString() {
		return "("+x+","+y+")";
	}
	
	
}
