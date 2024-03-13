class Aberth {
    /**
     * TODO
     * 1. Checking Polynomials / Setting Params
     * 2. WHILE not finished
     *  -> FOREACH Polynomial
     *   --> get p(z_i) with z_i = current approx of root
     *   --> get p'(z_i)
     *   --> get p(z_i)/p'(z_i)
     *   --> FOREACH root (var j)
     *       --> add 1/(z_i - z_j)
     *   --> calc nominator
     *   --> calc denominator
     *   --> devide nominator by denomainator
     *   --> subtract the result from z_i and save the new root
     *   --> print
     *   --> check if done (for all)
     *  -> check if done
     * 3. Print and test
     */

    private static boolean isValid(ComplexNumber[] polynomial) {
        return true;
    }

    private static boolean checkResult() {
        return true;
    }

    private static ComplexNumber[] aberth(ComplexNumber[] polynomial) {
        if (isValid(polynomial)) {
            System.err.println("Polynomial is not valid!");
            return null;
        }

        boolean done = false;
        do {

        } while (!done);

        if (checkResult()) {
            System.out.println("Roots were found!");
        } else {
            System.out.println("Roots were not found or invalid!");
        }
        
        return null;
    }

    public static void main(String[] args) {

    }
}