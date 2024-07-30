import java.util.ArrayList;
import java.util.Scanner;

public class SortingCustomerOrders 
{
    public static ArrayList<Order> bubbleSort(ArrayList<Order> order)
    {
        for(int i = 0; i<order.size(); i++)
        {
            for(int j = i+1; j<order.size();j++)
            {
                Order o = order.get(i);
                Order od = order.get(j);
                if(o.totalPrice > od.totalPrice)
                {
                    order.set(i, od);
                    order.set(j, o);
                }
            }
        }
        return null;
    }
    static void quickSort(ArrayList<Order> order, int start, int end)  
    {  
        if (start < end)  
        {  
            int p = partition(order, start, end); 
            quickSort(order, start, p - 1);  
            quickSort(order, p + 1, end);  
        }  
    }  

    static int partition (ArrayList<Order> order, int start, int end)  
    {  
        int pivot = order.get(end).totalPrice;
        int i = (start - 1);  
    
        for (int j = start; j <= end - 1; j++)  
        {  
            if (order.get(j).totalPrice < pivot)  
            {  
                i++; 
                Order o = order.get(i);
                Order od = order.get(j);
                order.set(i,od);
                order.set(j,o);
            }  
        }
        Order or = order.get(i+1);
        Order orde = order.get(end);
        order.set(i+1, orde); 
        order.set(end, or); 
        return (i + 1);  
    }  
    public static void main(String args[])
    {
        ArrayList<Order> order = new ArrayList<Order>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of list:");
        int n = sc.nextInt();
        for(int i = 0; i<n; i++)
        {
            Order o = new Order();
            System.out.println("Enter the order details in the following space separated format[CustomerName TotalPrice]:");
            o.orderId = i;
            o.customerName = sc.next();
            o.totalPrice = sc.nextInt();
            order.add(o);
        }
        System.out.println("For Bubble sort press 0\nFor Quick sort press 1\nEnter the sorting medthod:");
        int choice = sc.nextInt();
        System.out.println("Before sorting:");
        for(Order o : order)
        {
            System.out.println(o.orderId+" "+o.customerName+" "+o.totalPrice);
        }
        switch (choice) 
        {
            case 0:
                bubbleSort(order);
                break;
            case 1:
                quickSort(order, 0, n-1);
                break;
        }
        System.out.println("After sorting:");
        for(Order o : order)
        {
            System.out.println(o.orderId+" "+o.customerName+" "+o.totalPrice);
        }
        sc.close();
    }
}
class Order
{
    int orderId;
    String customerName;
    int totalPrice;
}