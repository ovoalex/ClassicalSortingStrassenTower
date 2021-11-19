import java.util.Arrays;

import java.util.Random;

public class sortingAlgorithms {

	public static void main(String[] args) {

		int quickArray[], mergeArray[];

		long start, end;

		//arrays of size

		int sizeArray[]={10000, 20000, 50000, 100000};

		for(int size: sizeArray) {

			System.out.println("\n\nSIZE: "+size);

			quickArray=getRandomArray(size);

			mergeArray=getRandomArray(size);

			System.out.printf("%30s%30s\n\n", "Type of sorting", "Time in ns");

			//calculate time for quick sort
			start=System.nanoTime();

			quickSort(quickArray, 0, size-1);

			end=System.nanoTime();

			System.out.printf("%30s%30d\n", "Quick Sort", end-start);
			

			//calculate time for merge sort
			start=System.nanoTime();
			
			mergeSort(mergeArray, 0, size-1);
			
			end=System.nanoTime();
			
			System.out.printf("%30s%30d\n", "Merge Sort", end-start);

		}

}

	//get random array from size 10000, 20000, 50000, 100000
	public static int[] getRandomArray(int size){

		int array[]=new int[size];
		
		Random rand=new Random();
		
		for(int i=0; i<array.length; i++)
		
		array[i]=rand.nextInt();
		
		return array;

	}

	//quick-sort 
	public static void quickSort(int array[], int low, int high) {

		if(low>high)
	
			return ;
	
		int pIndex=getPartitionIndex(array, low, high);
	
		quickSort(array, low, pIndex-1); // switch to low pivot 
	
		quickSort(array, pIndex+1, high); // switch to high pivot
	}

	//get partition index, from low-high
	public static int getPartitionIndex(int array[], int low,int high){

		int pivot=array[high];
	
		int pIndex=low; // place for first pivot item
	
		for(int i=low; i<high; i++){

			if(array[i]<pivot){ // if the new array item is smaller than first pivot item then switch to temp

				int temp=array[i];

				array[i]=array[pIndex];

				array[pIndex]=temp;

				pIndex++;
			}
		}

		array[high]=array[pIndex];

		array[pIndex]=pivot;

		return pIndex;
	}

	//merge sort
	public static void mergeSort(int array[], int low, int high) {

		if(low>=high)

			return;

		int mid=(low+high)/2;

		mergeSort(array, low, mid);

		mergeSort(array, mid+1, high);

		merge(array, low, mid, high);

	}

	public static void merge(int array[], int low, int mid, int high) {

		//low-mid

		//mid+1-high

		int index=0;

		int i=low;

		int j=mid+1;

		int ar[]=new int[high-low+1];

		while(i<=mid && j<=high) {

			if(array[i]<array[j]) {

				ar[index++]=array[i];

				i++;

			}

			else {

				ar[index++]=array[j];

				j++;

			}

		}

		while(i<=mid) {

			ar[index++]=array[i++];
		}

		while(j<=high) {

			ar[index++]=array[j++];
		}

		System.arraycopy(ar, 0, array, low, high-low+1);
	}

}