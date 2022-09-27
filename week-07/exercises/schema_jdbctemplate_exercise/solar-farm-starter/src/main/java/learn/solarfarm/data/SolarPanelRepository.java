package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarPanelRepository {

    List<SolarPanel> findAll() throws DataAccessException;

    List<SolarPanel> findBySection(String section) throws DataAccessException;

    SolarPanel findById(int id) throws DataAccessException;

    SolarPanel create(SolarPanel solarPanel) throws DataAccessException;

    boolean update(SolarPanel solarPanel) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}


