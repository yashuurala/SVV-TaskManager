public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private boolean completed;

    public Task(String title, String description, String dueDate, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;// mutant9
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Due Date: " + dueDate + "\n"
                + "Priority: " + priority + "\n"
                + "Status: " + (completed ? "Completed" : "Not Completed") + "\n"; // mutant3
    }
}
