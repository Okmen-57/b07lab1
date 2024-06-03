import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    private double[] coeff;
    private int[] exp;

    public Polynomial() {
        this.coeff = new double[]{0};
        this.exp = new int[]{0};
    }

    public Polynomial(double[] coeff, int[] exp) {
        this.coeff = coeff;
        this.exp = exp;
    }

    public Polynomial(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        String[] terms = line.split("(?=[+-])");
        this.coeff = new double[terms.length];
        this.exp = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].trim();
            int xIndex = term.indexOf('x');
            if (xIndex == -1) {
                this.coeff[i] = Double.parseDouble(term);
                this.exp[i] = 0;
            } else {
                this.coeff[i] = Double.parseDouble(term.substring(0, xIndex));
                if (xIndex == term.length() - 1) {
                    this.exp[i] = 1;
                } else {
                    this.exp[i] = Integer.parseInt(term.substring(xIndex + 1));
                }
            }
        }
    }

    public Polynomial add(Polynomial other) {
        int newLength = this.coeff.length + other.coeff.length;
        double[] tempCoeff = new double[newLength];
        int[] tempExp = new int[newLength];

        int index = 0;
        for (int i = 0; i < this.coeff.length; i++, index++) {
            tempCoeff[index] = this.coeff[i];
            tempExp[index] = this.exp[i];
        }

        for (int i = 0; i < other.coeff.length; i++, index++) {
            tempCoeff[index] = other.coeff[i];
            tempExp[index] = other.exp[i];
        }

        for (int i = 0; i < tempExp.length; i++) {
            for (int j = i + 1; j < tempExp.length; j++) {
                if (tempExp[i] == tempExp[j]) {
                    tempCoeff[i] += tempCoeff[j];
                    tempCoeff[j] = 0;
                }
            }
        }

        int count = 0;
        for (double c : tempCoeff) {
            if (c != 0) count++;
        }

        double[] finalCoeff = new double[count];
        int[] finalExp = new int[count];
        index = 0;
        for (int i = 0; i < tempCoeff.length; i++) {
            if (tempCoeff[i] != 0) {
                finalCoeff[index] = tempCoeff[i];
                finalExp[index] = tempExp[i];
                index++;
            }
        }

        return new Polynomial(finalCoeff, finalExp);
    }

    public Polynomial multiply(Polynomial other) {
        int newLength = this.coeff.length * other.coeff.length;
        double[] tempCoeff = new double[newLength];
        int[] tempExp = new int[newLength];

        int index = 0;
        for (int i = 0; i < this.coeff.length; i++) {
            for (int j = 0; j < other.coeff.length; j++, index++) {
                tempCoeff[index] = this.coeff[i] * other.coeff[j];
                tempExp[index] = this.exp[i] + other.exp[j];
            }
        }

        for (int i = 0; i < tempExp.length; i++) {
            for (int j = i + 1; j < tempExp.length; j++) {
                if (tempExp[i] == tempExp[j]) {
                    tempCoeff[i] += tempCoeff[j];
                    tempCoeff[j] = 0;
                }
            }
        }

        int count = 0;
        for (double c : tempCoeff) {
            if (c != 0) count++;
        }

        double[] finalCoeff = new double[count];
        int[] finalExp = new int[count];
        index = 0;
        for (int i = 0; i < tempCoeff.length; i++) {
            if (tempCoeff[i] != 0) {
                finalCoeff[index] = tempCoeff[i];
                finalExp[index] = tempExp[i];
                index++;
            }
        }

        return new Polynomial(finalCoeff, finalExp);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coeff.length; i++) {
            result += coeff[i] * Math.pow(x, exp[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < coeff.length; i++) {
            if (i > 0 && coeff[i] > 0) writer.append('+');
            if (exp[i] == 0) {
                writer.append(String.format("%f", coeff[i]));
            } else if (exp[i] == 1) {
                writer.append(String.format("%fx", coeff[i]));
            } else {
                writer.append(String.format("%fx%d", coeff[i], exp[i]));
            }
        }
        writer.close();
    }
}
