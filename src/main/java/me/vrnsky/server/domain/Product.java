package me.vrnsky.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Имя продукта не может быть пустым")
    private String name;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Описание продукта не может быть пустым")
    private String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
