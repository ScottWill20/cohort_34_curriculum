package learn.foraging.data;

import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    private static final String SEED_FILE_PATH = "./data/foragers-seed.csv";
    private static final String TEST_FILE_PATH = "./data/foragers-test.csv";
    private final ForagerFileRepository repository = new ForagerFileRepository(TEST_FILE_PATH);

    @BeforeEach
    public void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        ForagerFileRepository repo = new ForagerFileRepository("./data/foragers.csv");
        List<Forager> all = repo.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Forager forager = new Forager("","Test", "Testington", "GA");
        Forager actual = repository.add(forager);
        assertEquals(forager.getId(),actual.getId());
        List<Forager> all = repository.findAll();
        assertEquals(6,all.size());
    }

}