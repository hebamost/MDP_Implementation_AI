import java.awt.*;

public class policy {

    public String[][] policy = { { "     ", "UP", "" }, { "UP", "UP", "UP" }, { "UP", "UP", "UP" } };
    public String[][] newPolicy = { { "     ", "", "" }, { "", "", "" }, { "", "", "" } };
    public String[][] finalPolicy = new String[3][3];
    public String[][] prevPolicy=  new String[3][3];
    
    public boolean eq = false;


    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public int count = 0;
    public int outerCount = 0;
    public long time = 0;
    public double epsilon = 0.01;
    
    
    public static void copyArray(String[][] arr1, String [][] arr2) {
        for(int i=0; i<arr1.length;i++){
            for(int j=0; j<arr2.length; j++){
                arr1[i][j]=arr2[i][j];
            }
        }
    }

    public Double policyEval(Double[][] util, int i, int j, Double[][] re, double gamma, String[][] policy) {

        Point[] validDirections;
        double v = 0;
        // *calc up direction value*//
        if (policy[i][j] == "UP") {
            validDirections = value.getPossibleDirections(new Point(i, j), UP);
            v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                    + gamma * util[validDirections[0].x][validDirections[0].y])
                    + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                    + gamma * util[validDirections[1].x][validDirections[1].y])
                    + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                    + gamma * util[validDirections[2].x][validDirections[2].y]);
        }

        // *calc left direction value*//
        if (policy[i][j] == "LEFT") {
            validDirections = value.getPossibleDirections(new Point(i, j), LEFT);
            v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                    + gamma * util[validDirections[0].x][validDirections[0].y])
                    + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                    + gamma * util[validDirections[1].x][validDirections[1].y])
                    + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                    + gamma * util[validDirections[2].x][validDirections[2].y]);

        }

        // *calc right direction value*//
        if (policy[i][j] == "RIGHT") {
            validDirections = value.getPossibleDirections(new Point(i, j), RIGHT);
            v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                    + gamma * util[validDirections[0].x][validDirections[0].y])
                    + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                    + gamma * util[validDirections[1].x][validDirections[1].y])
                    + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                    + gamma * util[validDirections[2].x][validDirections[2].y]);

        }

        // *calc down direction value*//
        if (policy[i][j] == "DOWN") {
            validDirections = value.getPossibleDirections(new Point(i, j), DOWN);
            v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                    + gamma * util[validDirections[0].x][validDirections[0].y])
                    + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                    + gamma * util[validDirections[1].x][validDirections[1].y])
                    + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                    + gamma * util[validDirections[2].x][validDirections[2].y]);

        }

        return Math.round(v * 100.0) / 100.0;

    }

    public Double policyExtraction(Double[][] util, int i, int j, Double[][] re, double gamma) {
        double max = -10000;

        // *calc up direction value*//
        Point[] validDirections = value.getPossibleDirections(new Point(i, j), UP);
        double v = 0.8
                * (re[validDirections[0].x][validDirections[0].y]
                + gamma * util[validDirections[0].x][validDirections[0].y])
                + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                + gamma * util[validDirections[1].x][validDirections[1].y])
                + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                + gamma * util[validDirections[2].x][validDirections[2].y]);

        if (v > max) {
            max = v;
            newPolicy[i][j] = "UP";
        }

        // *calc left direction value*//
        validDirections = value.getPossibleDirections(new Point(i, j), LEFT);
        v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                + gamma * util[validDirections[0].x][validDirections[0].y])
                + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                + gamma * util[validDirections[1].x][validDirections[1].y])
                + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                + gamma * util[validDirections[2].x][validDirections[2].y]);

        if (v > max) {
            max = v;
            newPolicy[i][j] = "LEFT";
        }

        // *calc right direction value*//
        validDirections = value.getPossibleDirections(new Point(i, j), RIGHT);
        v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                + gamma * util[validDirections[0].x][validDirections[0].y])
                + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                + gamma * util[validDirections[1].x][validDirections[1].y])
                + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                + gamma * util[validDirections[2].x][validDirections[2].y]);

        if (v > max) {
            max = v;
            newPolicy[i][j] = "RIGHT";
        }

        // *calc down direction value*//
        validDirections = value.getPossibleDirections(new Point(i, j), DOWN);
        v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
                + gamma * util[validDirections[0].x][validDirections[0].y])
                + 0.1 * (re[validDirections[1].x][validDirections[1].y]
                + gamma * util[validDirections[1].x][validDirections[1].y])
                + 0.1 * (re[validDirections[2].x][validDirections[2].y]
                + gamma * util[validDirections[2].x][validDirections[2].y]);

        if (v > max) {
            max = v;
            newPolicy[i][j] = "DOWN";
        }

        return Math.round(max * 100.0) / 100.0;
    }
    
    public boolean equal(String [][] arr1, String [][] arr2){
        for(int i=0; i<arr1.length;i++){
            for(int j=0; j<arr2.length; j++){
                if(arr1[i][j]!=arr2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public Double[][] mainAlgo(Double[][] util, Double[][] re) {
    	long start = System.currentTimeMillis();
    	
        count = 0;
        outerCount = 0;
        Double[][] uDash = new Double[3][3];
        Double[][] u = new Double[3][3];

        value.copyArray(uDash, util);
        double gamma = 0.99;
        int stop = 0;

        copyArray(finalPolicy, policy);
        copyArray(prevPolicy, policy);

        do {
        	copyArray(prevPolicy, finalPolicy);
            do {
                stop = 0;
                value.copyArray(u, uDash);
                for (int i = 2; i >= 0; i--) {
                    for (int j = 0; j < 3; j++) {
                        if (i == 0 && j == 0)
                            continue;
                        if (i == 0 && j == 2)
                            break;
                        uDash[i][j] = policyEval(u, i, j, re, gamma, prevPolicy);
                        if(Math.abs(uDash[i][j] - u[i][j]) <= epsilon)
                            stop++;
                    }
                }
                count++;
            } while (stop != 7);
            
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    if (i == 0 && j == 2)
                        break;
                    Double pi = policyExtraction(uDash, i, j, re, gamma);
                    if (pi > uDash[i][j]) {
                        uDash[i][j] = pi;
                        finalPolicy[i][j] = newPolicy[i][j];
                    }
                }
            }
            outerCount++;
            
            if(equal(prevPolicy, finalPolicy))
            	break;
        } while (!eq);

        long end = System.currentTimeMillis();
        time = end - start;
        return uDash;
    }

}