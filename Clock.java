public class Clock {

	public static void calculate(Runnable block) {
		long startTime = System.nanoTime();
		block.run();
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime / 1.0e9);
	}

}