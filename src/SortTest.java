/*
 * Deliverable 4
 * 
 * Property Testing for Java Arrays.sort(int[]) method
 * 
 * Author: Colin Swan
 * Due Date: 3/24/2016
 */

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class SortTest{
	//Creating Random Number generator.
	Random rand = new Random(System.currentTimeMillis());
	
	/*
	 * This test asserts the property that an int[] array that has been
	 * sorted using Arrays.sort(int[]) will be the same size
	 * as it was before being sorted. It tests this by creating 100
	 * arrays of random size between 0 and 10,000. Each element in the
	 * randomly generated array is a random number between 0 and MAX_INT.
	 * It then tests that the size after sorting each array is the same as
	 * the size of that array before sorting.
	*/
	@Test
	public void testSameSize(){
		//Making 100 random arrays.
		for(int i = 0; i < 100; i++){
			//Creates an array of random size between
			//0(inclusive) and 10000(exclusive)
			int[] arr = new int[rand.nextInt(10000)];
			for(int j = 0; j < arr.length; j++){
				arr[j] = rand.nextInt(Integer.MAX_VALUE);
			}
			int startSize = arr.length;
			Arrays.sort(arr);
			int endSize = arr.length;
			assertEquals(startSize, endSize);
		}
		
	}
	
	/*
	 * This test asserts the property that elements in a sorted array
	 * should be in increasing order. It tests this by creating 100
	 * arrays of random size between 0 and 10,000. Each element in the
	 * randomly generated array is a random number between 0 and MAX_INT.
	 * It then tests that given an element at index j, the element at index
	 * j+1 is greater than the element at index j.
	*/
	@Test
	public void testOutputIncreasing(){
	//Making 100 random arrays.
		for(int i = 0; i < 100; i++){
			//Creates an array of random size between
			//0(inclusive) and 10000(exclusive)
			int[] arr = new int[rand.nextInt(10000)];
			for(int j = 0; j < arr.length; j++){
				arr[j] = rand.nextInt(Integer.MAX_VALUE);
			}
			Arrays.sort(arr);
			boolean nextGreater = true;
			String failMsg = "";
			for(int j= 0; j < arr.length-1; j++){
				if(arr[j] > arr[j+1]){
					nextGreater = false;
					failMsg = arr[j] + " is not greater than " + arr[j+1]; 
					break;
				}
			}
			assertTrue(failMsg, nextGreater);
		}
	}
	
	/*
	 * This test asserts the property that the Arrays.sort() method should
	 * be idempotent. It tests this by creating 100
	 * arrays of random size between 0 and 10,000. Each element in the
	 * randomly generated array is a random number between 0 and MAX_INT.
	 * It then sorts an array, saves the values of the sorted array in a
	 * new array, and sorts the array again. The test then checks that
	 * the values in the copy of the array (which was sorted once) are the 
	 * same as the values in the array that has been sorted twice.
	*/
	@Test
	public void testIdempotent(){
		for(int i = 0; i < 100; i++){
			//Creates an array of random size between
			//0(inclusive) and 10000(exclusive)
			int[] arr = new int[rand.nextInt(10000)];
			for(int j = 0; j < arr.length; j++){
				arr[j] = rand.nextInt(Integer.MAX_VALUE);
			}
			Arrays.sort(arr);
			
			//Saving the sorted values from the first call to sort arr.
			int[] firstSorted = new int[arr.length];
			for(int j = 0; j < arr.length; j++){
				firstSorted[j] = arr[j];
			}
			
			//Second sort
			Arrays.sort(arr);
			
			boolean equalTo = true;
			String failMsg = "";
			
			for(int j= 0; j < arr.length; j++){
				assertEquals(arr[j], firstSorted[j]);
			}
		}
	}
}