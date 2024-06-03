public class Driver {
    public static void main(String[] args) {
        // Creating a default polynomial and evaluating it
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3)); // Should print 0 since it's a zero polynomial

        // Coefficients and corresponding exponents for the first polynomial
        double[] c1 = {6, 5};  // Coefficients
        int[] exp1 = {0, 3};   // Corresponding exponents (6 + 5x^3)

        // Creating the first polynomial
        Polynomial p1 = new Polynomial(c1, exp1);

        // Coefficients and corresponding exponents for the second polynomial
        double[] c2 = {-2, -9}; // Coefficients
        int[] exp2 = {1, 4};    // Corresponding exponents (-2x^1 - 9x^4)

        // Creating the second polynomial
        Polynomial p2 = new Polynomial(c2, exp2);

        // Adding two polynomials
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));  // Evaluating the sum at x=0.1

        // Checking for roots
        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }
    }
}
