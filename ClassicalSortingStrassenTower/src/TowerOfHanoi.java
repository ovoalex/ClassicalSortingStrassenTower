import java.util.Scanner;

public class TowerOfHanoi {

	public static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod){
		
		if (n == 1){
			
			System.out.println("Move disk 1 from rod " + from_rod + " to rod " + to_rod);
			
			return;
		}
		towerOfHanoi(n-1, from_rod, aux_rod, to_rod);
		
		System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);
		
		towerOfHanoi(n-1, aux_rod, to_rod, from_rod);
	}

	public static void main (String[] args) {
		long start,end;
	
		start=System.nanoTime();
		
		Scanner kb = new Scanner(System.in); 
		
		System.out.print("Enter number to test: ");	
		
		int n = kb.nextInt();
		
		towerOfHanoi(n, 'A','B','C'); // A, B and C are names of rods
		
		end=System.nanoTime();
	
		System.out.println("");
		System.out.printf("%45s\n", "Tower Of Hanoi" );
		System.out.printf("%30s%30d\n", "Test time in ns: " , end-start);
	}
}