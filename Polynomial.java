public class Polynomial {
    private double[] coefficients;

    public Polynomial(double... coefficients) {
        this.coefficients = coefficients;
    }

    public void norm() {
        double factor = 1 / coefficients[coefficients.length - 1];
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i] * factor;
        }
    }

    public ComplexNumber[] getStartingPoints() {
        int degree = coefficients.length - 1;

        double theta = 2 * Math.PI / degree;
        double offset = Math.PI / (2 * degree);
        ComplexNumber[] guesses = new ComplexNumber[degree];
        for (int k = 0; k < degree; k++) {
            double realPart = Math.cos(k * theta + offset);
            double imagPart = Math.sin(k * theta + offset);
            guesses[k] = new ComplexNumber(realPart, imagPart);
        }
        return guesses;
    }

    public ComplexNumber evaluatePolynomial(ComplexNumber x) {
        ComplexNumber result = new ComplexNumber(0, 0);
        
        ComplexNumber notCoefficient = new ComplexNumber(1, 0);
        for (int i = 0; i < coefficients.length; i++) {
            result = ComplexNumber.add(result, ComplexNumber.multiply(coefficients[i], notCoefficient));

            notCoefficient = ComplexNumber.multiply(notCoefficient, x);
        }
        
        return result;
    }

    public ComplexNumber evaluateDerivative(ComplexNumber x) {
        ComplexNumber result = new ComplexNumber(0, 0);
        
        ComplexNumber notCoefficient = new ComplexNumber(1, 0);
        for (int i = 1; i < coefficients.length; i++) {

            ComplexNumber resultOfThisTerm = ComplexNumber.multiply(
                coefficients[i], 
                notCoefficient
            );

            resultOfThisTerm = ComplexNumber.multiply(
                i, 
                resultOfThisTerm
            );

            result = ComplexNumber.add(
                result, 
                resultOfThisTerm
            );

            notCoefficient = ComplexNumber.multiply(notCoefficient, x);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int degree = coefficients.length - 1;

        for (int i = degree; i >= 0; i--) {
            double coefficient = coefficients[i];
            if (coefficient != 0) {
                if (sb.length() != 0) {
                    if (coefficient > 0) {
                        sb.append(" + ");
                    } else {
                        sb.append(" - ");
                        coefficient = -coefficient;
                    }
                } else if (coefficient < 0) {
                    sb.append("-");
                    coefficient = -coefficient;
                }
                
                if (Math.abs(coefficient) != 1 || i == 0) {
                    sb.append(coefficient);
                }
                
                if (i > 0) {
                    sb.append("x");
                    if (i > 1) {
                        sb.append("^").append(i);
                    }
                }
            }
        }

        return sb.toString();
    }
}
