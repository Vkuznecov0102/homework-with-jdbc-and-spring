package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(mappedBy = "pet_id")
    private long id;

    @Column(name="type")
    private String type;

    @Column(name="name")
    private String name;

    public Pet(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
