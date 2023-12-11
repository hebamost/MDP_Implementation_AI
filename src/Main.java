
public class Main {

	public static void main(String[] args) {

		controller ctrl = new controller();
		
		double[] r = { 100, 3, 0, -3 };

		value v = new value();
		policy p = new policy();
		for (int k = 0; k < 4; k++) {
			System.out.println(" ");

			System.out.println("____________________________________________________________________________________________\n\n*** Result when r = " + r[k] + " ***");
			System.out.println(" ");

			Double[][] util = { { r[k], 0.0, 10.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } };
			Double[][] re = { { 0.0, -1.0, 0.0 }, { -1.0, -1.0, -1.0 }, { -1.0, -1.0, -1.0 } };

			System.out.println("\n-------------------value iteration-------------------");
			Double[][] resV = v.optValue(util, re);
			ctrl.printUtil(resV);
			ctrl.printPolic(v.policy);
			System.out.println("number of iterations = " + v.count);
			System.out.println("time taken: " + v.time + " ms");
			
			System.out.println("\n-------------------policy iteration-------------------");
			System.out.println("init policy: ");
			ctrl.printPolic(p.policy);
			Double[][] resP = p.mainAlgo(util, re);
			ctrl.printUtil(resP);
			ctrl.printPolic(p.finalPolicy);
			System.out.println("number of iterations = " + p.outerCount);
			System.out.println("time taken: " + p.time + " ms");
		}
	}
}