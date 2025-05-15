import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {// mutant1
            System.out.println("Index: " + i);
            System.out.println(tasks.get(i));
        }
    }

    public void editTask(int index, Task updatedTask) {
        if (index >= 0 && index < tasks.size()) {// mutant2 and mutant5
            tasks.set(index, updatedTask);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {// mutant7
            tasks.remove(index); // mutant6
            System.out.println("Task deleted successfully.");// mutant10
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {// mutant8
            Task task = tasks.get(index);
            task.setCompleted(true);
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public java.util.List<Task> getTasks() {
        return tasks;
    }
}