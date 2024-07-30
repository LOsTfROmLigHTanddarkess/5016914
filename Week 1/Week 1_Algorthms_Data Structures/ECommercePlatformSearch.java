import java.util.Arrays;
import java.util.Scanner;

public class ECommercePlatformSearch 
{

    public Product linearSearch(Product[] product, String name)
    {
        for(int i = 0; i<product.length; i++)
        {
            System.out.println(product[i].productName);
            if(product[i].productName.equals(name))
            {
                return product[i];
            }
        }
        return null;
    }
    public Product binarySearch(Product[] product, String name)
    {
        Arrays.sort(product, (a,b) -> a.productName.compareTo(b.productName));
        int low = 0;
        int high = product.length - 1;
 
        while (low <= high) 
        {
            int mid = (low + high) / 2;
            String guess = product[mid].productName;
            if (product[mid].productName.equals(name)) 
            {
                return product[mid];
            } 
            else if (guess.compareTo(name) > 0) 
            {
                high = mid - 1;
            } 
            else 
            {
                low = mid + 1;
            }
        }
        return null;
    }
    public static void main(String args[])
    {
        ECommercePlatformSearch ecps = new ECommercePlatformSearch();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array:");
        int n = sc.nextInt();
        Product[] product = new Product[n];
        for(int i =0; i<n ; i++)
        {
            System.out.println("Enter the space separated Product details in the following order[Name Category]:");
            product[i] = new Product(i, sc.next(), sc.next());
        }
        // product[0] = new Product(0, "Godrej", "Soap");
        // product[1] = new Product(1, "Nirma", "Soap");
        // product[2] = new Product(2, "Sunsilk", "Shampoo");
        // product[3] = new Product(3, "Vivel", "Lotion");
        // product[4] = new Product(4, "Colgate", "Toothpaste");
        // product[5] = new Product(5, "Beardo", "Shaving");
        while(true)
        {
            System.out.println("For binary search press 0\nFor linear search press 1\nTo exit press 2\nEnter search Medthod:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                System.out.println("Enter the name of the product:");
                String Name = sc.next();
                Product pt = ecps.linearSearch(product, Name);
                System.out.println(pt.productId+" "+pt.productName+" "+pt.category);
                break;
                case 0:
                System.out.println("Enter the name of the product:");
                String productName = sc.next();
                Product p = ecps.binarySearch(product, productName);
                System.out.println(p.productId+" "+p.productName+" "+p.category);
                break;
            }
            if(choice == 2)
            {
                System.out.println("Thank you");
                break;
            }
        }
        sc.close();
    }
}

class Product
{
    public int productId;
    public String productName;
    public String category;
    Product(int productId,String productName,String category)
    {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}