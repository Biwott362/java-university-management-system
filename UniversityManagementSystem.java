import java.util.ArrayList;
import java.util.List;

// Person class (Superclass)
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// Course class
class Course {
    private String courseCode;
    private String courseName;
    private double score;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.score = 0.0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }
}

// Student class (Subclass of Person)
class Student extends Person {
    private String studentId;
    private List<Course> enrolledCourses;
    private static final int MAX_COURSES = 6;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public boolean registerCourse(Course course) {
        if (enrolledCourses.size() < MAX_COURSES) {
            enrolledCourses.add(course);
            return true;
        }
        return false;
    }

    public void assignScore(String courseCode, double score) {
        for (Course course : enrolledCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                course.setScore(score);
                break;
            }
        }
    }

    public void generateReport() {
        System.out.println("\nStudent Report");
        System.out.println("Name: " + getName());
        System.out.println("Student ID: " + studentId);
        System.out.println("\nCourse Grades:");
        System.out.println("----------------------------------------");
        
        for (Course course : enrolledCourses) {
            System.out.printf("%s: %.2f%n", course.toString(), course.getScore());
        }
        
        double average = calculateAverage();
        System.out.println("----------------------------------------");
        System.out.printf("Average Grade: %.2f%n", average);
    }

    private double calculateAverage() {
        if (enrolledCourses.isEmpty()) return 0.0;
        double sum = 0;
        for (Course course : enrolledCourses) {
            sum += course.getScore();
        }
        return sum / enrolledCourses.size();
    }
}

// Lecturer class (Subclass of Person)
class Lecturer extends Person {
    private String employeeId;
    private List<Course> assignedCourses;
    private static final int MAX_COURSES = 3;

    public Lecturer(String name, int age, String employeeId) {
        super(name, age);
        this.employeeId = employeeId;
        this.assignedCourses = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public boolean assignCourse(Course course) {
        if (assignedCourses.size() < MAX_COURSES) {
            assignedCourses.add(course);
            return true;
        }
        return false;
    }
}

// Main UniversityManagementSystem class
public class UniversityManagementSystem {
    public static void main(String[] args) {
        // Create some courses
        Course javaProgramming = new Course("CS101", "Java Programming");
        Course dataStructures = new Course("CS102", "Data Structures");
        Course algorithms = new Course("CS103", "Algorithms");

        // Create a lecturer
        Lecturer lecturer = new Lecturer("Dr. Smith", 45, "EMP001");
        lecturer.assignCourse(javaProgramming);
        lecturer.assignCourse(dataStructures);

        // Create a student
        Student student = new Student("John Doe", 20, "STU001");
        student.registerCourse(javaProgramming);
        student.registerCourse(dataStructures);
        student.registerCourse(algorithms);

        // Assign scores to the student's courses
        student.assignScore("CS101", 85.5);
        student.assignScore("CS102", 92.0);
        student.assignScore("CS103", 88.5);

        // Generate student report
        student.generateReport();

        // Display lecturer's assigned courses
        System.out.println("\nLecturer's Assigned Courses:");
        System.out.println("Name: " + lecturer.getName());
        System.out.println("Employee ID: " + lecturer.getEmployeeId());
        System.out.println("Courses:");
        for (Course course : lecturer.getAssignedCourses()) {
            System.out.println(course);
        }
    }
} 