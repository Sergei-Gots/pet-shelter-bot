package pro.sky.petshelterbot.service;

import org.springframework.stereotype.Service;
import pro.sky.petshelterbot.entity.Pet;
import pro.sky.petshelterbot.entity.Shelter;
import pro.sky.petshelterbot.repository.PetRepository;
import pro.sky.petshelterbot.repository.ShelterRepository;

import java.util.Collection;

@Service
public class PetService {
    final private PetRepository petRepository;
    final private ShelterRepository shelterRepository;

    final private Shelter catShelter, dogShelter;

    public PetService(PetRepository catRepository, ShelterRepository shelterRepository) {

        this.petRepository = catRepository;
        this.shelterRepository = shelterRepository;
        catShelter = shelterRepository.getCatShelter();
        dogShelter = shelterRepository.getDogShelter();
    }

    public Pet createCat(String name) {
        return petRepository.save(new Pet ("cat", name, catShelter));
    }

    public Pet createDog(String name) {

        return petRepository.save(new Pet ("dog", name, dogShelter));
    }

    public Collection<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet add(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet add(String species, String name, Shelter shelter) {
        Pet pet = new Pet(species, name, shelter);
        return petRepository.save(pet);
    }

    public Pet get(Long id) {
        return  petRepository.getPetById(id);
    }

    public Pet delete(Long id) {
        Pet pet = get(id);
        petRepository.delete(pet);
        return pet;
    }

}
