package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForagerServiceTest {

    ForagerRepository repository = new ForagerRepositoryDouble();
    ForagerService service = new ForagerService(repository);

    @Test
    void shouldCreateForager() throws DataException {
        Result<Forager> actual = service.add(new Forager("","Test", "Testington", "KS"));
        assertNotNull(actual.getPayload());
        assertTrue(actual.isSuccess());

        assertEquals("73df34cd-0f6c-4446-93c0-460390407241",actual.getPayload().getId());
    }

    @Test
    void shouldNotCreateNullForager() throws DataException {
        Result<Forager> actual = service.add(null);

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());
        assertEquals("Nothing to save.",actual.getErrorMessages().get(0));
    }

    @Test
    void shouldNotCreateDuplicate() throws DataException {
        Result<Forager> actual = service.add(new Forager("","Jilly", "Sisse", "GA"));
        assertFalse(actual.isSuccess());
        assertEquals("Forager Jilly Sisse, GA is a duplicate.", actual.getErrorMessages().get(0));
    }

    @Test
    void shouldNotCreateWithEmptyNameOrState() throws DataException {
        Result<Forager> actual = service.add(new Forager("", "Test","     ","KS"));

        assertFalse(actual.isSuccess());
        assertNull(actual.getPayload());

        Result<Forager> actual1 = service.add(new Forager("", "         ","Testington","KS"));

        assertFalse(actual1.isSuccess());
        assertNull(actual1.getPayload());

        Result<Forager> actual2 = service.add(new Forager("", "Test","Testington","     "));

        assertFalse(actual2.isSuccess());
        assertNull(actual2.getPayload());
    }


}