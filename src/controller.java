
public class controller {
	
	public void printUtil(Double[][] arr) {
		System.out.println("----the utility----");
        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + "     ");
            }
        }
        System.out.println("\n");
	}
	
	public void printPolic(String[][] arr) {
		System.out.println("----the Policy----");
        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + "    ");
            }
        }
        System.out.println("\n");
	}

}
