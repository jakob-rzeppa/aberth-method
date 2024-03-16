class Aberth {
    /**
     * TODO
     * 1. Checking Polynomials / Setting Params
     * 2. WHILE not finished
     *  -> FOREACH Root
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

    private static boolean isValid(Polynomial polynomial) {
        return true;
    }

    private static boolean isAccurrateEnough(ComplexNumber[] corrections) {
        return false;
    }

    private static ComplexNumber[] aberth(Polynomial polynomial, ComplexNumber accuracy) {
        if (!isValid(polynomial)) {
            System.err.println("Polynomial is not valid!");
            return null;
        }

        ComplexNumber[] roots = polynomial.getStartingPoints();

        ComplexNumber[] corrections = new ComplexNumber[roots.length];
        boolean done = false;
        int counter = 0;

        // until accurate enough
        do {
            System.out.println("Counter: " + counter++);


            // For every root
            for (int i = 0; i < roots.length; i++) {
                ComplexNumber y = polynomial.evaluatePolynomial(roots[i]);
                ComplexNumber yDerivative = polynomial.evaluateDerivative(roots[i]);

                ComplexNumber fraction = ComplexNumber.divide(y, yDerivative);
                System.out.println("Fraction: " + fraction);

                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int j = 0; j < roots.length; j++) {
                    if (j == i) {
                        continue;
                    }
                    sum = ComplexNumber.add(
                        sum, 
                        ComplexNumber.divide(
                            new ComplexNumber(1, 0), 
                            ComplexNumber.subtract(roots[i], roots[j])));
                }

                ComplexNumber numerator = fraction;

                ComplexNumber denominator = ComplexNumber.subtract(
                    new ComplexNumber(1, 0),
                    ComplexNumber.multiply(fraction, sum)
                );

                corrections[i] = ComplexNumber.divide(
                    numerator,
                    denominator
                );

                roots[i] = ComplexNumber.subtract(
                    roots[i],
                    corrections[i]
                );
            }

            if (isAccurrateEnough(corrections)) {
                System.out.println("Roots were found!");
            } else {
                System.out.println("Roots were not found or invalid!");
            }
        } while (!done);
        
        return null;
    }

    public static void main(String[] args) {
        double[] d = new double[] {
            7,
            1,
            1,
            8,
            -4
        };
        Polynomial p = new Polynomial(d);
        aberth(p, new ComplexNumber(0.001, 0.001));
    }
}