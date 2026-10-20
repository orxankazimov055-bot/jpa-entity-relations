
package az.iktex.organization.structure.testders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "phone", length = 20)
    private String phone;

    // CascadeType.ALL və orphanRemoval: departament silinəndə işçilər də silinir.
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employee == null) {
            return;
        }

        if (!employees.contains(employee)) {
            employees.add(employee);
        }

        Department oldDepartment = employee.getDepartment();

        if (oldDepartment != null && oldDepartment != this) {
            oldDepartment.getEmployees().remove(employee);
        }

        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        if (employee == null) {
            return;
        }

        if (employees.remove(employee)) {
            employee.setDepartment(null);
        }
    }
}
