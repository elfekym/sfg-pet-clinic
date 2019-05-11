package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
@Setter
@Getter
@Entity
@Table(name = "specialities")
public class Specialty extends BaseEntity {
    private String description;

}
