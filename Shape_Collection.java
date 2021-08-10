//Hagai Hen
//ID: 313414872

package ex4;
import java.util.ArrayList;
import java.util.StringTokenizer;  
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import ex4.geometry.Circle2D;
import ex4.geometry.GeoShape;
import ex4.geometry.Point2D;
import ex4.geometry.Rect2D;
import ex4.geometry.Segment2D;
import ex4.geometry.Triangle2D;
/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Shape_Collection implements GUI_Shape_Collection{

	private ArrayList<GUI_Shape> g;

	public Shape_Collection() { //default constructor
		this.g = new ArrayList<GUI_Shape>();
	}

	public Shape_Collection(ArrayList<GUI_Shape> g) { //regular constructor
		this.g = g;
	}

	@Override
	public GUI_Shape get(int i) { //get a shape from the collection
		return this.g.get(i);
	}
	public GUI_Shape set(int i, GUI_Shape s) { //set a shape from the collection
		return this.g.set(i, s);
	}

	@Override
	public int size() { //returns the size of the collection
		return this.g.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) { //removes an element 
		return this.g.remove(i);
	}

	@Override
	public void addAt(GUI_Shape s, int i) { //add in a specific index
		this.g.add(i, s);
	}
	@Override
	public void add(GUI_Shape s) { //add shape in the end
		this.g.add(s);
	}
	@Override
	public GUI_Shape_Collection copy() { //create a copy of the collection
		return new Shape_Collection(this.g);
	}
	@Override
	public void sort(Comparator<GUI_Shape> comp) { //doing sort
		this.g.sort(comp);
	}

	@Override
	public void removeAll() { //clear all the collection
		this.g.removeAll(g);
	}

	@Override
	/*
	 * 
	 * 
	 */
	public void save(String file) { //save text file with the info of the shapes
		try { //Goes through each shape in the collection, and writes its print on a text file
			FileWriter myWriter = new FileWriter(file);
			for (int i = 0; i < this.g.size(); i++) {
				myWriter.write(this.g.get(i).toString());
				myWriter.write("\n");
			}
			myWriter.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void load(String file) { //Goes through each line in the file and produces a collection with similar values

		this.g.removeAll(this.g); //clear the collection
		try {
			int i = 0;
			File f = new File(file);
			Scanner myReader = new Scanner(f);
			String str = "";
			
			while (myReader.hasNextLine()) { //goes through each line
				String[] data = myReader.nextLine().split(",");	//split the string cells by ","			
				GeoShape s;
				GUIShape gs;
				if(data[4].equals("Point2D")) {
					str = data[5] + "," + data[6]; //the point
					s = new Point2D(str); 
					gs = new GUIShape(s, Boolean.parseBoolean(data[2]), Color.decode(data[1]), Integer.parseInt(data[3])); //define the GUIShape using the data in the string[] 
					this.g.add(i,gs);					
				}
				if(data[4].equals("Circle2D")) {
					str = data[5] + "," + data[6] + "," + data[7]; //the point and radius
					s = new Circle2D(str);
					gs = new GUIShape(s, Boolean.parseBoolean(data[2]), Color.decode(data[1]), Integer.parseInt(data[3])); //define the GUIShape using the data in the string[] 
					this.g.add(i,gs);					
				}
				if(data[4].equals("Segment2D")) {
					str = data[5] + "," + data[6] + "," + data[7] + "," + data[8]; //the points
					s = new Segment2D(str);
					gs = new GUIShape(s, Boolean.parseBoolean(data[2]), Color.decode(data[1]), Integer.parseInt(data[3])); //define the GUIShape using the data in the string[] 
					this.g.add(i,gs);					
				}
				if(data[4].equals("Rect2D")) {
					str = data[5] + "," + data[6] + "," + data[7] + "," + data[8];
					s = new Rect2D(str);
					gs = new GUIShape(s, Boolean.parseBoolean(data[2]), Color.decode(data[1]), Integer.parseInt(data[3])); //define the GUIShape using the data in the string[] 
					this.g.add(i,gs);

				}
				if(data[4].equals("Triangle2D")) {
					str = data[5] + "," + data[6] + "," + data[7] + "," + data[8] + "," + data[9] + "," + data[10]; //the points
					s = new Triangle2D(str);
					gs = new GUIShape(s, Boolean.parseBoolean(data[2]), Color.decode(data[1]), Integer.parseInt(data[3])); //define the GUIShape using the data in the string[] 
					this.g.add(i,gs);					
				}
				i++;
			}
			myReader.close();	
		}
		

		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rect2D getBoundingBox() { //returns the smallest rectangle that blocks all the shapes
		
		//the program finds the rectangle by finding the smallest and largest x and y values
		//and using it to build the rectangle


		double xMinVal = Integer.MAX_VALUE;
		double xMaxVal = Integer.MIN_VALUE;
		double yMinVal = Integer.MAX_VALUE;
		double yMaxVal = Integer.MIN_VALUE;
		Point2D[] tmpArr = new Point2D[4];
		for (int i = 0; i < this.g.size(); i++)
		{
			if (this.g.get(i).getShape() instanceof Point2D) {
				tmpArr = this.g.get(i).getShape().getPoints();				
				xMinVal = Math.min(tmpArr[0].x(), xMinVal);
				yMinVal = Math.min(tmpArr[0].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[0].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[0].y(), yMaxVal);
			}
			if (this.g.get(i).getShape() instanceof Segment2D || this.g.get(i).getShape() instanceof Rect2D) {
				tmpArr = this.g.get(i).getShape().getPoints();				
				xMinVal = Math.min(tmpArr[0].x(), xMinVal);
				yMinVal = Math.min(tmpArr[0].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[0].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[0].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[1].x(), xMinVal);
				yMinVal = Math.min(tmpArr[1].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[1].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[1].y(), yMaxVal);
			}
			if (this.g.get(i).getShape() instanceof Triangle2D) {
				tmpArr = this.g.get(i).getShape().getPoints();				
				xMinVal = Math.min(tmpArr[0].x(), xMinVal);
				yMinVal = Math.min(tmpArr[0].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[0].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[0].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[1].x(), xMinVal);
				yMinVal = Math.min(tmpArr[1].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[1].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[1].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[2].x(), xMinVal);
				yMinVal = Math.min(tmpArr[2].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[2].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[2].y(), yMaxVal);
			}
			if (this.g.get(i).getShape() instanceof Circle2D) {
				tmpArr = ((Circle2D) this.g.get(i).getShape()).getPoints2();
				xMinVal = Math.min(tmpArr[1].x(), xMinVal);
				yMinVal = Math.min(tmpArr[1].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[1].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[1].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[2].x(), xMinVal);
				yMinVal = Math.min(tmpArr[2].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[2].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[2].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[3].x(), xMinVal);
				yMinVal = Math.min(tmpArr[3].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[3].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[3].y(), yMaxVal);
				xMinVal = Math.min(tmpArr[4].x(), xMinVal);
				yMinVal = Math.min(tmpArr[4].y(), yMinVal);
				xMaxVal = Math.max(tmpArr[4].x(), xMaxVal);
				yMaxVal = Math.max(tmpArr[4].y(), yMaxVal);
			}
		}
		Point2D p1 = new Point2D(xMinVal,yMinVal);
		Point2D p2 = new Point2D(xMaxVal,yMaxVal);
		Rect2D Rect = new Rect2D(p1,p2);
		return Rect;
	}
	@Override
	public String toString() { //prints all the shapes in the collection
		String str = "";
		for (int i = 0; i < this.g.size(); i++) {
			str = str + this.g.get(i).toString() + "\n";
		}
		return str;
	}
}
