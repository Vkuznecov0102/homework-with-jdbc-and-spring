package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Email {
    private long id;
    private String address;

    public Email(String address) {
        this.address = address;
    }
}
