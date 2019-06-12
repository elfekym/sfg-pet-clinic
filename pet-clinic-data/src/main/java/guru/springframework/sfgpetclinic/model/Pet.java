package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    private String name;

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        if (visits == null || visits.size() > 0 ) {
            this.visits = visits;
        }
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

}
