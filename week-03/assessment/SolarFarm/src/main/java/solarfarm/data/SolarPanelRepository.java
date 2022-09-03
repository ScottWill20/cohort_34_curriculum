package solarfarm.data;

import solarfarm.models.SolarPanel;

import java.util.List;

public interface SolarPanelRepository {
    // READ
    List<SolarPanel> findAll() throws DataAccessException;

    List<SolarPanel> findBySection(String section) throws DataAccessException;

    // CREATE
    SolarPanel create(SolarPanel solarPanel) throws DataAccessException;

    // UPDATE
    boolean update(SolarPanel solarPanel) throws DataAccessException;

    // DELETE
    boolean deleteById(int solarPanelId) throws DataAccessException;
}
