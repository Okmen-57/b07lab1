public class Driver {
    public static void main(String[] args) {
        Polynomial p0 = new Polynomial();
        System.out.println("p0(3) = " + p0.evaluate(3));

        double[] c1 = {6, -2, 5};  
        int[] exp1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, exp1);
        System.out.println("p1(1) = " + p1.evaluate(1));
	System.out.println("p1(2) = " + p1.evaluate(2));
        System.out.println("p1(-1) = " + p1.evaluate(-1));

        double[] c2 = {3, 1};
        int[] exp2 = {0, 2};
        Polynomial p2 = new Polynomial(c2, exp2);
	System.out.println("p2(2) = " + p2.evaluate(2));
        Polynomial sum = p1.add(p2);
        System.out.println("Sum of p1 and p2 evaluated at x=2: " + sum.evaluate(2));

        Polynomial product = p1.multiply(p2);
        System.out.println("Product of p1 and p2 evaluated at x=2: " + product.evaluate(2));

        if (sum.hasRoot(1)) {
            System.out.println("1 is a root of sum");
        } else {
            System.out.println("1 is not a root of sum");
        }

        double[] c3 = {4, -1};
        int[] exp3 = {0, 5};
        Polynomial p3 = new Polynomial(c3, exp3);
        System.out.println("p3(2) = " + p3.evaluate(2));

        double[] c4 = {2, 3, 1};
        int[] exp4 = {3, 1, 5};
        Polynomial p4 = new Polynomial(c4, exp4);
        System.out.println("p4(1) = " + p4.evaluate(1));
        System.out.println("p4(2) = " + p4.evaluate(2));

    }
}
