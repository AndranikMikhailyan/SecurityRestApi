package edu.java.SecurityRestDeveloperApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
public class Phone extends BaseEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "verification_code")
    private int verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private VerificationStatus verificationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode=" + verificationCode +
                ", verificationStatus=" + verificationStatus +
                ", userId=" + user.getId() +
                '}';
    }
}
