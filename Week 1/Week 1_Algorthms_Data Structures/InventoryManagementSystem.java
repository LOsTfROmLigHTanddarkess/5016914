import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagementSystem 
{
    public class Product
    {
        int productId;
        String productName;
        int quantity;
        int price;
    }
    Scanner sc = new Scanner(System.in);
    public Product addProduct(int size)
    {
        Product p = new Product();
        p.productId = size+1;
        System.out.println("Enter the product name:");
        p.productName = sc.next();
        System.out.println("Enter the quantity:");
        p.quantity = sc.nextInt();
        System.out.println("Enter the product price:");
        p.price = sc.nextInt();
        return p;
    }
    public Product deleteProduct(ArrayList<Product> product)
    {
        System.out.println("Enter productId:");
        int id = sc.nextInt();
        for(Product p : product)
        {
            if(p.productId==id)
            {
                return p;
            }
        }
        System.out.println("Object does not exist");
        return null;
    }
    public Product updateProduct(int id)
    {
            Product p = new Product();
            p.productId = id;
            System.out.println("Enter the product name:");
            p.productName = sc.next();
            System.out.println("Enter the quantity:");
            p.quantity = sc.nextInt();
            System.out.println("Enter the product price:");
            p.price = sc.nextInt();
            return p;
    }
    public void display(ArrayList<Product> product)
    {
        for (Product p : product) 
        {
            System.out.println(p.productId+" "+p.productName+" "+p.quantity+" "+p.price);
        }
    }
    public static void main(String args[])
    {
        InventoryManagementSystem ivs = new InventoryManagementSystem();
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> product = new ArrayList<Product>();
        int id = 0;
        while(true)
        {
            System.out.println("For Adding product to inventory press 1\nFor updating product in inventory press 2\nFor deleting product in inventory press 3\nTo exit press 0:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                product.add(ivs.addProduct(id));
                id++;
                ivs.display(product);
                break;
                case 2:
                System.out.println("Enter the Id for Updation:");
                int new_id = sc.nextInt();
                if(new_id<=id)
                {
                    product.set(new_id-1,ivs.updateProduct(new_id));
                    ivs.display(product);
                }
                else
                {
                    System.out.println("Not found");
                    ivs.display(product);
                }
                break;
                case 3:
                product.remove(ivs.deleteProduct(product));
                ivs.display(product);
                break;
            }
        if(choice == 0) 
        {
            System.out.println("Thank you");
            break;
        }
        }
        sc.close();
    }
}
