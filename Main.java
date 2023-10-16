import java.math.BigDecimal;
import java.util.Scanner;
import java.math.RoundingMode;
import java.math.*;

public class Main {
    
    public static BigDecimal pow(BigDecimal base, BigDecimal exponent) {
        BigDecimal expResult = BigDecimal.ZERO;
        int signOf2 = exponent.signum();

        // Perform X^(A+B)=X^A*X^B (B = remainder)
        double dn1 = base.doubleValue();
        // Compare the same row of digits according to context
        BigDecimal n2 = exponent.multiply(new BigDecimal(signOf2)); 
        BigDecimal remainderOf2 = n2.remainder(BigDecimal.ONE);
        BigDecimal n2IntPart = n2.subtract(remainderOf2);
        BigDecimal intPow = base.pow(n2IntPart.intValueExact());
        BigDecimal doublePow = new BigDecimal(Math.pow(dn1, remainderOf2.doubleValue()));
        expResult = intPow.multiply(doublePow);

        // Fix negative power
        if (signOf2 == -1)
            expResult = BigDecimal.ONE.divide(expResult, RoundingMode.HALF_UP);
        return expResult;
    }
    
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        MathContext precision = new MathContext(1000000);
        boolean run = true;
        while (run) { 

            System.out.print("Enter the base, followed by the tetration value (a ^^ b): ");
        
            int a = kb.nextInt();
            int b = kb.nextInt();

            BigDecimal bigResult = new BigDecimal(0);

            if (b < 0) { 
                System.out.println("Positive integers only -- ");
            }

            if (b == 0) {

                System.out.println(1 + "\n");

            }

            else {

                BigDecimal bigA = new BigDecimal(a);
                bigResult = bigA;
                int exponent = b;
                double result;

                for (bigResult = bigA; b > 1; b--){  
                    bigResult = pow(bigA, bigResult);
                }

                
                System.out.println(bigResult.round(precision) + "\n");
            
            }
        
        }
        
        kb.close();
        
    }
}