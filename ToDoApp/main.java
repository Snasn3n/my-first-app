package ToDoApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static final String FILE_NAME = "todos.txt";
    
    // Methode zum Laden der Tasks aus der Datei
    public static ArrayList<String> loadTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        tasks.add(line);
                    }
                }
                reader.close();
                System.out.println("Tasks loaded from file.");
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
    
    public static void saveTasks(ArrayList<String> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    
        ArrayList<String> tasks = loadTasks();

    Scanner scanner = new Scanner(System.in);

    boolean running = true;


    System.out.println("=== ToDo App ===");
    System.out.println("1. Add a task");
    System.out.println("2. View tasks");
    System.out.println("3. Remove a task");
    System.out.println("4. Exit");
    System.out.println("Enter your choice:");

    while (running) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Adding a task...");
                System.out.print("Enter a task: ");
                String task = scanner.nextLine();
                tasks.add(task);
                saveTasks(tasks); // Speichere Tasks nach dem Hinzufügen
                System.out.println("Task added and saved successfully!");
                System.out.print("Enter your next choice: ");
                break;
            case 2:
                System.out.println("Viewing tasks...");
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available.");
                } else {
                    System.out.println("Tasks: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                System.out.print("Enter your next choice: ");
                break;
            case 3:
                System.out.println("Removing a task...");
                if (tasks.isEmpty()) {
                    System.out.println("No tasks to remove.");
                } else {
                    System.out.println("Current tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.print("Enter the task number to remove: ");
                    int taskIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); 
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        String removedTask = tasks.remove(taskIndex);
                        saveTasks(tasks); // Speichere Tasks nach dem Entfernen
                        System.out.println("Task '" + removedTask + "' removed and saved successfully!");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                }
                System.out.print("Enter your next choice: ");
                break;
            case 4:
                System.out.println("Exiting... Goodbye!");
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                System.out.print("Enter your choice: ");
                break;
        }
    }

    scanner.close();

    }
}
