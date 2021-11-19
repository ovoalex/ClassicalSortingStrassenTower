import java.util.Random;
import java.util.Scanner;

public class StrassenMatrixMulti {
	public int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		int[][] R = new int[n][n];
		/** base case **/
		if (n == 1)
			R[0][0] = A[0][0] * B[0][0];
		else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];
			
			//Dividing matrix A into 4 halves
			split(A, A11, 0, 0);
			split(A, A12, 0, n / 2);
			split(A, A21, n / 2, 0);
			split(A, A22, n / 2, n / 2);
			//Dividing matrix B into 4 halves
			split(B, B11, 0, 0);
			split(B, B12, 0, n / 2);
			split(B, B21, n / 2, 0);
			split(B, B22, n / 2, n / 2);
			/**
			* M1 = (A11 + A22)(B11 + B22) M2 = (A21 + A22) B11 M3 = A11 (B12 -
			* B22) M4 = A22 (B21 - B11) M5 = (A11 + A12) B22 M6 = (A21 - A11)
			* (B11 + B12) M7 = (A12 - A22) (B21 + B22)
			**/
			//M1 = (A11 + A22)(B11 + B22) M2
			int[][] M1 = multiply(add(A11, A22), add(B11, B22));
			//M2 = (A21 + A22) B11
			int[][] M2 = multiply(add(A21, A22), B11);
			// M3 = A11 (B12 -B22)
			int[][] M3 = multiply(A11, sub(B12, B22));
			//M4 = A22 (B21 - B11)
			int[][] M4 = multiply(A22, sub(B21, B11));
			//M5 = (A11 + A12)
			int[][] M5 = multiply(add(A11, A12), B22);
			//M6 = (A21 - A11)(B11 + B12)
			int[][] M6 = multiply(sub(A21, A11), add(B11, B12));
			//M7 = (A12 - A22) (B21 + B22)
			int[][] M7 = multiply(sub(A12, A22), add(B21, B22));
			/**
			* C11 = M1 + M4 - M5 + M7 C12 = M3 + M5 C21 = M2 + M4 C22 = M1 - M2
			* + M3 + M6
			**/
			int[][] C11 = add(sub(add(M1, M4), M5), M7);
			int[][] C12 = add(M3, M5);
			int[][] C21 = add(M2, M4);
			int[][] C22 = add(sub(add(M1, M3), M2), M6);
			/** join 4 halves into one result matrix **/
			join(C11, R, 0, 0);
			join(C12, R, 0, n / 2);
			join(C21, R, n / 2, 0);
			join(C22, R, n / 2, n / 2);
		}
		/** return result **/
		return R;
	}
	
	/** Function to sub two matrices **/
	public int[][] sub(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}
	/** Function to add two matrices **/
	public int[][] add(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}
	/** Function to split parent matrix into child matrices **/
	public void split(int[][] P, int[][] C, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				C[i1][j1] = P[i2][j2];
	}
	/** Function to join child matrices into parent matrix **/
	public void join(int[][] C, int[][] P, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				P[i2][j2] = C[i1][j1];
	}
	/** Main function **/
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StrassenMatrixMulti strassenMatixMultiplication = new StrassenMatrixMulti();
		System.out.println("Enter the base of squared matrices:");
		int n = input.nextInt();
		long start, end;
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
		finalMatrix = strassenMatixMultiplication.multiply(firstMatrix, secondMatrix);
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
		System.out.printf("%55s\n", "Strassen Matrix Multiplication" );
		System.out.printf("%30s%30d\n", "Test time in ns: " , end-start);
	}
}