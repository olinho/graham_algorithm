import javax.swing.JFrame;
import javax.swing.JPanel;

public class PresentationFrame extends JFrame{
	
	private JPanel panel;
	
	public PresentationFrame() {
		super("Presentation panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		
		panel = new PresentationPanel();
		add(panel);
		
		setVisible(true);
	}
	
	public JPanel getPanel() throws Exception {
		if (panel != null)
			return panel;
		else 
			throw new Exception("no panel");
	}

}
