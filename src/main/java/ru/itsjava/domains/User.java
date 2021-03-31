package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fio")
    private String fio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="email_id", referencedColumnName = "id")
    private Email mail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pet_id", referencedColumnName = "id")
    private Pet pet;
}
