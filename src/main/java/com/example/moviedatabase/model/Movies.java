package com.example.moviedatabase.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
public class Movies {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Getter @Setter
    String movieName;

    @NotNull
    @Getter @Setter
    String movieDescription;

    @NotNull @Lob
    @Getter @Setter
    byte[] moviePicture;
}
