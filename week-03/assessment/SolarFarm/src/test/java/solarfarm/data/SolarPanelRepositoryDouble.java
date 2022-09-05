package solarfarm.data;

import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelRepositoryDouble implements SolarPanelRepository {

    @Override
    public List<SolarPanel> findAll() throws DataAccessException {
        ArrayList<SolarPanel> all = new ArrayList<>();
        all.add(new SolarPanel(1,"The Ridge",1,1,2020, SolarPanelMaterial.MONOCRYSTALLINE_SILICON,true));
        all.add(new SolarPanel(2,"Treeline",1,2,2021, SolarPanelMaterial.AMORPHOUS_SILICON,true));
        all.add(new SolarPanel(3, "Flats",1,3,2022,SolarPanelMaterial.CADMIUM_TELLURIDE,false));
        return all;
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        for (SolarPanel solarPanel : findAll()) {
            if (solarPanel.getSection().contains(section)) {
                result.add(solarPanel);
            }
        }
        return result;
    }

    @Override
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (SolarPanel solarPanel : all) {
            if (solarPanel.getSection().contains(section) &&
                    solarPanel.getRow() == row &&
                    solarPanel.getColumn() == column) {
                return solarPanel;
            }
        }
        return null;
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        solarPanel.setId(99);
        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        return solarPanel.getId() > 0;
    }

    @Override
    public boolean deleteById(int solarPanelId) throws DataAccessException {
        return solarPanelId != 999;
    }
}
