
public class allCars {
	
	private FroggerCars [] cars;
	
	public allCars() {
		cars = new FroggerCars[2];
		for (int i = 0; i < 2; i++) {
			cars[i] = new FroggerCars();
		}
	}

}
