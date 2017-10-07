
public class AreaWithStack extends Stack{
	
	Area area;
	Stack s;
	public AreaWithStack(Area a) {
		super(a.points);
		this.area = a;
	}
	
	public Stack getStack() {
		return new Stack(super.getStackAsList());
	}
}
