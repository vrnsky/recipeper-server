package me.vrnsky.server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RECIPES")
@ToString
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    @JsonProperty(value = "title")
    @NotBlank(message = "Заголовок рецепта не может быть пустым")
    private String title;

    @Column(name = "DESCRIPTION")
    @JsonProperty(value = "description")
    @NotBlank(message = "Описание рецепта не может быть пустым")
    private String description;

    @Column(name = "PRODUCT")
    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
