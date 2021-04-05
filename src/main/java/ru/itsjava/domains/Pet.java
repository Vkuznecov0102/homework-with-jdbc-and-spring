package ru.itsjava.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Pet {
    private long id;
    private final String type;
    private final String name;
}
