package fr.aelion.atos24.cyber.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // /!\ Integer vs Long
    private String email;
    @Column(name="password")
    private String pwd;
}
