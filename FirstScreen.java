import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstScreen extends JFrame {
	private JPanel mainPanel;
	private JLabel id;
	private JTextField txtId;
	
	public static void main(String[] args) {
		FirstScreen setFirstScreen = new FirstScreen();
		setFirstScreen.firstScreenPanel();
	}
	
	public void firstScreenPanel() {
		mainPanel = new JPanel();
		id = new JLabel("ID");		
		txtId = new JTextField(20);
		mainPanel.add(id);
		mainPanel.add(txtId);
		add(mainPanel);	
		setTitle("S/E Project");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

}
