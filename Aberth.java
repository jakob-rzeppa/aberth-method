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

    private static boolean isAccurrateEnough(ComplexNumber[] corrections, double accuracy) {
        for (ComplexNumber correction : corrections) {
            if (correction.getAbsoluteValue() >= accuracy) {
                return false;
            }
        }
        return true;
    }

    private static ComplexNumber calcCorrection(Polynomial polynomial, ComplexNumber[] roots, int currentIndex) {
        ComplexNumber y = polynomial.evaluatePolynomial(roots[currentIndex]);
        ComplexNumber yDerivative = polynomial.evaluateDerivative(roots[currentIndex]);

        ComplexNumber fraction = ComplexNumber.divide(y, yDerivative);

        ComplexNumber sum = new ComplexNumber(0, 0);
        for (int i = 0; i < roots.length; i++) {
            if (i == currentIndex) {
                continue;
            }
            sum = ComplexNumber.add(
                sum, 
                ComplexNumber.divide(
                    new ComplexNumber(1, 0), 
                    ComplexNumber.subtract(roots[currentIndex], roots[i])));
        }

        ComplexNumber numerator = fraction;

        ComplexNumber denominator = ComplexNumber.subtract(
            new ComplexNumber(1, 0),
            ComplexNumber.multiply(fraction, sum)
        );

        ComplexNumber correction = ComplexNumber.divide(
            numerator,
            denominator
        );

        return correction;
    }

    private static ComplexNumber[] aberth(Polynomial polynomial, double accuracy) {
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
            System.out.println();
            System.out.println("Counter: " + counter++);


            // For every root
            for (int i = 0; i < roots.length; i++) {
                corrections[i] = calcCorrection(polynomial, roots, i);

                roots[i] = ComplexNumber.subtract(
                    roots[i],
                    corrections[i]
                );
            }

            System.out.println("Corrections: ");
            for (ComplexNumber correction : corrections) {
                System.out.println(correction);
            }

            System.out.println("Roots: ");
            for (ComplexNumber root : roots) {
                System.out.println(root);
            }

            done = isAccurrateEnough(corrections, accuracy);
        } while (!done && counter <= 100);
        
        return null;
    }

    public static void main(String[] args) {
        double[] d = new double[] {
            7,
            1,
            1,
            8,
            1
        };
        Polynomial p = new Polynomial(d);
        aberth(p, 0.001);
    }
}