import java.util.Scanner;

public class TaskManagementMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Mark task as complete");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    taskManager.displayTasks();
                    break;
                case 2:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    Task newTask = new Task(title, description, dueDate, priority);
                    taskManager.addTask(newTask);
                    System.out.println("Task added successfully.");
                    break;
                case 3:
                    System.out.print("Enter index of task to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new due date: ");
                    String newDueDate = scanner.nextLine();
                    System.out.print("Enter new priority (High/Medium/Low): ");
                    String newPriority = scanner.nextLine();
                    Task updatedTask = new Task(newTitle, newDescription, newDueDate, newPriority);
                    taskManager.editTask(editIndex, updatedTask);
                    break;
                case 4:
                    System.out.print("Enter index of task to delete: ");
                    int deleteIndex = scanner.nextInt();
                    taskManager.deleteTask(deleteIndex);
                    break;
                case 5:
                    System.out.print("Enter index of task to mark as complete: ");
                    int completeIndex = scanner.nextInt();
                    taskManager.markTaskAsComplete(completeIndex);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Exiting Task Management App. Goodbye!");
        scanner.close();
    }
}