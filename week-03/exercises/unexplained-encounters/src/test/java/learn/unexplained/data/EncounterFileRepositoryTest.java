package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String TEST_PATH = "./data/encounters-test.csv";
    static final String SEED_PATH = "./data/encounters-seed.csv";
    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws DataAccessException, IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);

        for (Encounter e : repository.findAll()) {
            repository.deleteById(e.getEncounterId());
        }

        for (Encounter e : testEncounters) {
            repository.add(e);
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldFindOneOfEachType() throws DataAccessException {
        List<Encounter> ufo = repository.findByType(EncounterType.UFO);
        assertNotNull(ufo);
        assertEquals(1,ufo.size());

        List<Encounter> creature = repository.findByType(EncounterType.CREATURE);
        assertNotNull(creature);
        assertEquals(1,creature.size());

        List<Encounter> sound = repository.findByType(EncounterType.SOUND);
        assertNotNull(sound);
        assertEquals(1,sound.size());

    }

    @Test
    void shouldUpdateExistingOrbiter() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(3);
        encounter.setWhen("2022-09-01");
        encounter.setType(EncounterType.VISION);
        encounter.setDescription("Test Description");
        encounter.setOccurrences(2);

        boolean success = repository.update(encounter);
        assertTrue(success);

        Encounter actual = repository.findById(3);
        assertNotNull(actual);
        assertEquals("2022-09-01", actual.getWhen());
        assertEquals(EncounterType.VISION, actual.getType());
        assertEquals("Test Description", actual.getDescription());
        assertEquals(2, actual.getOccurrences());

    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(100000);

        boolean actual = repository.update(encounter);
        assertFalse(actual);
    }

    @Test
    void shouldDeleteExisting() throws DataAccessException {
        boolean actual = repository.deleteById(2);
        assertTrue(actual);

        Encounter encounter = repository.findById(2);
        assertNull(encounter);
    }

    @Test
    void shouldNotDeleteMissing() throws DataAccessException {
        boolean actual = repository.deleteById(100000);
        assertFalse(actual);

    }


}