import java.util.*;
import java.util.function.*;
import java.awt.Color;
import java.util.stream.Stream;

public class Design {

	// Strategy design - predicate / higher order function
	// Decorator - camera example
	// Builder / Cascade
	// Execute Around Method

	public static int sumNumbers(List<Integer> numbers, Predicate<Integer> selector) {
		int totalVal = 0;
		for (int n : numbers) {
			if (selector.test(n)) {
				totalVal += n;
			}
		}
		return totalVal;
	}

	public static void main(String args[]) {
		System.out.println("Hello....");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("sum of all numbers: " + sumNumbers(numbers, n -> true));			
		System.out.println("sum of all odd numbers: " + sumNumbers(numbers, n -> n %2 != 0));
		System.out.println("sum of all even numbers: " + sumNumbers(numbers, n -> n %2 == 0));
		//System.out.println("sum of all odd numbers: " + sumOddNumbers(numbers));
		//System.out.println("sum of all even numbers: " + sumEvenNumbers(numbers));
		
		Function<Integer, Integer> incIt = n -> n + 1;
		Function<Integer, Integer> doubleIt = n -> n * 2;
		
		System.out.println(incIt.apply(5));
		System.out.println(doubleIt.apply(5));
		System.out.println(incIt.andThen(doubleIt).apply(5));
		printSnap(new Camera());
		printSnap(new Camera(Color::brighter));
		
		FileUtil.doOps(fileUtil-> {fileUtil.init().open();});
		
		//fileUtil.close();
		
	}

	public static void printSnap(Camera camera) {
		camera.snap(new Color(125, 125, 125));
	}

	

}

class FileUtil {
	
	public FileUtil init() {
		System.out.println("init");
		return this;
	}
	
	public void open() {
		System.out.println("file opened...");
	}
	
	public void operation() {
		System.out.println("operation is done too...");
	}
	
	private static void close() {
		System.out.println("closed");
	}
	
	public static void doOps(Consumer<FileUtil> block) {
		FileUtil fileUtil = new FileUtil();
		try {
			block.accept(fileUtil);
		} finally {
			close();
		}
		
	}
	
}

class Camera {
	
	private Function<Color, Color> filter = color -> color;

	
	public Camera(Function<Color, Color>... filters) {
		filter = Stream.of(filters).reduce(Function.identity(), Function::andThen);
		// filter = Stream.of(filters).reduce(color -> color, (theFilters, aFilter) -> theFilters.andThen(aFilter));
	}
	
	
	public void snap(Color color) {
		System.out.println(filter.apply(color));
	}
}