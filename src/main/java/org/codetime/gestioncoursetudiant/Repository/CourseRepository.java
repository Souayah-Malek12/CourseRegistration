package org.codetime.gestioncoursetudiant.Repository;

import org.codetime.gestioncoursetudiant.Entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findByName(String name);
    List<Course> findAllByOrderByNameAsc();

}
