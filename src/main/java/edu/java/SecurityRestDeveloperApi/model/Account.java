package edu.java.SecurityRestDeveloperApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Account extends BaseEntity {

    @OneToOne()
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Account{" +
                ", description='" + description + '\'' +
                '}';
    }
}
