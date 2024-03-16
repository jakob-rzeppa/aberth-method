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

    private static boolean isValid(double[] polynomial) {
        return true;
    }

    private static boolean checkResult() {
        return true;
    }

    private static ComplexNumber[] getStartingPoints(double[] polynomial) {
        //TODO Temp
        ComplexNumber[] result = new ComplexNumber[polynomial.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ComplexNumber(i, 0);
        }
        return result;

        /*double t1 = (polynomial[1].isNull()) ? 1 : p.coefficients[1];
        double t2 = (p.coefficients[p.degree] == 0) ? 1: p.coefficients[p.degree];

        double radius = Math.abs((p.degree * p.coefficients[0]) / (2 * t1)) + Math.abs(p.coefficients[p.degree - 1] / (2 * p.degree * t2));
        double theta = 2 * Math.PI / p.degree;
        double offset = Math.PI / (2 * p.degree);
        ComplexNumber[] roots = new ComplexNumber[polynomial.length - 1];

        System.out.println("iteration: 0");
        for (int i = 0; i < p.degree; i++) {
            roots[i] = new Complex(radius * Math.cos(i * theta + offset), radius * Math.sin(i * theta + offset));
            System.out.println("i = " + i + ": " + roots[i].round(accuracyDecimalPlaces));
        }
        System.out.println();
        return roots;*/
    }

    public static ComplexNumber evaluatePolynomial(double[] polynomial, ComplexNumber x) {
        ComplexNumber result = new ComplexNumber(0, 0);
        
        ComplexNumber notCoefficient = new ComplexNumber(1, 0);
        for (int i = 0; i < polynomial.length; i++) {
            result = ComplexNumber.add(result, ComplexNumber.multiply(polynomial[i], notCoefficient));

            notCoefficient = ComplexNumber.multiply(notCoefficient, x);
        }
        
        return result;
    }

    public static ComplexNumber evaluateDerivative(double[] polynomial, ComplexNumber x) {
        ComplexNumber result = new ComplexNumber(0, 0);
        
        ComplexNumber notCoefficient = new ComplexNumber(1, 0);
        for (int i = 1; i < polynomial.length; i++) {
            ComplexNumber extraFractorFromDerivative = new ComplexNumber(i, 0);

            ComplexNumber notCoefficientWithDerivativeExtraFactor = ComplexNumber.multiply(
                extraFractorFromDerivative, 
                notCoefficient
            );

            ComplexNumber resultOfThisTerm = ComplexNumber.multiply(
                polynomial[i], 
                notCoefficientWithDerivativeExtraFactor
            );

            result = ComplexNumber.add(
                result, 
                resultOfThisTerm
            );

            notCoefficient = ComplexNumber.multiply(notCoefficient, x);
        }

        return result;
    }

    private static ComplexNumber[] aberth(double[] polynomial) {
        if (!isValid(polynomial)) {
            System.err.println("Polynomial is not valid!");
            return null;
        }

        ComplexNumber[] roots = getStartingPoints(polynomial);

        boolean done = false;
        int counter = 0;
        do {
            System.out.println("Counter: " + counter++);
            for (int i = 0; i < roots.length; i++) {
                ComplexNumber y = evaluatePolynomial(polynomial, roots[i]);
                ComplexNumber yDerivative = evaluateDerivative(polynomial, roots[i]);

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

                roots[i] = ComplexNumber.subtract(
                    roots[i],
                    ComplexNumber.divide(numerator, denominator)
                );
            }
        } while (!done);

        if (checkResult()) {
            System.out.println("Roots were found!");
        } else {
            System.out.println("Roots were not found or invalid!");
        }
        
        return null;
    }

    public static void main(String[] args) {
        double[] p = new double[] {
            0,
            0,
            1
        };
        aberth(p);
    }
}