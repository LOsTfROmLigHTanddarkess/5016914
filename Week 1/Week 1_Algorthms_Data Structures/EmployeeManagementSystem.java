import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem 
{
    static Scanner sc = new Scanner(System.in);
    public static void traverseEmployee(Employee[] employeeDetails)
    {
        for(Employee er : employeeDetails)
        {
            if (er != null) 
            {
                System.out.println(er.employeeId + " " + er.name + " " + er.position + " " + er.salary);
            }
        }
    }
    public static Employee addEmployee(int id)
    {
        System.out.println("Enter the space separated Employee details in the following order[Name Position Salary]:");
        Employee e = new Employee(id,sc.next(), sc.next(), sc.nextInt());
        return e;
    }
    
    public static Employee searchEmployee(Employee[] employees)
    {
        System.out.println("Enter id for search:");
        int searchId = sc.nextInt();
        List<Employee> nonNullEmployees = new ArrayList<>();
        for (Employee e : employees) {
            if (e != null) {
                nonNullEmployees.add(e);
            }
        }
        Employee[] employee = nonNullEmployees.toArray(new Employee[0]);
        Arrays.sort(employee, Comparator.comparingInt(e -> e.employeeId));
        int low = 0;
        int high = employee.length - 1;
        while (low <= high) 
        {
            int mid = (low + high) / 2;
            int guess = employee[mid].employeeId;
            if (employee[mid].employeeId == searchId) 
            {
                return employee[mid];
            } 
            else if (guess > searchId) 
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
        public static Employee deleteEmployee(Employee[] employees)
        {
            System.out.println("Enter id for deletion:");
            int id = sc.nextInt();
            for(int i = 0; i < employees.length; i++)
            {
                Employee e = employees[i];
                if(Arrays.asList(e.employeeId).contains(id))
                {
                    employees[i] = null;
                    return null;
                }
                if(i == employees.length-1)
                {
                    System.out.println("Employee does not exist");
                }
            }
            return null;
        }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the array size:");
        int n = sc.nextInt();
        int id = 0;
        System.out.println("Enter the no of elements to be inserted:");
        int limit = sc.nextInt();
        Employee[] employee = new Employee[n];
        for(int i = 0; i < limit; i++)
        {
            System.out.println("Enter the space separated Employee details in the following order[Name Position Salary]:");
            Employee e = new Employee(i,sc.next(), sc.next(), sc.nextInt());
            employee[i] = e;
            id++;
        }
        while(true)
        {
            System.out.println("To Add press 0\nTo Search press 1\nTo Traverse press 2\nTo Delete press 3\nTo Exit press 4\nEnter input:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 0:
                    if(id>employee.length-1)
                    {
                        System.out.println("Out of bounds of array");
                        System.out.println();
                        break;
                    }
                    employee[id] = addEmployee(id);
                    id++;
                    break;
                case 1:
                    Employee e = searchEmployee(employee);
                    System.out.println((e!=null)?e.employeeId+" "+e.name+" "+e.position+" "+e.salary:"Employee not present in this Firm");
                    System.out.println();
                    break;
                case 2:
                    traverseEmployee(employee);
                    break;
                case 3:
                    deleteEmployee(employee);
                    break;
            }
            if(choice == 4)
            {
                System.out.println("Thank You");
                break;
            }
        }
        sc.close();
    }
}
class Employee
{
    int employeeId; 
    String name;
    String position; 
    int salary;
    Employee(int employeeId, String name, String position, int salary)
    {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}
