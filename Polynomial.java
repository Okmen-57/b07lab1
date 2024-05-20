public class Polynomial {
    private double[] coef;

    public Polynomial() {
        this.coef = new double[]{0};
    }

    public Polynomial(double[] coef) {
        this.coef = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            this.coef[i] = coef[i];
        }
    }

    public Polynomial add(Polynomial other) {
        int max_len = Math.max(this.coef.length, other.coef.length);
        double[] resultCoef = new double[max_len];

        for (int i = 0; i < max_len; i++) {
            double thisC = 0;
            if (i < this.coef.length) {
                thisC = this.coef[i];
            }

            double otherC = 0;
            if (i < other.coef.length) {
                otherC = other.coef[i];
            }

            resultCoef[i] = thisC + otherC;
        }

        return new Polynomial(resultCoef);
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < coef.length; i++) {
            result += coef[i] * Math.pow(x, i);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}
