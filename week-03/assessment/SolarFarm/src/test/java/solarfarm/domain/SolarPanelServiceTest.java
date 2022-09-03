package solarfarm.domain;

import org.junit.jupiter.api.Test;
import solarfarm.data.DataAccessException;
import solarfarm.data.SolarPanelRepository;
import solarfarm.data.SolarPanelRepositoryDouble;
import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import static org.junit.jupiter.api.Assertions.*;

class SolarPanelServiceTest {

    SolarPanelRepository repository = new SolarPanelRepositoryDouble();
    SolarPanelService service = new SolarPanelService(repository);

    @Test
    void shouldCreateSolarPanel() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0, "Treeline",1,4,2022, SolarPanelMaterial.AMORPHOUS_SILICON,true));
        assertNotNull(actual.getSolarPanel());
        assertTrue(actual.isSuccess());

        assertEquals(99,actual.getSolarPanel().getId());
    }

    @Test
    void shouldNotCreateNullLogEntry() throws DataAccessException {
        SolarPanelResult actual = service.create(null);

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("Solar Panel entry cannot be null.",actual.getMessages().get(0));
    }

    @Test
    void shouldNotCreateWithEmptySection() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0,"     ",1,4,2022,SolarPanelMaterial.AMORPHOUS_SILICON,true));

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("Section is required.",actual.getMessages().get(0));
    }

    @Test
    void shouldNotCreateWithInvalidRow() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0,"Treeline",0,4,2022,SolarPanelMaterial.AMORPHOUS_SILICON,true));

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("Row must be a number between 1 and 250.",actual.getMessages().get(0));

    }
    @Test
    void shouldNotCreateWithInvalidColumn() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0,"Treeline",1,251,2022,SolarPanelMaterial.AMORPHOUS_SILICON,true));

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("Column must be a number between 1 and 250.",actual.getMessages().get(0));
    }

    @Test
    void shouldNotCreateWithInvalidYear() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0,"Treeline",1,4,2023,SolarPanelMaterial.AMORPHOUS_SILICON,true));

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("That is not a valid year.",actual.getMessages().get(0));
    }

    @Test
    void shouldNotCreateWithoutMaterial() throws DataAccessException {
        SolarPanelResult actual = service.create(new SolarPanel(0,"Treeline",1,4,2022,null,true));

        assertFalse(actual.isSuccess());
        assertNull(actual.getSolarPanel());
        assertEquals("Material type is required.",actual.getMessages().get(0));
    }

//    @Test
//    void shouldNotCreateWithInvalidTracking() throws DataAccessException {
//        SolarPanelResult actual = service.create(new SolarPanel(0,"Treeline",1,4,2022,SolarPanelMaterial.AMORPHOUS_SILICON,));
//
//        assertFalse(actual.isSuccess());
//        assertNull(actual.getSolarPanel());
//        assertEquals("Material type is required.",actual.getMessages().get(0));
//    }


    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByKey() {
    }

    @Test
    void findBySection() {
    }

}