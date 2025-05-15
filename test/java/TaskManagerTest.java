import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TaskManagerTest {

    @Test
    public void testAddTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Test", "Description", "2025-04-08", "High");
        manager.addTask(task);
        assertEquals(1, manager.getTasks().size());
    }

    @Test
    public void testDeleteTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Delete", "Test", "2025-04-08", "Low");
        manager.addTask(task);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.deleteTask(0);
        assertEquals(0, manager.getTasks().size());
        assertTrue(out.toString().contains("Task deleted successfully."));
    }

    @Test
    public void testMarkTaskComplete() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Complete", "Test", "2025-04-08", "Medium");
        manager.addTask(task);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.markTaskAsComplete(0);
        assertTrue(manager.getTasks().get(0).isCompleted());
        assertTrue(out.toString().contains("Task marked as complete."));
    }

    @Test
    public void testEditTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Old Title", "Old Desc", "2025-04-10", "Low");
        manager.addTask(task);
        
        Task newTask = new Task("New Title", "New Desc", "2025-04-11", "High");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.editTask(0, newTask);
        
        Task edited = manager.getTasks().get(0);
        assertEquals("New Title", edited.getTitle());
        assertEquals("New Desc", edited.getDescription());
        assertEquals("2025-04-11", edited.getDueDate());
        assertEquals("High", edited.getPriority());
        assertTrue(out.toString().contains("Task updated successfully."));
    }

    @Test
    public void testInvalidIndexHandling() {
        TaskManager manager = new TaskManager();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.deleteTask(-1);
        manager.markTaskAsComplete(5);
        Task dummyTask = new Task("Dummy", "Desc", "2025-04-12", "Low");
        manager.editTask(2, dummyTask);

        assertTrue(out.toString().contains("Invalid task index."));
        assertEquals(0, manager.getTasks().size());
    }

    @Test
    public void testEditTaskNoChange() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Same Title", "Same Desc", "2025-04-10", "Medium");
        manager.addTask(task);
        
        Task sameTask = new Task("Same Title", "Same Desc", "2025-04-10", "Medium");
        manager.editTask(0, sameTask);

        Task result = manager.getTasks().get(0);
        assertEquals("Same Title", result.getTitle());
        assertEquals("Same Desc", result.getDescription());
        assertEquals("2025-04-10", result.getDueDate());
        assertEquals("Medium", result.getPriority());
    }

    @Test
    public void testDisplayTasksEmpty() {
        TaskManager manager = new TaskManager();
        manager.displayTasks();  // no crash
    }

    @Test
    public void testDisplayTasksWithTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("T1", "D1", "2025-04-12", "High");
        manager.addTask(task);
        manager.displayTasks();  // should print task info
    }

    @Test
    public void testTaskGettersAndToString() {
        Task task = new Task("T2", "D2", "2025-04-13", "Low");
        assertEquals("T2", task.getTitle());
        assertEquals("D2", task.getDescription());
        assertEquals("2025-04-13", task.getDueDate());
        assertEquals("Low", task.getPriority());
        assertFalse(task.isCompleted());
        assertTrue(task.toString().contains("T2"));
    }

    @Test
    public void testSetters() {
        Task task = new Task("Title", "Desc", "2025-04-08", "Medium");
        task.setTitle("New Title");
        task.setDescription("New Desc");
        task.setDueDate("2025-04-09");
        task.setPriority("High");
        task.setCompleted(true);
        assertEquals("New Title", task.getTitle());
        assertEquals("New Desc", task.getDescription());
        assertEquals("2025-04-09", task.getDueDate());
        assertEquals("High", task.getPriority());
        assertTrue(task.isCompleted());
    }

    @Test
    public void testAllInvalidIndexBranches() {
        TaskManager manager = new TaskManager();
        Task dummyTask = new Task("Dummy", "Dummy", "2025-04-10", "Low");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.editTask(-1, dummyTask);
        manager.deleteTask(-1);
        manager.markTaskAsComplete(-1);

        assertTrue(out.toString().contains("Invalid task index."));
        assertEquals(0, manager.getTasks().size());
    }

    @Test
    public void testToStringCoverage() {
        Task task = new Task("Title", "Desc", "2025-04-08", "Low");
        String result = task.toString();
        assertTrue(result.contains("Title"));
        assertTrue(result.contains("Not Completed"));
    }

    @Test
    public void testEditTaskWithValidIndex() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Old", "Desc", "2025-04-10", "Low");
        manager.addTask(task);
        Task updated = new Task("New", "Updated", "2025-04-11", "High");
        manager.editTask(0, updated);
    }

    @Test
    public void testToStringMethod() {
    Task task = new Task("Title", "Desc", "2025-04-10", "High");
    task.setCompleted(true);  // covers true branch

    String expected = "Title: Title\n" +
                      "Description: Desc\n" +
                      "Due Date: 2025-04-10\n" +
                      "Priority: High\n" +
                      "Status: Completed\n";

    assertEquals(expected, task.toString());
}

	@Test
public void testDeleteTaskBranchFalseThenTrue() {
    TaskManager manager = new TaskManager();
    manager.addTask(new Task("Sample", "Desc", "2025-04-10", "Low"));
    manager.deleteTask(-1);  // index >= 0 is false, index < size is true
}

	@Test
public void testBranchConditionsIndependently() {
    TaskManager manager = new TaskManager();
    Task dummy = new Task("Dummy", "D", "2025", "Low");

    // Only index >= 0 is false (should hit left side false, right true)
    manager.editTask(-1, dummy);
    manager.markTaskAsComplete(-1);
    manager.deleteTask(-1);

    // Only index < tasks.size() is false (index = 5 while list size is 1)
    manager.addTask(dummy);  // now list size = 1
    manager.editTask(5, dummy);
    manager.markTaskAsComplete(5);
    manager.deleteTask(5);
}
}