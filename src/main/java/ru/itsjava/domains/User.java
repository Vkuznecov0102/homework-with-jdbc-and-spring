package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private long id;
    private String fio;
    private Email mail;
    private Pet pet;

    public User(String fio, Email mail, Pet pet) {
        this.fio = fio;
        this.mail = mail;
        this.pet = pet;
    }

}
