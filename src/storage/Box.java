package storage;

/**
 * This class holds the method to create a Box container to hold numbers
 * 
 * @param <N>
 */
public class Box<N extends Number> {
	private N[] numbers;
	private int index = 0;
	private int boxSize;
	private int initSize = 1;

	public Box() {
		this.numbers = (N[]) (new Number[initSize]);
	}

	public Box(int boxSize) {
		this.numbers = (N[]) new Number[boxSize];
	}

	/**
	 * This method is used to add an item to the box
	 * 
	 * @param <N>
	 * @param num
	 */
	public void add(N num) {
		if (this.index == numbers.length) {
			this.numbers = increaseSize(this.numbers, this.numbers.length);
		}
		this.numbers[this.index] = (N) num;
		this.index++;
	}

	/**
	 * This is a private method, meant to double the size of the array when needed
	 * @param numbers is an array of generic type N
	 * @param size is the current size of the array
	 * @return the array, with the size having been doubled
	 */
	private N[] increaseSize(N[] numbers, int size) {
		N[] newArray = (N[]) new Number[size*2];
		for (int i = 0; i < numbers.length; i++) {
			newArray[i] = numbers[i];
		}
		return newArray;
	}

	/**
	 * This method returns the length of the box (the number of items in it)
	 * @param <N> is a generic instance type. The type must be a Number or one of its sub-types
	 * @return the number of items in the box
	 */
	public <N> int size() {
		int count = 0;
		for (N e : (N[]) this.numbers) {
			if (e != null) {
				count++;
				continue;
			}
			break;
		}
		return count;
	}

	public void val() {
		System.out.println(numbers[0]);
	}
	public void get() {
		
	}
	private StringBuilder buildString(StringBuilder sb) {
		for(N e: numbers) {
			if (e != null) {
				sb.append(e+ ", ");
			}
		}
		sb.delete(sb.length()-2, sb.length());
		return sb;
	}
	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(sb);
		return "Box{"+ sb+"}";
	}
}







