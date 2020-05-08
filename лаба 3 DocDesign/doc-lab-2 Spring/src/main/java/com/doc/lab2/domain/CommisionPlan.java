package com.doc.lab2.domain;

import com.doc.lab2.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commision_plans", schema = "workers")
public class CommisionPlan implements EntityInterface {
    private Long id;
    private String commision_plan;
    private Long workerId;

    public CommisionPlan() { }

    public CommisionPlan(Long id, String commision_plan, Long workerId) {
        this.id = id;
        this.commision_plan = commision_plan;
        this.workerId = workerId;
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
    @Column(name = "worker_id", nullable = false)
    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    @Basic
    @Column(name = "commision_plan", nullable = false, length = 50)
    public String getCommision_plan() {
        return commision_plan;
    }

    public void setCommision_plan(String commision_plan) {
        this.commision_plan = commision_plan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommisionPlan that = (CommisionPlan) o;
        return id == that.id &&
                Objects.equals(commision_plan, that.commision_plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commision_plan);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", commision_plan= " + commision_plan;
    }

    public String toJsonObject() {
        return "{" +
                "\"id\":" + id + "," +
                "\"workerId\":" + id + "," +
                "\"commision_plan\":" + "\"" + commision_plan + "\"" +
                "}";
    }
}
