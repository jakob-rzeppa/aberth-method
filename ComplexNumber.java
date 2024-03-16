public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public boolean isNull() {
        if (real == 0 && imaginary == 0) {
            return true;
        } else {
            return false;
        }
    }

    public double getAbsoluteValue() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public static ComplexNumber add(ComplexNumber first, ComplexNumber secound) {
        return new ComplexNumber(first.real + secound.real, first.imaginary + secound.imaginary);
    }

    public static ComplexNumber subtract(ComplexNumber first, ComplexNumber secound) {
        return new ComplexNumber(first.real - secound.real, first.imaginary - secound.imaginary);
    }

    public static ComplexNumber multiply(double first, ComplexNumber secound) {
        return ComplexNumber.multiply(new ComplexNumber(first, 0), secound);
    }

    public static ComplexNumber multiply(ComplexNumber first, ComplexNumber secound) {
        double realPart = first.real * secound.real - first.imaginary * secound.imaginary;
        double imaginaryPart = first.real * secound.imaginary + first.imaginary * secound.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public static ComplexNumber divide(ComplexNumber first, ComplexNumber secound) {
        double denominator = secound.real * secound.real + secound.imaginary * secound.imaginary;

        if (denominator == 0d) {
            throw new RuntimeException("cant devide by 0");
        }

        double realPart = (first.real * secound.real + first.imaginary * secound.imaginary) / denominator;
        double imaginaryPart = (first.imaginary * secound.real - first.real * secound.imaginary) / denominator;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public static ComplexNumber power(ComplexNumber base, int exponent) {
        if (exponent < 0) {
            throw new RuntimeException("Can't use negative Exponent!");
        }
        if (exponent == 0) {
            return new ComplexNumber(1d, 1d);
        }

        ComplexNumber result = base;
        for (int i = 1; i < exponent; i++) {
            result = ComplexNumber.multiply(result, base);
        }

        return result;
    }

    @Override
    public String toString() {
        String realDisplay = String.format("%.4f", real).substring(0,
                Math.min(String.format("%.4f", real).length(), 5));
        String imaginaryDisplay = String.format("%.4f", imaginary).substring(0,
                Math.min(String.format("%.4f", imaginary).length(), 5));
        return "(" + realDisplay + " + " + imaginaryDisplay + "i)";
    }
}
