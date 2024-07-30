import java.util.Scanner;

public class FinancialForecasting 
{
    public static double calculateFutureValue(double initialValue, double[] growthRates, int index) 
    {
        // Base case: No more growth rates to apply
        if (index >= growthRates.length) 
        {
            return initialValue;
        }
        // Recursive case: Apply the current growth rate and recurse
        double newValue = initialValue * (1 + growthRates[index]);
        return calculateFutureValue(newValue, growthRates, index + 1);
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter initial value:");
        double initialValue = sc.nextDouble(); // Example initial value
        System.out.println("Enter the no of growthrates to be stored:");
        int n = sc.nextInt();
        double[] growthRates = new double[n];
        for(int i = 0; i < n; i++)
        {
            System.out.println("Enter growthrate(in %):");
            double growthrate = sc.nextDouble();
            growthRates[i] = growthrate/100;
        }
        //double[] growthRates = {0.05, 0.03, 0.07}; // Example growth rates (5%, 3%, 7%)

        double futureValue = calculateFutureValue(initialValue, growthRates, 0);
        System.out.println();
        System.out.println("Future Value: " + futureValue);
        System.out.println("Thank you");
        sc.close();
    }
}
