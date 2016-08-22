import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExcitingFeatures {
	
public static void main (String args[]) {
	
	// external vs internal iterators - forEach
	// stream vs parallelStream
	
	//System.out.println("Hello...");  
	
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
	
	// iterators
	for(int i=1; i<=numbers.size(); i++) {
		//System.out.println("value is " + i);
	}
	
	System.out.println("--------");
	// iterators
	for (int i : numbers) {
		//System.out.println("value: " + i);
	}
	
	//numbers.forEach(e -> System.out.println(e));
	numbers.forEach(System.out::println);
	
	// find the sum of double of odd numbers that are > 3
	int doubleVal = 0;
	for (int i : numbers) {
		if (i > 3 && i%2 != 0) {
			doubleVal += i * 2;  
		}
	}
	System.out.println("doubleVal " + doubleVal);
	
	/*System.out.println(
	numbers.stream()
			.filter(e -> e > 3)
			.filter(e -> e%2 != 0)
			.map(e -> e * 2)
			.reduce(0, Integer::max));*/
	
	/*System.out.println(
	numbers.stream()
			.filter(e -> e > 3)
			.filter(e -> e%2 != 0)
			.mapToInt(e -> e * 2)
			.sum());*/
	
	Clock.calculate(()-> {numbers.parallelStream()
			.filter(Utils::isGT3)
			.filter(Utils::isOddNumber)
			.mapToInt(Utils::doubleValue)
			.sum();});

	List<String> cars = Arrays.asList("C1", "C2", "C3", "C4", "C5");
	System.out.println(cars.stream()
			  .collect(joining(",", "Start", "End")));
	
	System.out.println(buildCars().stream()
			  .collect(toMap(
				  car -> car.getMake() + "-" + car.getModel(),
				  Car::getYear
			  )));
	
	System.out.println(buildCars().stream()
			  .collect(groupingBy(Car::getMake, mapping(Car::getModel, toList()))));
		
	
	IntStream.range(0,5)
		.forEach(System.out::println);
	
	/*ExecutorService executorService = Executors.newFixedThreadPool(10);
	for (int i = 1; i <= 10; i++) {
		int temp = i;
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("task # " + temp);
			}
		});
	}
	
	IntStream.range(1, 10)
			.forEach(e -> executorService.submit(() -> {System.out.println("task #" + e);}));
	
	System.out.println("task completed...");
	*/	
}

private static List<Car> buildCars() {
	return Arrays.asList(
		new Car("Toyota", "Corolla", "2000"),
		new Car("Audi", "A6", "2016"),
		new Car("Toyota", "Prius", "2010"),
		new Car("Honda", "Accord", "2015"));
}

}

class Car {
	private String make;
	private String model;
	private String year;
	
	public Car(String make, String model, String year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getMake() {
		return this.make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getYear() {
		return this.year;
	}
	
}

class Utils {

	public static boolean isGT3(int value) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("isGT3 " + value);
		return value > 3;
	}
	
	public static boolean isOddNumber(int value) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("isOdd " + value);				
		return value %2 != 0;
	}
	
	public static int doubleValue(int value) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("doubleValue " + value);				
		return value * 2;
	}
	
} 