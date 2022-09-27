package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolarPanelJdbcRepositoryTemplate implements SolarPanelRepository {

    private final JdbcTemplate jdbcTemplate;

    public SolarPanelJdbcRepositoryTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<SolarPanel> mapper = (resultSet, rowIndex) -> {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setId(resultSet.getInt("panel_id"));
        solarPanel.setSection(resultSet.getString("section"));
        solarPanel.setRow(resultSet.getInt("row"));
        solarPanel.setColumn(resultSet.getInt("column"));
        solarPanel.setYearInstalled(resultSet.getInt("year_installed"));
        solarPanel.setMaterial(Material.valueOf(resultSet.getString("material")));
        solarPanel.setTracking(resultSet.getBoolean("is_tracking"));

        return solarPanel;

    };

    @Override
    public List<SolarPanel> findAll() throws DataAccessException {
        final String sql = "select panel_id, section, `row`, `column`, year_installed, material, is_tracking " +
                "from panel " +
                "order by section, `row`, `column`;";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
//        final String sql = "select panel_id, section, `row`, `column`, " +
//                "year_installed, material, is_tracking from solar_panel" +
//                "from panel where section = ?;";
//        try{
//            return jdbcTemplate.query(sql,mapper,section);
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
        return null;
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }
}
