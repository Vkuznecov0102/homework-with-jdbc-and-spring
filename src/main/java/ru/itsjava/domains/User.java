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
}
