import java.util.Random;
import java.util.Scanner;

	public class ClassicalMatrixMulti {
		
		public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the base of squared matrices:");
			int n = input.nextInt();
			long start,end;
			int[][] firstMatrix = new int[n][n];
			int[][] secondMatrix = new int[n][n];
			int[][] finalMatrix = new int[n][n];
			Random r = new Random();
			int Low = 1;
			int High = 10;
			start=System.nanoTime();
			System.out.println("Generating first Matrix...");			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					firstMatrix[i][j] = r.nextInt(High-Low) + Low;
					System.out.print(firstMatrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("Generating second Matrix...");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					secondMatrix[i][j] = r.nextInt(High-Low) + Low;
					System.out.print(secondMatrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("Multiplying the matrices...");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						finalMatrix[i][j] = finalMatrix[i][j] + firstMatrix[i][k] * secondMatrix[k][j];
					}
				}
			}
			System.out.println("Final Matrix is:");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(finalMatrix[i][j] + " ");
				}
				System.out.println();
			}
			input.close();
			end=System.nanoTime();
			
			System.out.println("");
			System.out.printf("%55s\n", "Classical Matrix Multiplication" );
			System.out.printf("%30s%30d\n", "Test time in ns: " , end-start);
		}
}