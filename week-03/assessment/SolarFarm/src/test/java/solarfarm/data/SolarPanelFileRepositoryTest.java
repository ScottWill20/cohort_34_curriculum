package solarfarm.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolarPanelFileRepositoryTest {

    private static final String SEED_FILE_PATH = "./data/solar-panels-seed.csv";
    private static final String TEST_FILE_PATH = "./data/solar-panels-test.csv";

    private final SolarPanelFileRepository repository = new SolarPanelFileRepository(TEST_FILE_PATH);

    @BeforeEach
    public void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<SolarPanel> actual = repository.findAll();
        assertEquals(15,actual.size());

        SolarPanel solarPanel = actual.get(14);
        assertEquals(15,solarPanel.getId());
        assertEquals("Flats",solarPanel.getSection());
        assertEquals(101,solarPanel.getRow());
        assertEquals(91,solarPanel.getColumn());
        assertEquals(2022,solarPanel.getYearInstalled());
        assertEquals(SolarPanelMaterial.COPPER_INDIUM_GALLIUM_SELENIDE, solarPanel.getMaterial());
    }

    @Test
    void shouldFindFiveOfEachSection() throws DataAccessException {
        List<SolarPanel> ridge = repository.findBySection("The Ridge");
        assertNotNull(ridge);
        assertEquals(5,ridge.size());

        List<SolarPanel> tree = repository.findBySection("Treeline");
        assertNotNull(tree);
        assertEquals(5,tree.size());

        List<SolarPanel> flats = repository.findBySection("Flats");
        assertNotNull(flats);
        assertEquals(5,flats.size());



    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}