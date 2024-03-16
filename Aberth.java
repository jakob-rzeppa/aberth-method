class Aberth {
    /**
     * TODO
     * 1. Checking Polynomials / Setting Params
     * 2. WHILE not finished
     * -> FOREACH Root
     * --> get p(z_i) with z_i = current approx of root
     * --> get p'(z_i)
     * --> get p(z_i)/p'(z_i)
     * --> FOREACH root (var j)
     * --> add 1/(z_i - z_j)
     * --> calc nominator
     * --> calc denominator
     * --> devide nominator by denomainator
     * --> subtract the result from z_i and save the new root
     * --> print
     * --> check if done (for all)
     * -> check if done
     * 3. Print and test
     */

    private static boolean isValid(Polynomial polynomial) {
        if (polynomial.getCoefficients().length >= 1) {
            return false;
        }
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

            ComplexNumber fractionInSum = ComplexNumber.divide(
                    new ComplexNumber(1, 0),
                    ComplexNumber.subtract(roots[currentIndex],
                            roots[i]));

            sum = ComplexNumber.add(
                    sum,
                    fractionInSum);
        }

        ComplexNumber numerator = fraction;

        ComplexNumber denominator = ComplexNumber.subtract(
                new ComplexNumber(1, 0),
                ComplexNumber.multiply(fraction, sum));

        ComplexNumber correction = ComplexNumber.divide(
                numerator,
                denominator);

        return correction;
    }

    private static ComplexNumber[] aberth(Polynomial polynomial, double accuracy) {
        if (!isValid(polynomial)) {
            System.err.println("Polynomial is not valid!");
            return null;
        }

        System.out.println(polynomial);

        polynomial.norm();
        System.out.println();
        System.out.println("Normalized: ");
        System.out.println(polynomial);

        ComplexNumber[] roots = polynomial.getStartingPoints();

        System.out.println();
        System.out.println("Starting Points: ");
        for (ComplexNumber root : roots) {
            System.out.println(root);
        }

        ComplexNumber[] corrections = new ComplexNumber[roots.length];
        boolean done = false;
        int counter = 0;

        // until accurate enough
        while (!done && counter <= 100) {

            // For every root
            for (int i = 0; i < roots.length; i++) {
                corrections[i] = calcCorrection(polynomial, roots, i);

                roots[i] = ComplexNumber.subtract(
                        roots[i],
                        corrections[i]);
            }

            System.out.println();
            System.out.println("Counter: " + counter++);
            for (ComplexNumber root : roots) {
                System.out.println(root);
            }

            done = isAccurrateEnough(corrections, accuracy);
        }

        System.out.println();
        System.out.println("Test:");
        for (ComplexNumber root : roots) {
            System.out.println("p(" + root + ") = " + polynomial.evaluatePolynomial(root));
        }

        if (counter >= 100) {
            System.out.println(" ----------- FAILED ---------- ");
        }

        return roots;
    }

    public static void main(String[] args) {
        // TODO Input
        Polynomial p = new Polynomial(6);
        aberth(p, 0.0001);
    }
}