package com.springhomework.emailAPI;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emails")
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId = 0L;

    @Column(nullable = false)
    private String email;
}
