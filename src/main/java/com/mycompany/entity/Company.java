package com.mycompany.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Company entity, representing a company object that can be persisted to a
 * relational database and, at the same time, be used as a transfer object for
 * company entities that are used in the user interface.
 *
 * @author Nils Preusker - n.preusker@gmail.com
 */
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer employees = 0;
    private Date createDate;


    /**
     * Default constructor for JAX-RS (object <> JSON serialization)
     */
    public Company() {
    }

    public Company(String name, Integer employees, Date createDate) {
        this.name = name;
        this.employees = employees;
        this.createDate = createDate;
    }

    // -------- getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployees() { return employees; }

    public void setEmployees(Integer employees) { this.employees = employees; }

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date date) { this.createDate = date; }
}
