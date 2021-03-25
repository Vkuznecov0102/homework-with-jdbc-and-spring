package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Pet {
    private long id;
    private String type;
    private String name;

    public Pet(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
