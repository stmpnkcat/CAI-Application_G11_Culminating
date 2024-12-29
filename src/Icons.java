
import javax.swing.ImageIcon;

/*
 * This class creates the images used by other classes
 */
public class Icons {
	
	// Logo icon
	public static final ImageIcon LOGO = new ImageIcon("images/logo.png");
	
	public static final ImageIcon BASIL_NEUTRAL = new ImageIcon("images/basil_neutral.gif");
	public static final ImageIcon BASIL_ANGRY = new ImageIcon("images/basil_angry.gif");
	
	public static final ImageIcon ARROW_LEFT = new ImageIcon("images/arrow_left.gif");
	public static final ImageIcon ARROW_RIGHT = new ImageIcon("images/arrow_right.gif");
	
	public static final ImageIcon DIAGRAM_1 = new ImageIcon("images/diagram_1.png");
	
	public static final ImageIcon TEMPLATE_CODE = new ImageIcon("images/template_code.png");
	
	public static final ImageIcon[] BASIL_WALK = 
		{new ImageIcon("images/basil_walk_left.gif"),
			new ImageIcon("images/basil_walk_up.gif"),
			new ImageIcon("images/basil_walk_right.gif"),
			new ImageIcon("images/basil_walk_down.gif")};
	
	public static final ImageIcon[] BASIL_IDLE = 
		{new ImageIcon("images/basil_idle_left.png"),
			new ImageIcon("images/basil_idle_up.png"),
			new ImageIcon("images/basil_idle_right.png"),
			new ImageIcon("images/basil_idle_down.png")};
	
	public static final ImageIcon[] LEVEL = 
		{new ImageIcon("images/level_1.png"), 
			new ImageIcon("images/level_2.png"), 
			new ImageIcon("images/level_3.png")};
	
}
