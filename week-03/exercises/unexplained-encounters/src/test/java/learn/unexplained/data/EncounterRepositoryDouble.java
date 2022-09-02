package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterRepositoryDouble() {
        Encounter sound = new Encounter();
        sound.setEncounterId(1);
        sound.setType(EncounterType.SOUND);
        sound.setWhen("Test Date");
        sound.setDescription("Test Description");
        sound.setOccurrences(1);



        encounters.add(sound);
        encounters.add(new Encounter(2,EncounterType.UFO, "Test Date 2", "Test Description 2", 2));
        encounters.add(new Encounter(3,EncounterType.SOUND, "Test Date 3", "Test Description 3", 3));
        encounters.add(new Encounter(4,EncounterType.VISION, "Test Date 4", "Test Description 4", 4));
    }


    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return new ArrayList<>(encounters);
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        encounters.add(encounter);
        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == 12;
    }

    @Override
    public Encounter findById(int encounterId) throws DataAccessException {
        for (Encounter e : encounters) {
            if (e.getEncounterId() == encounterId) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        ArrayList<Encounter> result = new ArrayList<>();
        for (Encounter e : encounters) {
            if (type == e.getType()) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return true;
    }
}
