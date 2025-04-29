package org.da.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    private Long id;
    private String name;
    private String email;
    private String createAt;
    private String UplordAt;
}
