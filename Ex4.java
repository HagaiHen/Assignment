//Hagai Hen
//ID: 313414872

package ex4;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import ex4.geometry.*;
/**
 * This class is the "main" class which will be constructed and run as in (Test_Ex4).
 * Ex4: you should implement this class!
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI{

	private Shape_Collection s;

	public Ex4() { //default constructor
		this.s = new Shape_Collection();
	}
	public Ex4(Shape_Collection g) { //regular/copy constructor
		this.s = g;
	}

	@Override
	public void init(GUI_Shape_Collection g) { //set the collection
		this.s = (Shape_Collection) g;
	}

	@Override
	public GUI_Shape_Collection getShape_Collection() { //get a collection
		return this.s;
	}

	@Override
	public void show() { //goes through each shape in the collection and draws it
		int i = 0;
		double[] arr = new double[3];
		double[] arr1 = new double[3];
		Point2D[] arr2 = new Point2D[3];
		StdDraw.setPenRadius(0.005); //set the thickness of the pen	
		
		arr2 = this.s.getBoundingBox().getPoints(); //get the points of "getBoundingBox"
		StdDraw.setScale(Math.min(arr2[1].x(), arr2[0].x()) - 0.1, Math.max(arr2[0].y(), arr2[1].y()) + 0.1); //define the scale using "getBoundingBox"

		while(i<this.s.size()) //goes through collection
		{

			StdDraw.setPenColor(this.s.get(i).getColor()); //set the color for the specific shape 
			if(this.s.get(i).getShape() instanceof Point2D) { //check the kind of the shape
				arr[0] = this.s.get(i).getShape().centerOfMass().x(); //get the values of the shape
				arr[1] = this.s.get(i).getShape().centerOfMass().y();
				StdDraw.point(arr[0], arr[1]); //draw the point
			}
			if(this.s.get(i).getShape() instanceof Segment2D) { 
				arr2 = this.s.get(i).getShape().getPoints();
				StdDraw.line(arr2[0].x(), arr2[0].y(), arr2[1].x(), arr2[1].y()); 

			}
			if(this.s.get(i).getShape() instanceof Rect2D) { 
				arr[0] = this.s.get(i).getShape().centerOfMass().x();
				arr[1] = this.s.get(i).getShape().centerOfMass().y();
				arr2 = this.s.get(i).getShape().getPoints();
				if(this.s.get(i).isFilled()) //check if the shape is filled
					StdDraw.filledRectangle(this.s.get(i).getShape().centerOfMass().x(), this.s.get(i).getShape().centerOfMass().y(), (Math.abs(this.s.get(i).getShape().getPoints()[0].x()-this.s.get(i).getShape().getPoints()[1].x())/2), (Math.abs(this.s.get(i).getShape().getPoints()[0].y()-this.s.get(i).getShape().getPoints()[1].y())/2));
				else StdDraw.rectangle(this.s.get(i).getShape().centerOfMass().x(), this.s.get(i).getShape().centerOfMass().y(), (Math.abs(this.s.get(i).getShape().getPoints()[0].x()-this.s.get(i).getShape().getPoints()[1].x())/2), (Math.abs(this.s.get(i).getShape().getPoints()[0].y()-this.s.get(i).getShape().getPoints()[1].y())/2));
			}	

			if(this.s.get(i).getShape() instanceof Circle2D) {			
				arr[0] = this.s.get(i).getShape().centerOfMass().x();
				arr[1] = this.s.get(i).getShape().centerOfMass().y();
				arr2 = this.s.get(i).getShape().getPoints();
				if(this.s.get(i).isFilled()) 
					StdDraw.filledCircle(arr[0], arr[1], arr2[1].y()-arr[1]);
				else StdDraw.circle(arr[0], arr[1], arr2[1].y()-arr[1]);
			}

			if(this.s.get(i).getShape() instanceof Triangle2D) {			
				arr2 = this.s.get(i).getShape().getPoints();
				arr[0] = arr2[0].x();
				arr[1] = arr2[1].x();
				arr[2] = arr2[2].x();
				arr1[0] = arr2[0].y();
				arr1[1] = arr2[1].y();
				arr1[2] = arr2[2].y();

				if(this.s.get(i).isFilled())
					StdDraw.filledPolygon(arr, arr1);
				else StdDraw.polygon(arr, arr1);
			}
			i++;
		}

	}

	@Override
	public String getInfo() { //prints all the shapes values in the collection
		return this.s.toString();
	}
}
