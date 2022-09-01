package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterRepositoryDouble() {
        Encounter creature = new Encounter();
        creature.setEncounterId(1);
        creature.setWhen("Test Date");
        creature.setDescription("Test Description");
        creature.setType(EncounterType.CREATURE);
        creature.setOccurrences(1);


        encounters.add(creature);

    }
    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return new ArrayList<>(encounters);
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
    public Encounter add(Encounter encounter) throws DataAccessException {
        encounters.add(encounter);
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return true;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return true;
    }









}
