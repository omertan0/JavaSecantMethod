import java.util.Scanner;

public class JavaSecantMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Initial Guess (xi-1): ");
        double xi_1 = scanner.nextDouble();

        System.out.print("Second Guess (xi): ");
        double xi = scanner.nextDouble();

        System.out.print("Maximum Number of Iterations: ");
        int maxIter = scanner.nextInt();

        System.out.print("Approximation Error Tolerance (eps_s): ");
        double eps_s = scanner.nextDouble();

        scanner.close();

        secant(xi_1, xi, maxIter, eps_s);
    }

    public static void secant(double xi_1, double xi, int maxIter, double eps_s) {
        double xr = 0;
        double eps_a = 100;
        int iter = 0;
        boolean rootFound = false;

        System.out.println("Iteration | xi-1       | xi         | xr         | eps_a");
        System.out.println("-------------------------------------------------------------");

        while (iter < maxIter) {
            double f_xi_1 = f(xi_1);
            double f_xi = f(xi);

            if (f_xi_1 == f_xi) {
                System.out.println("f(xi-1) and f(xi) are equal. Division by zero risk.");
                return;
            }

            xr = xi - (f_xi * (xi_1 - xi)) / (f_xi_1 - f_xi);

            if (iter > 0) {
                eps_a = Math.abs((xr - xi) / xr);
            }

            iter++;
            System.out.printf("%-10d | %-10.6f | %-10.6f | %-10.6f | %-10.6f%n",
                    iter, xi_1, xi, xr, eps_a);

            if (Math.abs(f(xr)) < 1e-9 || eps_a < eps_s) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Root found: " + xr);
                rootFound = true;
                break;
            }

            xi_1 = xi;
            xi = xr;
        }

        if (!rootFound) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Maximum number of iterations reached. Root not found.");
        }
    }

    public static double f(double x) {
        return Math.log(x); // Natural logarithm
    }
}
