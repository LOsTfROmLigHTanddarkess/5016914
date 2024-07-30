import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryManagementSystem 
{
    static Scanner sc = new Scanner(System.in);
    public static Book linearSearch(ArrayList<Book> book)
    {
        System.out.println("Enter the name of the Book:");
        String title = sc.nextLine();
        for(int i = 0; i< book.size(); i++)
        {
            Book b = book.get(i);
            if(b.title.equalsIgnoreCase(title))
            {
                return b;
            }
            if(i == book.size()-1)
            {
                System.out.println("This book is not available");
            }
        }
        return null;
    }
    public static Comparator<Book> byTitle()
    {
        Comparator<Book> book = new Comparator<Book>() 
        {
            @Override
            public int compare(Book b1, Book b2)
            {
                return b1.title.compareTo(b2.title);
            }
        };
        return book;
    }
    public static Book binarySearch(ArrayList<Book> book)
    {
        Collections.sort(book, byTitle());
        System.out.println("Enter the name of the Book:");
        String name = sc.next();
        int low = 0;
        int high = book.size() - 1;
 
        while (low <= high) 
        {
            int mid = (low + high) / 2;
            String guess = book.get(mid).title;
            if (guess.equals(name)) 
            {
                return book.get(mid);
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
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> book = new ArrayList<>();
        System.out.println("Enter the size of list:");
        int n = sc.nextInt();
        for(int i = 0; i < n; i++)
        {
            Book b = new Book();
            b.bookId = i;
            System.out.println("Enter the book Title:"); 
            b.title = sc.next(); // giving error show to sir
            System.out.println("Enter author name:");
            b.author = sc.next();
            book.add(b);
        }
        while(true)
        {
            System.out.println("For binary search press 0\nFor linear search press 1\nTo exit press 2\nEnter search Medthod:");
            int choice = sc.nextInt();
            switch (choice) 
            {
                case 0:
                    Book bk = linearSearch(book);
                    System.out.println(bk.bookId+" "+bk.title+" by "+bk.author);
                    System.out.println();
                    break;
                case 1:
                    Book b = linearSearch(book);
                    System.out.println(b.bookId+" "+b.title+" by "+b.author);
                    System.out.println();
                    break;
            }
            if(choice == 2)
            {
                System.out.println("Thank You");
                break;
            }
        }
        sc.close();
    }
}
class Book
{
    int bookId;
    String title;
    String author;
    Book(int bookId, String title, String author)
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
    Book()
    {
    }
}