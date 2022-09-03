package solarfarm.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelKey;
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
    void shouldCreate() throws DataAccessException {
        SolarPanel solarPanel = new SolarPanel(0,"Flats",22,33,2022,SolarPanelMaterial.AMORPHOUS_SILICON,true);
        SolarPanel actual = repository.create(solarPanel);
        assertEquals(16,actual.getId());
        List<SolarPanel> all = repository.findAll();
        assertEquals(16,all.size());

        SolarPanel idTen = all.get(9);
        assertEquals(10, idTen.getId());
        assertEquals("Treeline",idTen.getSection());
        assertEquals(31, idTen.getRow());
        assertEquals(21,idTen.getColumn());
        assertEquals(2016,idTen.getYearInstalled());
        assertEquals(SolarPanelMaterial.AMORPHOUS_SILICON,idTen.getMaterial());
        assertEquals(false,idTen.isTracking());

        SolarPanel idSixteen = all.get(15);
        assertEquals(16, idSixteen.getId());
        assertEquals("Flats",idSixteen.getSection());
        assertEquals(22, idSixteen.getRow());
        assertEquals(33,idSixteen.getColumn());
        assertEquals(2022,idSixteen.getYearInstalled());
        assertEquals(SolarPanelMaterial.AMORPHOUS_SILICON,idSixteen.getMaterial());
        assertEquals(true,idSixteen.isTracking());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        SolarPanel solarPanel = repository.findByKey("The Ridge",2,2);
        solarPanel.setMaterial(SolarPanelMaterial.AMORPHOUS_SILICON);
        solarPanel.setYearInstalled(2012);
        solarPanel.setTracking(false);

        boolean result = repository.update(solarPanel);
        assertTrue(result);

        solarPanel = repository.findByKey("The Ridge",2,2);

        assertNotNull(solarPanel);

        assertEquals(5,solarPanel.getId());
        assertEquals("The Ridge",solarPanel.getSection());
        assertEquals(2,solarPanel.getRow());
        assertEquals(2,solarPanel.getColumn());
        assertEquals(2012,solarPanel.getYearInstalled());
        assertEquals(SolarPanelMaterial.AMORPHOUS_SILICON,solarPanel.getMaterial());
        assertEquals(false,solarPanel.isTracking());

    }

    @Test
    void shouldNotUpdateUnknownId() throws DataAccessException {
        SolarPanel solarPanel = new SolarPanel(
                16,
                "The Ridge",
                99,
                101,
                2020,
                SolarPanelMaterial.MONOCRYSTALLINE_SILICON,
                true);

        boolean result = repository.update(solarPanel);

        assertFalse(result);

    }

    @Test
    void shouldDelete() throws DataAccessException {
        boolean result = repository.deleteById(14);

        assertTrue(result);

        List<SolarPanel> all = repository.findAll();

        assertEquals(14,all.size());

        SolarPanel solarPanel = repository.findByKey("Flats",91,81);

        assertNull(solarPanel);
    }

    @Test
    void shouldNotDeleteUnknownId() throws DataAccessException {
        boolean result = repository.deleteById(9999);

        assertFalse(result);
    }
}