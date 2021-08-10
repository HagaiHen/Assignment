//Hagai Hen
//ID: 313414872

package ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import ex4.geometry.*;

public class GUIShape implements GUI_Shape{

	private GeoShape shape;
	private boolean flag;
	private Color clr;
	private int tag;

    public GUIShape(GeoShape s, boolean flag, Color clr, int tag) { //regular constructor
    	this.shape = s;
    	this.flag = flag;
    	this.clr = clr;
    	this.tag = tag;
     }
    
    public GUIShape(GUIShape s) { //copy constructor
    	
    	this(s.getShape(), s.isFilled(), s.getColor(), s.getTag());
     }
    public GUIShape() { //default constructor
    	this.shape = null;
    	this.flag = false;
    	this.clr = Color.black;
    	this.tag = 0;
     }
    
	@Override
	public GeoShape getShape() { //returns the value of the shape
		return this.shape;
	}

	@Override
	public void setShape(GeoShape s) { //set the value of the shape
		this.shape = s;
	}

	@Override
	public boolean isFilled() { //check if the shape is filled
		return this.flag;	
	}

	@Override
	public void setFilled(boolean filled) { //set the fill
		this.flag = filled;
	}

	@Override
	public Color getColor() { //get the color
		return this.clr;
	}

	@Override
	public void setColor(Color clr) { //set the color
		this.clr = clr;
	}

	@Override
	public int getTag() { // get the tag of the shape
		return this.tag;
	}

	@Override
	public void setTag(int tag) { // set the tag of the shape
		this.tag = tag;
	}

	@Override
	public GUI_Shape copy() { //create a copy of the shape
		return new GUIShape(this);
	}
	@Override
	public String toString() { //prints all the values of the shape
		return this.getClass().getSimpleName() + "," + (this.clr.getRGB()&0xffffff) + "," + this.flag + "," + this.tag + "," + this.shape.getClass().getSimpleName() + "," + this.shape.toString();
	}
}
