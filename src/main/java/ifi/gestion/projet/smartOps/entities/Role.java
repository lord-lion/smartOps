package ifi.gestion.projet.smartOps.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Entity
@Table(name = "TB_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Authority name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Employer> employers;
}