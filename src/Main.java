import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		Area area = new Area();
//		CsvReader reader = new CsvReader();
		
//		Area area = new Area(reader.getPointsFromFile());
		
		
		area.showPoints();
		
		Area area2 = new Area(area.getUniquePoints());
		area2.showPoints();
		
		Point mostRightBottomPoint = area2.getMostRightBottomPoint();
		System.out.println("Most right-bottom point:");
		mostRightBottomPoint.show();
		
		System.out.println();
		
		area2.points = area2.getPointsWithoutMostRightBottom();
		System.out.println("Other points:");
		area2.showPoints();
		
		
		System.out.println("After sorting:");
//		Collections.sort(area2.points, (b,c) -> area2.isLeft(mostRightBottomPoint, b, c) == true ? -1 : 1);
//		area2.sortPointsByAxis(mostRightBottomPoint);
		area2.sortAndRemoveLinearPoints(mostRightBottomPoint);
		System.out.println("Number of points: "+ area2.points.size());
		area2.showPoints();
		
		area2.putOnHead(mostRightBottomPoint);
		area2.showPoints();
		
//		************
		Stack stack = new Stack();
		int i=0;
		int N=area2.points.size();
		
		System.out.println("N = " + N);
		stack.putOnHead(area2.points.get(i));
		i++;
		stack.putOnHead(area2.points.get(i));
		i++;
		stack.showStack();
		
		Point p0 = mostRightBottomPoint;
		Point PT1, PT2;
		Point p_i;
		while( i<N ) {
			PT1 = stack.whatIsHead();
			System.out.print("PT1=");PT1.show();
			System.out.println();
			if (PT1 == p0){
				p_i = area2.points.get(i);
				stack.putOnHead(p_i);
				i++;
			}
			
			PT2 = stack.whatIsAfterHead();
			System.out.print("PT2=");PT2.show();
			System.out.println();
			p_i = area2.points.get(i);
			System.out.print("P[i]=");p_i.show();
			System.out.println();
			if ( isLeft(PT2,PT1,p_i) ) {
				System.out.println("warunek isLeft jest spe³niony");
				stack.putOnHead(p_i);
				i++;
			}
			else {
				stack.takeHead();
			}
			System.out.print("Stack: ");
			stack.showStack();
			System.out.println();
		}
		
		stack.showStack();
		
		System.out.println("Stack size = " + stack.getSize());
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PresentationFrame frame = new PresentationFrame();
			}
		});
	
	}
	
	public static boolean isLeft(Point a, Point b, Point c) {
		double d = (c.x-a.x)*(b.y-a.y) - (c.y-a.y)*(b.x-a.x);
		return d<0;
	}
}
