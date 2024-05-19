public class Polynomial {
    private double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double thisCoeff = 0;
            if (i < this.coefficients.length) {
                thisCoeff = this.coefficients[i];
            }

            double otherCoeff = 0;
            if (i < other.coefficients.length) {
                otherCoeff = other.coefficients[i];
            }

            resultCoefficients[i] = thisCoeff + otherCoeff;
        }

        return new Polynomial(resultCoefficients);
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}
