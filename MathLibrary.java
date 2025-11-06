public class MathLibrary (

// Declare native methods

public native int add(int a, int b);

public native int subtract(int a, int b);

public native int multiply(int a, int b);

public native int divide(int a, int b);

static {

}

// Load the shared library (libMathLibrary.so) System.loadLibrary("MathLibrary");

public static void main(String[] args) {

MathLibrary math = new MathLibrary();

inta 20, b = 10;

System.out.println("Addition: " + math.add(a, b));

System.out.println("Subtraction: " + math.subtract(a, b));

System.out.println("Multiplication: " + math.multiply(a, b));

System.out.println("Division: " + math.divide(a, b));



}

}