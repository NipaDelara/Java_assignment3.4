package Task3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {

        //Create objects
        Student student1 = new Student(1, "Jon", 22);
        Course course1 = new Course("JAVA101", "Java Programming", "Mr. Smith");
        Enrollment enrollment1 = new Enrollment(student1, course1, "2025-09-08");

        try {
            //Serialize object to file
            FileOutputStream fileOut = new FileOutputStream("enrollments.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(enrollment1);

            objectOut.close();
            fileOut.close();

            System.out.println("Object saved to enrollments.ser");

            //Deserialize object from file
            FileInputStream fileIn = new FileInputStream("enrollments.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Enrollment loadedEnrollment = (Enrollment) objectIn.readObject();

            objectIn.close();
            fileIn.close();

            //Print object information
            System.out.println("\nDeserialized Enrollment Information:");
            System.out.println("Student ID: " + loadedEnrollment.getStudent().getId());
            System.out.println("Student Name: " + loadedEnrollment.getStudent().getName());
            System.out.println("Student Age: " + loadedEnrollment.getStudent().getAge());

            System.out.println("Course Code: " + loadedEnrollment.getCourse().getCourseCode());
            System.out.println("Course Name: " + loadedEnrollment.getCourse().getCourseName());
            System.out.println("Instructor: " + loadedEnrollment.getCourse().getInstructor());

            System.out.println("Enrollment Date: " + loadedEnrollment.getEnrollmentDate());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}