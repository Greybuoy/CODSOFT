import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    protected List<String> enrolledStudents;

    // Constructor to initialize course properties
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters for course properties
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    // Method to enroll a student in the course
    public boolean enrollStudent(String studentName) {
        if (!isFull()) {
            enrolledStudents.add(studentName);
            return true;
        }
        return false;
    }

    // Method to display course information
    public void displayInfo() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Schedule: " + schedule);
        System.out.println("Enrolled Students: " + enrolledStudents.size());
        System.out.println("Available Slots: " + (capacity - enrolledStudents.size()));
    }
}

class Student {
    private int studentID;
    private String name;
    private List<String> registeredCourses;

    // Constructor to initialize student properties
    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Getters for student properties
    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    // Method to register a course for the student
    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    // Method to drop a course for the student
    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }

    // Method to display student information
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Registered Courses: " + registeredCourses);
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        int studentIDCounter = 1;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Course Registration System =====");
            System.out.println("1. Add Course");
            System.out.println("2. Register Student");
            System.out.println("3. Display Course Information");
            System.out.println("4. Display Student Information");
            System.out.println("5. Register Student for Course");
            System.out.println("6. Drop Course for Student");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new course
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = scanner.nextLine();
                    System.out.print("Enter Course Description: ");
                    String courseDescription = scanner.nextLine();
                    System.out.print("Enter Course Capacity: ");
                    int courseCapacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Schedule: ");
                    String courseSchedule = scanner.nextLine();

                    Course course = new Course(courseCode, courseTitle, courseDescription, courseCapacity, courseSchedule);
                    courses.add(course);
                    break;

                case 2:
                    // Register a new student
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    Student student = new Student(studentIDCounter++, studentName);
                    students.add(student);
                    break;

                case 3:
                    // Display course information
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    for (Course c : courses) {
                        if (c.getCode().equalsIgnoreCase(courseCode)) {
                            c.displayInfo();
                            break;
                        }
                    }
                    break;

                case 4:
                    // Display student information
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    for (Student s : students) {
                        if (s.getStudentID() == studentID) {
                            s.displayInfo();
                            break;
                        }
                    }
                    break;

                case 5:
                    // Register student for a course
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();

                    for (Student s : students) {
                        if (s.getStudentID() == studentID) {
                            for (Course c : courses) {
                                if (c.getCode().equalsIgnoreCase(courseCode)) {
                                    if (!c.isFull()) {
                                        s.registerCourse(c.getCode());
                                        if (c.enrollStudent(s.getName())) {
                                            System.out.println("Student " + s.getName() + " registered for " + c.getTitle());
                                        } else {
                                            System.out.println("Course " + c.getTitle() + " is full. Registration failed.");
                                        }
                                        break;
                                    } else {
                                        System.out.println("Course " + c.getTitle() + " is full. Registration failed.");
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;

                case 6:
                    // Drop a course for a student
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();

                    for (Student s : students) {
                        if (s.getStudentID() == studentID) {
                            s.dropCourse(courseCode);
                            for (Course c : courses) {
                                if (c.getCode().equalsIgnoreCase(courseCode)) {
                                    c.enrolledStudents.remove(s.getName());
                                    System.out.println("Student " + s.getName() + " dropped course " + c.getTitle());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;

                case 7:
                    // Exit the program
                    System.out.println("Exiting Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
