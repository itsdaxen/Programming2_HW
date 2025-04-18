package dev.m3s.programming2.homework2;

import java.util.Arrays;

public class TestClass {

        public static void main(String[] args) {
            // Step 1: Create the student
            Student student = new Student();

            // Step 2: Create courses
            Course c1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
            Course c2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
            Course c3 = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
            Course c4 = new Course("Even more basic studies", 556677, 'a', 0, 3, 50.0, true);
            Course c5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
            Course c6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
            Course c7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
            Course c8 = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
            Course c9 = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
            Course c10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
            Course c11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);

            // Step 3: Create student courses
            StudentCourse sc1 = new StudentCourse(c1, 1, 2013);
            StudentCourse sc2 = new StudentCourse(c2, 1, 2014);
            StudentCourse sc3 = new StudentCourse(c3, 1, 2015);
            StudentCourse sc4 = new StudentCourse(c4, 4, 2016);
            StudentCourse sc5 = new StudentCourse(c5, 5, 2017);
            StudentCourse sc6 = new StudentCourse(c6, 1, 2018);
            StudentCourse sc7 = new StudentCourse(c7, 1, 2019);
            StudentCourse sc8 = new StudentCourse(c8, 2, 2020);
            StudentCourse sc9 = new StudentCourse(c9, 0, 2021);
            StudentCourse sc10 = new StudentCourse(c10, 'A', 2021);
            StudentCourse sc11 = new StudentCourse(c11, 'f', 2022);

            // Step 4 & 5: Split into bachelor and master courses
            StudentCourse[] bachelorCourses = {sc1, sc2, sc3, sc4, sc5};
            StudentCourse[] masterCourses = {sc6, sc7, sc8, sc9, sc10, sc11};

            // Step 6-9: Titles and theses
            student.setDegreeTitle(0, "Bachelor of Science");
            student.setDegreeTitle(1, "Master of Science");
            student.setTitleOfThesis(0, "Bachelor thesis title");
            student.setTitleOfThesis(1, "Masters thesis title");

            // Step 10 & 11: Add courses
            student.addCourses(0, bachelorCourses);
            student.addCourses(1, masterCourses);

            // Step 12-15: Final data
            student.setStartYear(2001);
            student.setGraduationYear(2020);
            student.setFirstName("Donald");
            student.setLastName("Duck");

            // Step 16: Print student details
            System.out.println(student);

            // Step 17-19: Birthdate and updated theses
            student.setBirthDate("230498-045T");
            student.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
            student.setTitleOfThesis(1, "Dreaming of a white Christmas");

            // Step 20-21: Print degrees
            student.printDegrees();

            // Step 22: Grade update
            sc9.setGrade(3);

            // Step 23: Print student again
            System.out.println(student);

            // Step 24: Print degrees
            student.printDegrees();

            // Step 25-28: Test invalid and valid grade changes
            sc11.setGrade('X'); // invalid
            System.out.println(sc11);
            sc11.setGrade('a'); // valid
            System.out.println(sc11);

            // Step 29-32: Manipulate and print first course
            sc1.setGrade(6); // invalid
            System.out.println(sc1);
            sc1.setGrade(5); // valid
            System.out.println(sc1);
        }
    }
