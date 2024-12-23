import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private String[] courses;
    private double grade;

    public Student(String studentID, String name, String[] courses, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.courses = courses;
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String[] getCourses() {
        return courses;
    }

    public double getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Courses: " + Arrays.toString(courses) + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private HashMap<String, Student> studentsMap;
    private LinkedList<Student> studentsList;

    public StudentManagementSystem() {
        studentsMap = new HashMap<>();
        studentsList = new LinkedList<>();
    }

    public void addStudent(Student student) {
        studentsMap.put(student.getStudentID(), student);
        studentsList.add(student);
    }

    public void updateStudent(String studentID, String name, String[] courses, double grade) {
        Student student = studentsMap.get(studentID);
        if (student != null) {
            student.setName(name);
            student.setCourses(courses);
            student.setGrade(grade);
        }
    }

    public void deleteStudent(String studentID) {
        Student student = studentsMap.remove(studentID);
        if (student != null) {
            studentsList.remove(student);
        }
    }

    public Student searchStudentByID(String studentID) {
        return studentsMap.get(studentID);
    }

    public void sortStudentsByGrade() {
        Collections.sort(studentsList, Comparator.comparingDouble(Student::getGrade).reversed());
    }

    public List<Student> getAllStudents() {
        return studentsList;
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Sort Students by Grades");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Student ID:");
                    String id = scanner.nextLine();
                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Courses (comma-separated):");
                    String[] courses = scanner.nextLine().split(",");
                    double grade = 0;
                    boolean validGrade = false;
                    while (!validGrade) {
                        try {
                            System.out.println("Enter Grade:");
                            grade = Double.parseDouble(scanner.nextLine());
                            validGrade = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid grade. Please enter a numeric value.");
                        }
                    }
                    Student newStudent = new Student(id, name, courses, grade);
                    sms.addStudent(newStudent);
                    break;
                case 2:
                    System.out.println("Enter Student ID to Update:");
                    id = scanner.nextLine();
                    System.out.println("Enter New Name:");
                    name = scanner.nextLine();
                    System.out.println("Enter New Courses (comma-separated):");
                    courses = scanner.nextLine().split(",");
                    grade = 0;
                    validGrade = false;
                    while (!validGrade) {
                        try {
                            System.out.println("Enter New Grade:");
                            grade = Double.parseDouble(scanner.nextLine());
                            validGrade = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid grade. Please enter a numeric value.");
                        }
                    }
                    sms.updateStudent(id, name, courses, grade);
                    break;
                case 3:
                    System.out.println("Enter Student ID to Delete:");
                    id = scanner.nextLine();
                    sms.deleteStudent(id);
                    break;
                case 4:
                    System.out.println("Enter Student ID to Search:");
                    id = scanner.nextLine();
                    Student student = sms.searchStudentByID(id);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    sms.sortStudentsByGrade();
                    System.out.println("Students sorted by grades.");
                    break;
                case 6:
                    for (Student s : sms.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
