package solarfarm.domain;

import org.springframework.stereotype.Service;
import solarfarm.data.DataAccessException;
import solarfarm.data.SolarPanelRepository;
import solarfarm.models.SolarPanel;

import java.util.List;
import java.util.Objects;

@Service
public class SolarPanelService {
    private final SolarPanelRepository repository;

    public SolarPanelService(SolarPanelRepository repository) {

        this.repository = repository;
    }

    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public SolarPanelResult create(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);

        if (!result.isSuccess()) {
            return result;
        }

        if (solarPanel.getId() > 0) {
            result.addMessage("cannot create existing log entry.");
        }

        List<SolarPanel> solarPanels = repository.findAll();
        for (SolarPanel s : solarPanels) {
            if (Objects.equals(solarPanel.getSection(), s.getSection())
                    && Objects.equals(solarPanel.getRow(), s.getRow())
                    && Objects.equals(solarPanel.getColumn(), s.getColumn())
                    && solarPanel.getId() != s.getId()) {
                result.addMessage("There is already a Solar Panel at that location.");
                return result;
            }
        }

        solarPanel = repository.create(solarPanel);
        result.setSolarPanel(solarPanel);
        return result;
    }

    public SolarPanelResult update(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);
        if (!result.isSuccess()) {
            return result;
        }

        List<SolarPanel> solarPanels = repository.findAll();
        for (SolarPanel s : solarPanels) {
            if (Objects.equals(solarPanel.getSection(), s.getSection())
                    && Objects.equals(solarPanel.getRow(), s.getRow())
                    && Objects.equals(solarPanel.getColumn(), s.getColumn())
                    && solarPanel.getId() != s.getId()) {
                result.addMessage("There is already a Solar Panel at that location.");
                return result;
            }
        }
        boolean updated = repository.update(solarPanel);

        if (!updated) {
            result.addMessage(String.format("Solar Panel %s-%s-%s does not exist.", solarPanel.getSection(), solarPanel.getRow(), solarPanel.getColumn()));
        }
        return result;
    }

    public SolarPanelResult deleteById(int solarPanelId) throws DataAccessException {
        SolarPanelResult result = new SolarPanelResult();
        if (!repository.deleteById(solarPanelId)) {
            result.addMessage(String.format("Solar Panel with ID %s does not exist.", solarPanelId));
        }
        return result;
    }

    // HELPER METHODS
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        return repository.findByKey(section,row,column);
    }

    private SolarPanelResult validate(SolarPanel solarPanel) {
        SolarPanelResult result = new SolarPanelResult();

        if (solarPanel == null) {
            result.addMessage("Solar Panel entry cannot be null.");
            return result;
        }
        if (solarPanel.getSection() == null || solarPanel.getSection().isBlank()) {
            result.addMessage("Section is required.");
            return result;
        }
        if (solarPanel.getRow() < 1 || solarPanel.getRow() > 250) {
            result.addMessage("Row must be a number between 1 and 250.");
            return result;
        }
        if (solarPanel.getColumn() < 1 || solarPanel.getColumn() > 250) {
            result.addMessage("Column must be a number between 1 and 250.");
            return result;
        }
        if (solarPanel.getYearInstalled() > 2022) {
            result.addMessage("That is not a valid year.");
            return result;
        }
        if (solarPanel.getMaterial() == null) {
            result.addMessage("Material type is required.");
            return result;
        }
        if (solarPanel.isTracking() != true && solarPanel.isTracking() != false) {
            result.addMessage("Is Tracking is required[true/false].");
            return result;
        }
        return result;
    }

}
