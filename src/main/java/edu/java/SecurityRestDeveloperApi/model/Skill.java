package edu.java.SecurityRestDeveloperApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills")
@Data
public class Skill extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private List<Developer> developers;

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
