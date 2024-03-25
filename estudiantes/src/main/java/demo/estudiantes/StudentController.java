package demo.estudiantes;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController() {

        // Inicializar la lista con datos
        students.add(new Student(1, "Sebastian", 23,
                                Arrays.asList(new Asignatura("MAT","matematicas"), new Asignatura("LEN", "lenguaje")),
                                llenarListaNotas("matematicas",6.0,2.0,5.0)));

        students.add(new Student(2, "Daniel", 25,
                                Arrays.asList(new Asignatura("HIS", "historia"), new Asignatura("MAT", "matematicas")),
                                llenarListaNotas("historia", 4.0,2.5,4.3)));

    }

    private Map<String, List<Double>> llenarListaNotas(String asignatura, Double nota1, Double nota2, Double nota3){
        Map<String, List<Double>> retorno = new HashMap<String, List<Double>>();
        List<Double> notas = Arrays.asList(nota1, nota2, nota3);
        retorno.put(asignatura, notas);
        return retorno;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @GetMapping(path = "/students/{idEstudiante}/promedios/{nombreAsignatura}")
    public Double listarSucursal(@PathVariable("idEstudiante") Integer idEstudiante, @PathVariable("nombreAsignatura") String nombreAsignatura) {
        Double promedio = 0.0;
        for (Student estudiante : students) {
            if (estudiante.getId() == idEstudiante) {
                List<Double> notas = estudiante.getNotasPorAsignatura().get(nombreAsignatura);
                Double sumatoria = 0.0;
                for (Double nota : notas) {
                    sumatoria = sumatoria + nota;
                }
                promedio = sumatoria / notas.size();
            }
        }
        return promedio;
    }
}