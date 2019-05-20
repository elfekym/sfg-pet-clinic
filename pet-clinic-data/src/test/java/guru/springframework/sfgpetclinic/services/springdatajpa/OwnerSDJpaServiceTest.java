package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String SMITH = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    Owner returnOwner;
    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().build();
    }

    @Test
    void findByLastName() {
        //Owner returnOwner = new Owner();
        returnOwner.setLastName(SMITH);
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.findByLastName(SMITH);
        assertEquals(SMITH, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().build());
        returnOwnerSet.add(Owner.builder().build());
        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = ownerSDJpaService.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(returnOwner));
        Owner savedOwner = ownerSDJpaService.findById(1L);
        assertNotNull(savedOwner);
    }

    @Test
    void save() {
        Owner ownerToSave = new Owner();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = ownerSDJpaService.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}