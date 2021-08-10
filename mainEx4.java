//Hagai Hen
//ID: 313414872

package ex4;

public class mainEx4 {

	public static void main(String[] args) {

		if(args.length == 0)
			throw new IllegalArgumentException("ERR: got wrong format string. Please try again.  should be of format: <file name> <sort num>");
		else {
			Ex4 win = new Ex4();
			GUI_Shape_Collection sc = win.getShape_Collection();
			String file = args[0];
			int sortNum = Integer.parseInt(args[1]);
			sc.load(file);
			Shape_Comp comp = new Shape_Comp(sortNum);
			sc.sort(comp);
			win.show();	
		}
	}
}
