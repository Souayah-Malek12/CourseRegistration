package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*; // Pour JPA
        import jakarta.validation.constraints.Size; // Pour validation
import lombok.*; // Lombok

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Username is invalid")
    @Column(nullable = false)
    private String username;

    @Size(min = 4, message = "Password is invalid")
    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordCheck;

    private String role;
}
