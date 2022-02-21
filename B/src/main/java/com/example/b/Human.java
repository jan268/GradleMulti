package com.example.b;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human implements Serializable {

    Long id;
    String name;
    String lastName;
    String phoneNumber;
    String zipCode;
    String city;
}
