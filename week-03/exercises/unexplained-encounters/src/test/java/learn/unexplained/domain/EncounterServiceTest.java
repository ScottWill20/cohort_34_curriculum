package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    EncounterService service = new EncounterService(new EncounterRepositoryDouble());

    @Test
    void shouldNotAddNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddEmptyDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "  ", 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", null, 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroOccurrences() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 0);
        EncounterResult expected = makeResult("occurrences must be greater than 0");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(5, EncounterType.UFO, "Test Date 2", "Test Description 2", 2);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 1);
        EncounterResult expected = new EncounterResult();
        expected.setPayload(encounter);

        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }

    @Test
    void shouldFindByType () throws DataAccessException {
        List<Encounter> ufos = service.findByType(EncounterType.UFO);
        assertNotNull(ufos);
        assertEquals(1, ufos.size());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        EncounterResult result = service.update(
                new Encounter(3, EncounterType.SOUND, "2022-04-04 11:00 PM", "test", 1));
                assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateEmptyWhen() throws DataAccessException {
        EncounterResult result = service.update(
                new Encounter(3, EncounterType.SOUND, "         ", "Test Description", 1));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateEmptyDescription () throws DataAccessException {
        EncounterResult result = service.update(
                new Encounter(3, EncounterType.SOUND, "2022-04-04 11:00 PM", "          ", 1));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateZeroOccurrences() throws DataAccessException {
        EncounterResult result = service.update(
                new Encounter(3, EncounterType.SOUND, "2022-04-04 11:00 PM", "          ", 0));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateNonExistentEncounter() throws DataAccessException {
        EncounterResult result = service.update(
                new Encounter(99, EncounterType.SOUND, "2022-04-04 11:00 PM", "          ", 1));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        EncounterResult result = service.deleteById(12);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDelete() throws DataAccessException {
        EncounterResult result = service.deleteById(10);
        assertFalse(result.isSuccess());
    }

}