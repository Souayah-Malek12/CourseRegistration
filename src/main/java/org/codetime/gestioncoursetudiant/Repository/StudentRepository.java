package org.codetime.gestioncoursetudiant.Repository;

import org.codetime.gestioncoursetudiant.Entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long>{

    Student findByFirstName(String firstName);
    List<Student> findAllByOrderByFirstNameAsc();
}
