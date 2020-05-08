package com.doc.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.doc.lab2.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "workers", schema = "workers")
public class Worker implements EntityInterface {
    private Long id;
    private String surname;
    private String name;
    private String unit_salary;
    private Salary salaryByWorkers;
    Set<CommisionPlan> commisionPlans = new HashSet<>();

    @Transient
    private Long relatedSalaryId;

    public Worker(String surname, String name, String unit_salary, Salary salaryByWorkers) {

        this.surname = surname;
        this.name = name;
        this.unit_salary = unit_salary;
       this.salaryByWorkers = salaryByWorkers;
    }

    public Worker(Long id, String surname, String name, String unit_salary) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.unit_salary = unit_salary;
    }

    public Worker(Long id, String surname, String name, String unit_salary, Long salaryId) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.unit_salary = unit_salary;
        this.relatedSalaryId = salaryId;
    }

    public Worker() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unit_salary", nullable = true, length = 50)
    public String getUnit_salary() {
        return unit_salary;
    }

    public void setUnit_salary(String unit_salary) {
        this.unit_salary = unit_salary;
    }

    @ManyToOne
    @JoinColumn(name = "salary_id",  referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Salary getSalaryByWorkers() {
        return salaryByWorkers;
    }

    public void setSalaryByWorkers(Salary salaryByWorkers) {
        this.salaryByWorkers = salaryByWorkers;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "worker_commision_plans",
            joinColumns = { @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "commision_plan_id", referencedColumnName = "id", nullable = false), }
    )
    @JsonIgnore
    public Set<CommisionPlan> getCommisionPlans() {
        return commisionPlans;
    }

    public void setCommisionPlans(Set<CommisionPlan> commisionPlans) {
        this.commisionPlans = commisionPlans;
    }

    @Transient
    public Long getRelatedSalaryId() {
        return relatedSalaryId;
    }

    public void setRelatedSalaryId(Long salaryId) {
        this.relatedSalaryId = salaryId;
    }

    @Override
    public String toString() {
        return "Workers{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", unit_salary='" + unit_salary + '\'' +
                '}';
    }

    public String toStringJoinTable(){
        return "Workers{" +
                "id=" + id +
//                " projects=" + projects +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker that = (Worker) o;
        return id == that.id &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(unit_salary, that.unit_salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, unit_salary);
    }


    public String toJsonObject() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":" + "\"" + name + "\"," +
                "\"surname\":" + "\"" + surname + "\"," +
                "\"unit_salary\":" + "\"" + unit_salary + "\"" +
        "}";
    }
}
