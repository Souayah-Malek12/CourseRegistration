package Service;

import Entity.Student;
import Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl {
    @Autowired
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    public Student findStudentById(Long id) {
        return repository.findById(id).orElse(new Student());
    }

    public List<Student> findAllByOrderByFirstNameAsc() {
        return repository.findAllByOrderByFirstNameAsc();
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public void updateStudent(Student student) {
        repository.save(student);
    }

    public void deleteStudentById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllStudents() {
        repository.deleteAll();
    }

    public List<Student> findAllStudents() {
        return (List<Student>) repository.findAll();
    }

}
