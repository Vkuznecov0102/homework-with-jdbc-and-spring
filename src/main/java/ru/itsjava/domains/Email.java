package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Email {
    private long id;
    private final String address;

    public Email(String address) {
        this.address = address;
    }
}
