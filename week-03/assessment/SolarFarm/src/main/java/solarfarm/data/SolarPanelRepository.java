package solarfarm.data;

import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelKey;

import java.util.List;

public interface SolarPanelRepository {
    // READ

    // READ
    List<SolarPanel> findAll() throws DataAccessException;

    List<SolarPanel> findBySection(String section) throws DataAccessException;

    SolarPanel findByKey(String section, int row, int column) throws DataAccessException;

    // CREATE
    SolarPanel create(SolarPanel solarPanel) throws DataAccessException;

    // UPDATE
    boolean update(SolarPanel solarPanel) throws DataAccessException;

    // DELETE
    boolean deleteById(int solarPanelId) throws DataAccessException;
}
