import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		ArrayList<IMovable> objList = new ArrayList<IMovable>();
		
		for(int i = 0; i<15; i++) {
			if(i%3 == 0) {
				objList.add(new Person());
			}
			else if(i%3 == 1) {
				objList.add(new Banana());
			}
			else {
				objList.add(new Monkey());
			}
		}
		
		for(int i = 0; i<objList.size(); i++) {
			objList.get(i).move(0, 0, 0, 0, 0);
			if(objList.get(i) instanceof Monkey) {
				System.out.println("It is a monkey");
			}
		}

	}

}
