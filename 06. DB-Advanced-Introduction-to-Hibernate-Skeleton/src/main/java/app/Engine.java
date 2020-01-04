package app;

import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        //this.removeObjects();
        //this.containsEmployee();
        //this.addingANewAddress();     // --- to be implemented ---
        //this.addressesWithEmployeeCount();       // --- to be implemented ---
        //this.employeesWithSalaryOver50000();
        //this.employeesFromDepartments();
        //this.getEmployeeWithProject();
        //this.findLatest10Projects();
        //this.increaseSalaries();
        //this.removeTowns();   // --- to be implemented ---
        //this.findEmployeesByFirstName();      // --- to be implemented ---
        //this.employeesMaximumSalaries();      // --- to be implemented ---
    }

    private void employeesMaximumSalaries() {

    }

    private void findEmployeesByFirstName() {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        final List<Employee> names = this.entityManager
                .createQuery("FROM Employee WHERE firstName  LIKE concat(:pattern, '%')", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        for (Employee employee : names) {
            System.out.println(String.format("%s %s - %s - ($%.2f)",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    employee.getSalary()));
        }

        this.entityManager.getTransaction().commit();
    }

    private void removeTowns() {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        this.entityManager.getTransaction().begin();

//        this.entityManager.createQuery("DELETE FROM Town WHERE name = :townName", Town.class)
//                .setParameter("townName", townName)
//                .executeUpdate();
//
//        this.entityManager.getTransaction().commit();
//        this.entityManager.getTransaction().begin();

//        final int deletedAddresses = this.entityManager
//                .createQuery("DELETE FROM Address WHERE town.name = :townName", Address.class)
//                .setParameter("townName", townName)
//                .executeUpdate();

        final Town town = this.entityManager.
                createQuery("FROM Town WHERE name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        this.entityManager.remove(town);
//
        this.entityManager.getTransaction().commit();
//        this.entityManager.getTransaction().begin();
//
//        final List<Address> addressesToDelete = this.entityManager
//                .createQuery("FROM Address WHERE town.name = :townName", Address.class)
//                .setParameter("townName", townName)
//                .getResultList();
//
//        for (Address address : addressesToDelete) {
//            this.entityManager.remove(address);
//        }

        //System.out.println(String.format("%d address in %s deleted", deletedAddresses, townName));

        this.entityManager.getTransaction().commit();
    }

    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();

        final List<Employee> resultList = this.entityManager.createQuery("FROM Employee WHERE department.name " +
                "IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        for (Employee employee : resultList) {
            employee.setSalary(employee.getSalary().multiply(new BigDecimal(1.12)));

            this.entityManager.persist(employee);

            System.out.println(String.format("%s %s ($%.2f)",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary()));
        }

        this.entityManager.getTransaction().commit();
    }

    private void findLatest10Projects() {
        this.entityManager.getTransaction().begin();

        this.entityManager.createQuery("FROM Project ORDER BY name", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(project -> System.out.println(String.format("Project name: %s\n" +
                                "\tProject Description: %s\n" +
                                "\tProject Start Date: %s\n" +
                                "\tProject End Date: %s",
                        project.getName(),
                        project.getDescription(),
                        project.getStartDate(),
                        project.getEndDate())));

        this.entityManager.getTransaction().commit();
    }

    private void getEmployeeWithProject() {
        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());

        this.entityManager.getTransaction().begin();

        this.entityManager.createQuery("FROM Employee WHERE id = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultStream()
                .forEach(employee -> {
                    System.out.println(String.format("%s %s - %s",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getJobTitle()));

                    employee.getProjects()
                            .stream()
                            .sorted(Comparator.comparing(Project::getName))
                            .forEach(project -> System.out.println(String.format("\t%s", project.getName())));
                });

        this.entityManager.getTransaction().commit();
    }

    private void employeesFromDepartments() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("FROM Employee WHERE department.name = 'Research and Development' ORDER BY salary, id", Employee.class)
                .getResultStream()
                .forEach(employee -> System.out.println(String.format("%s %s from %s - $%.2f",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getName(),
                        employee.getSalary())));

        this.entityManager.getTransaction().commit();
    }

    private void employeesWithSalaryOver50000() {
        this.entityManager.getTransaction().begin();

        final List<Employee> employees = this.entityManager
                .createQuery("FROM Employee", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            if (employee.getSalary().compareTo(new BigDecimal(50000)) > 0) {
                System.out.println(employee.getFirstName());
            }
        }

        this.entityManager.getTransaction().commit();
    }

    private void addressesWithEmployeeCount() {
        this.entityManager.getTransaction().begin();

        this.entityManager.createQuery("FROM Address ORDER BY size(employees), town.id", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(address -> System.out.println(String.format("%s, %s - %d employees",
                        address.getText(),
                        address.getTown().getName(),
                        address.getEmployees().size())));

        this.entityManager.getTransaction().commit();
    }

    private void addingANewAddress() {
        Scanner scanner = new Scanner(System.in);
        String employeeLastName = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        final Town town = this.entityManager
                .createQuery("FROM Town WHERE name = 'Sofia'", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vikoshka 15");
        address.setTown(town);

        this.entityManager.persist(address);

        this.entityManager.getTransaction().commit();

        this.updatingEmployeeAddress(employeeLastName, address);
    }

    private void updatingEmployeeAddress(String lastName, Address address) {
        this.entityManager.getTransaction().begin();

        final Employee employee = this.entityManager
                .createQuery("FROM Employee WHERE lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        employee.setAddress(address);
        this.entityManager.persist(employee);

        this.entityManager.getTransaction().commit();
    }

    private void containsEmployee() {
        Scanner scanner = new Scanner(System.in);
        String employeeName = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        try {
            final Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE concat(firstName, ' ', lastName) = :name", Employee.class)
                    .setParameter("name", employeeName)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException ex) {
            System.out.println("No");
        }

        this.entityManager.getTransaction().commit();
    }

    private void removeObjects() {
        this.entityManager.getTransaction().begin();

        final List<Town> towns = this.entityManager
                .createQuery("FROM Town", Town.class)
                .getResultList();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                this.entityManager.detach(town);
            } else {
                town.setName(town.getName().toLowerCase());
                this.entityManager.persist(town);
            }
        }

        this.entityManager.getTransaction().commit();
    }
}
