
import javax.swing.ImageIcon;

/*
 * This class creates the images used by other classes
 */
public class Icons {
	
	// Logo icon
	public static final ImageIcon LOGO = new ImageIcon("images/logo.png");
	
	// Profile icons
	public static final ImageIcon[] BASIL_PROFILE = 
			{new ImageIcon("images/basil_neutral.gif"),
					new ImageIcon("images/basil_happy.gif"),
					new ImageIcon("images/basil_angry.gif"),
					new ImageIcon("images/basil_sad.gif")};
	
	// Arrow icons
	public static final ImageIcon ARROW_LEFT = new ImageIcon("images/arrow_left.gif");
	public static final ImageIcon ARROW_RIGHT = new ImageIcon("images/arrow_right.gif");
	
	// Diagram icons
	public static final ImageIcon DIAGRAM_HIERARCHY = new ImageIcon("images/diagram_hierarchy.png");
	public static final ImageIcon DIAGRAM_MATH = new ImageIcon("images/diagram_math.png");
	public static final ImageIcon DIAGRAM_EXAMPLE = new ImageIcon("images/diagram_example.png");
	public static final ImageIcon DIAGRAM_PARAMETERS = new ImageIcon("images/diagram_parameters.png");
	public static final ImageIcon DIAGRAM_RETURNING = new ImageIcon("images/diagram_returning.png");
	public static final ImageIcon DIAGRAM_CALLSTACK = new ImageIcon("images/diagram_callstack.png");
	public static final ImageIcon DIAGRAM_OVERLOADING = new ImageIcon("images/diagram_overloading.png");
	public static final ImageIcon DIAGRAM_SYNTAX = new ImageIcon("images/diagram_syntax.png");
	
	// Code icon
	public static final ImageIcon TEMPLATE_CODE = new ImageIcon("images/template_code.png");
	
	// Character icons
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
	
	public static final ImageIcon BASIL_PLUSH = new ImageIcon("images/basil_plush.png");
	
	// Level icons
	public static final ImageIcon[] LEVEL = 
		{new ImageIcon("images/level_1.png"), 
			new ImageIcon("images/level_2.png"), 
			new ImageIcon("images/level_3.png")};
	
}
