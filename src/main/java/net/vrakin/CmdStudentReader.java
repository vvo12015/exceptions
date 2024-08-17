package net.vrakin;

import java.util.Scanner;

public class CmdStudentReader implements StudentReader {
    @Override
    public Student readStudent() {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Enter student name: ");
        student.setName(sc.nextLine());
        System.out.println("Enter student lastName: ");
        student.setLastName(sc.nextLine());

        System.out.println("Enter student gender. MALE or FEMALE: ");
        boolean trueEnter = true;
        while (trueEnter) {
            trueEnter = false;
            String gender = sc.nextLine();
            if (gender.equalsIgnoreCase("male")) {
                student.setGender(Gender.MALE);
            } else if (gender.equalsIgnoreCase("female")) {
                student.setGender(Gender.FEMALE);
            } else {
                System.out.printf("Your enter is wrong%nEnter student gender. MALE or FEMALE: %n");
                trueEnter = true;
            }
        }
        System.out.println("Enter student ID: ");
        student.setId(sc.nextInt());
        sc.nextLine();

        System.out.println("Enter student groupName: ");
        student.setGroupName(sc.nextLine());

        sc.close();
        return student;
    }
}
