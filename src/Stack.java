import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Stack {
	private List<Point> stack = new ArrayList<>();
	
	
	public Stack() {
	}
	
	public Stack(List<Point> list) {
		stack = list;
	}
	
	public void putOnHead(Point p) {
		stack.add(0, p);
	}
	
	public void takeHead() {
		stack.remove(0);
	}
	
	public Point getHead() {
		Point headPoint = stack.get(0);
		takeHead();
		return headPoint;
	}
	
	
	public Point whatIsHead() {
		Point headPoint = getHead();
		putOnHead(headPoint);
		return headPoint;
	}
	
	public Point whatIsAfterHead() {
		Point pHead = getHead();
		Point pAfterHead = whatIsHead();
		putOnHead(pHead);
		return pAfterHead;
	}
	
	
	public String getStackAsString() {
		Stack stackTmp = new Stack();
		String txt = "";
		while ( !this.isStackEmpty() ){
			Point p = getHead();
			txt += p.getPointAsString() + (" | ");
			stackTmp.putOnHead(p);
		}
		txt += "\n";
		
		while( !stackTmp.isStackEmpty() ){
			this.putOnHead(stackTmp.getHead());
		}
		
		return txt;
	}
	
	public void showStack() {
		Stack stackTmp = new Stack();
		while ( !this.isStackEmpty() ){
			Point p = getHead();
			p.show(); System.out.print(" | ");
			stackTmp.putOnHead(p);
		}
		System.out.println();
		
		while( !stackTmp.isStackEmpty() ){
			this.putOnHead(stackTmp.getHead());
		}
	}
	
	public int getSize() {
		return stack.size();
	}
	
	public boolean isStackEmpty() {
		return stack.isEmpty();
	}
	
	public List<Point> getStackAsList() {
		return stack;
	}
	
	public void saveToFile() {
		String text = getStackAsString();
		String fileName = "C:/Users/AlekSandR/workspace/GrahamAlgorithm/result.txt";
		try {
			
			PrintStream out = new PrintStream(new FileOutputStream(fileName));
			out.print(text);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
