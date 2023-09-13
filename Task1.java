import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Create arrays to store subject names and marks
        String[] subjects = new String[numSubjects];
        int[] marks = new int[numSubjects];

        // Input loop: Get subject names and marks from the user
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjects[i] = scanner.next();
            System.out.print("Enter marks obtained in " + subjects[i] + ": ");
            marks[i] = scanner.nextInt();
        }

        // Calculate the total marks
        int totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) {
            totalMarks += marks[i];
        }

        // Calculate the average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Assign a grade based on the average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display the results
        System.out.println("\nResults:");
        System.out.println("--------------------------------------------------");
        System.out.println("Subject\t\tMarks Obtained");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println(subjects[i] + "\t\t" + marks[i]);
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        // Close the scanner
        scanner.close();
    }
}
