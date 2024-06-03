import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    private double[] coefficients;
    private int[] exponents;

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        String[] terms = line.split("(?=[+-])");  // Regex to keep the sign with the term
        this.coefficients = new double[terms.length];
        this.exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].trim();
            int xIndex = term.indexOf('x');
            if (xIndex == -1) {
                // It's a constant term
                this.coefficients[i] = Double.parseDouble(term);
                this.exponents[i] = 0;
            } else {
                // It's a term with 'x'
                this.coefficients[i] = Double.parseDouble(term.substring(0, xIndex));
                if (xIndex == term.length() - 1) {
                    // It's a linear term without explicit exponent
                    this.exponents[i] = 1;
                } else {
                    // It's a term with explicit exponent
                    this.exponents[i] = Integer.parseInt(term.substring(xIndex + 1));
                }
            }
        }
    }

    public Polynomial add(Polynomial other) {
        int newLength = this.coefficients.length + other.coefficients.length;
        double[] tempCoefficients = new double[newLength];
        int[] tempExponents = new int[newLength];

        int index = 0;
        for (int i = 0; i < this.coefficients.length; i++, index++) {
            tempCoefficients[index] = this.coefficients[i];
            tempExponents[index] = this.exponents[i];
        }

        for (int i = 0; i < other.coefficients.length; i++, index++) {
            tempCoefficients[index] = other.coefficients[i];
            tempExponents[index] = other.exponents[i];
        }

        // Simplification step
        for (int i = 0; i < tempExponents.length; i++) {
            for (int j = i + 1; j < tempExponents.length; j++) {
                if (tempExponents[i] == tempExponents[j]) {
                    tempCoefficients[i] += tempCoefficients[j];
                    tempCoefficients[j] = 0;
                }
            }
        }

        // Filter out zero coefficients and consolidate the polynomial
        int count = 0;
        for (double coef : tempCoefficients) {
            if (coef != 0) count++;
        }

        double[] finalCoefficients = new double[count];
        int[] finalExponents = new int[count];
        index = 0;
        for (int i = 0; i < tempCoefficients.length; i++) {
            if (tempCoefficients[i] != 0) {
                finalCoefficients[index] = tempCoefficients[i];
                finalExponents[index] = tempExponents[i];
                index++;
            }
        }

        return new Polynomial(finalCoefficients, finalExponents);
    }

    public Polynomial multiply(Polynomial other) {
        int newLength = this.coefficients.length * other.coefficients.length;
        double[] tempCoefficients = new double[newLength];
        int[] tempExponents = new int[newLength];

        int index = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++, index++) {
                tempCoefficients[index] = this.coefficients[i] * other.coefficients[j];
                tempExponents[index] = this.exponents[i] + other.exponents[j];
            }
        }

        // Simplification step
        for (int i = 0; i < tempExponents.length; i++) {
            for (int j = i + 1; j < tempExponents.length; j++) {
                if (tempExponents[i] == tempExponents[j]) {
                    tempCoefficients[i] += tempCoefficients[j];
                    tempCoefficients[j] = 0;
                }
            }
        }

        // Filter out zero coefficients and consolidate the polynomial
        int count = 0;
        for (double coef : tempCoefficients) {
            if (coef != 0) count++;
        }

        double[] finalCoefficients = new double[count];
        int[] finalExponents = new int[count];
        index = 0;
        for (int i = 0; i < tempCoefficients.length; i++) {
            if (tempCoefficients[i] != 0) {
                finalCoefficients[index] = tempCoefficients[i];
                finalExponents[index] = tempExponents[i];
                index++;
            }
        }

        return new Polynomial(finalCoefficients, finalExponents);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < coefficients.length; i++) {
            if (i > 0 && coefficients[i] > 0) writer.append('+');
            if (exponents[i] == 0) {
                writer.append(String.format("%f", coefficients[i]));
            } else if (exponents[i] == 1) {
                writer.append(String.format("%fx", coefficients[i]));
            } else {
                writer.append(String.format("%fx%d", coefficients[i], exponents[i]));
            }
        }
        writer.close();
    }
}
