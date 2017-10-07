import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PresentationPanel extends JPanel{
	
	private Graphics2D g2d;
	private int centerX = 50;
	private double rate = 4.0;
	private int centerY = 50;
	private Stack stackInPanel;
	private Area areaInPanel;
	private final static int R=6;
	
	public PresentationPanel() {
		setPreferredSize(new Dimension(400, 400));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g2d = (Graphics2D)g;
		
		logic();
		
		
		paintAreaPoints(areaInPanel);
		paintArea(stackInPanel);
	}

	public void paintArea(Stack stack) {
		Point a,b,firstP;
		b = stack.getHead();
		firstP = b;
		paintPoint(b);
		while( !stack.isStackEmpty() ) {
			a=b;
			b = stack.getHead();
			paintLineConectingPoints(a, b);
			paintPoint(b);
		}
		paintLineConectingPoints(b, firstP);
	}
	 
	public void paintAreaPoints(Area area) {
		AreaWithStack as = new AreaWithStack(area);
		Stack stack = new Stack(as.getStackAsList());
		Point a;
		while( !stack.isStackEmpty() ) {
			a=stack.getHead();
			paintPoint(a);
		}
	}
	
	public void paintPoint(Point a) {
		int x,y;
		
		x= centerX + (int)(a.x * rate);
		y= centerY + (int)(a.y * rate);
		
		g2d.drawOval(x, y, R, R);
	}
	
	public void paintLineConectingPoints(Point a, Point b) {
		int x1,x2,y1,y2;
		x1 = centerX + (int) (a.x*rate);
		x2 = centerX + (int) (b.x*rate);
		y1 = centerY + (int) (a.y*rate);
		y2 = centerY + (int) (b.y*rate);
		int p1_x = (int) x1+R/2;
		int p2_x = (int) x2+R/2;
		int p1_y = (int) y1+R/2;
		int p2_y = (int) y2+R/2;
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	
	public void logic() {
//		Area area = new Area();
		
		CsvReader reader = new CsvReader();
		Area area = new Area(reader.getPointsFromFile());
		
		area.showPoints();
		areaInPanel = area;
		
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
			if ( Main.isLeft(PT2,PT1,p_i) ) {
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
		stack.saveToFile();
		
		System.out.println("Stack size = " + stack.getSize());
		
		stackInPanel = stack;
	}

}
