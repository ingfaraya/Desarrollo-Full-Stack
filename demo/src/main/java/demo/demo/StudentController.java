package demo.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController () {
        students.add(new Student(1, "Estudiante 1"));
        students.add(new Student(2, "Estudiante 2"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        for (Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }
}
