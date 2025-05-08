package console;

import model.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        School school = new School("001", "Kyiv Tech School");

        while (true) {
            System.out.println("\n--- School Management Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. Add discipline");
            System.out.println("3. Assign discipline to student");
            System.out.println("4. Perform test for a student");
            System.out.println("5. Show overall school info");
            System.out.println("6. Export data");
            System.out.println("7. Import data");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter student name: ");
                        String studentName = scanner.nextLine();
                        school.addStudent(new Student(studentId, studentName));
                        System.out.println("Student added!");
                        break;

                    case 2:
                        System.out.print("Enter discipline name: ");
                        String disciplineName = scanner.nextLine();
                        school.addDisciplines(new Discipline(disciplineName));
                        System.out.println("Discipline added!");
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        String sid = scanner.nextLine();
                        System.out.print("Enter discipline name: ");
                        String dname = scanner.nextLine();
                        System.out.print("Enter grade: ");
                        double grade = scanner.nextDouble();
                        scanner.nextLine();

                        Student student = school.getStudents().stream()
                                .filter(s -> s.getId().equals(sid))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

                        Discipline discipline = school.getDisciplines().stream()
                                .filter(d -> d.getName().equals(dname))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));

                        student.assignDiscipline(discipline, grade);
                        System.out.println("Discipline assigned to student!");
                        break;

                    case 4:
                        System.out.print("Enter student ID: ");
                        String stId = scanner.nextLine();
                        System.out.print("Enter discipline name: ");
                        String dsName = scanner.nextLine();

                        Student stud = school.getStudents().stream()
                                .filter(s -> s.getId().equals(stId))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

                        Discipline disc = school.getDisciplines().stream()
                                .filter(d -> d.getName().equals(dsName))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));

                        stud.doTest(disc);
                        System.out.println("Test completed!");
                        break;

                    case 5:
                        System.out.println(school.getSchoolOverallInfo());
                        break;

                    case 6:
                        school.exportDisciplines(true);
                        school.exportStudents();
                        System.out.println("Data exported successfully.");
                        break;

                    case 7:
                        school.importDisciplines();
                        school.importStudents();
                        System.out.println("Data imported successfully.");
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
