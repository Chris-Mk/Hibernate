package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findEmployeeByFirstNameAndLastName(String firstNAme, String lastName);

    @Query("select e from app.ccb.domain.entities.Employee as e where size(e.clients) <> 0 order by size(e.clients) desc, e.id")
    List<Employee> findAllEmployeesWithClients();
}
