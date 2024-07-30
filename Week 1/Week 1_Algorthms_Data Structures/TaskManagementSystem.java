import java.util.Scanner;

public class TaskManagementSystem 
{
    static Scanner sc = new Scanner(System.in);
    public static void addTaskToList(TaskLinkedList taskList, int len)
    {
        Task task = new Task();
        task.taskId = len;
        System.out.println("Enter task name:");
        task.taskName = sc.next();
        System.out.println("Enter task status:");
        task.status = sc.next();
        taskList.addTask(task);
        len++;
    }
    public static Task searchTask(TaskLinkedList taskList)
    {
        System.out.println("Enter taskId for search:");
        int id = sc.nextInt();
        Task searchedTask = taskList.searchTask(id);
        if (searchedTask != null) {
            return searchedTask;
        } else {
            System.out.println("Task not found.");
        }
        return null;
    }
    public static Task deleteTask(TaskLinkedList taskList)
    {
        System.out.println("Enter taskId for deletion:");
        int id = sc.nextInt();
        boolean isDeleted = taskList.deleteTask(id);
        if (isDeleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
        return null;
    }
    public static void main(String[] args) 
    {
        TaskLinkedList taskList = new TaskLinkedList();
        Scanner sc = new Scanner(System.in);
        int len = 0;
        System.out.println("Enter list size:");
        int n = sc.nextInt();
        for(int i = 0; i < n; i++)
        {
            Task task = new Task();
            task.taskId = i;
            System.out.println("Enter task name:");
            task.taskName = sc.next();
            System.out.println("Enter task status:");
            task.status = sc.next();
            taskList.addTask(task);
            len++;
        }
        while(true)
        {
            System.out.println("To Add press 0\nTo Search press 1\nTo Traverse press 2\nTo Delete press 3\nTo Exit press 4\nEnter input:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 0:
                    addTaskToList(taskList, len);
                    break;
                case 1:
                    Task foundTask = searchTask(taskList);
                    System.out.println(foundTask.taskId+" "+foundTask.taskName+" "+foundTask.status);
                    break;
                case 2:
                    System.out.println("Traversing tasks:");
                    taskList.traverseTasks();
                    break;
                case 3:
                    deleteTask(taskList);
                    break;
            }
            if(choice == 4)
            {
                System.out.println("Thank you");
                break;
            }
        }
        sc.close();
    }
}
class Task
{
    int taskId;
    String taskName;
    String status;
    Task(int taskId, String taskName, String status) 
    {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
    Task()
    {

    }
}
class TaskLinkedList 
{
    private Node head;
    private static class Node 
    {
        Task task;
        Node next;

        Node(Task task) 
        {
            this.task = task;
            this.next = null;
        }
    }
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task.taskId+" "+current.task.taskName+" "+current.task.status);
            current = current.next;
        }
    }
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.taskId == taskId) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.task.taskId != taskId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }
        return false;
    }
}