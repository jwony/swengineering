<<<<<<< HEAD:src/FrameManager.java


=======
>>>>>>> origin/master:src/FrameManager.java
import java.awt.*;
import javax.swing.*;

public class FrameManager
{
	static void CreateJLabel(Container c,JLabel obj,String name, int sx, int sy, int dx, int dy, Font f)
	{
		obj.setText(name);
		obj.setSize(sx,sy);
		obj.setLocation(dx,dy);
		obj.setFont(f);
		c.add(obj);
	}
	
	static void CreateJTextField(Container c,JTextField obj,String name, int sx, int sy, int dx, int dy, Font f)
	{
		obj.setText(name);
		obj.setSize(sx,sy);
		obj.setLocation(dx,dy);
		obj.setFont(f);
		c.add(obj);
	}
	
	static void CreateJButton(Container c,JButton obj,String name, int sx, int sy, int dx, int dy, Font f)
	{
		obj.setText(name);
		obj.setSize(sx,sy);
		obj.setLocation(dx,dy);
		obj.setFont(f);
		c.add(obj);
	}
}
