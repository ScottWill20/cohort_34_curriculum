package solarfarm.data;

import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolarPanelFileRepository implements SolarPanelRepository {
    private static final String DELIMITER = ",";
    private final String DELIMITER_REPLACEMENT = "@@@";
    private String filePath;

    public SolarPanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    // METHODS

    // READ
    @Override
    public List<SolarPanel> findAll() throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                SolarPanel solarPanel = deserialize(line);
                result.add(solarPanel);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            throw new DataAccessException("Could not open file path: " + filePath,ex);
        }
        return result;
    }
    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        for (SolarPanel solarPanel : findAll()) {
            if (solarPanel.getSection() == section) {
                result.add(solarPanel);
            }
        }
        return result;
    }

    // CREATE
    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        int nextId = getNextId(all);
        solarPanel.setId(nextId);
        all.add(solarPanel);
        writeToFile(all);
        return solarPanel;
    }

    // UPDATE
    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == solarPanel.getId()) {
                all.set(i,solarPanel);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    // DELETE
    @Override
    public boolean deleteById(int solarPanelId) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == solarPanelId) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private String restore(String value) {
        return value.replace(DELIMITER_REPLACEMENT,DELIMITER);
    }

    private String clean (String value) {
        return  value.replace(DELIMITER,DELIMITER_REPLACEMENT);
    }

    // HELPER METHODS

    private String serialize(SolarPanel solarPanel) {
        StringBuilder buffer = new StringBuilder(1000);
        buffer.append(solarPanel.getId()).append(DELIMITER);
        buffer.append(clean(solarPanel.getSection())).append(DELIMITER);
        buffer.append(solarPanel.getRow()).append(DELIMITER);
        buffer.append(solarPanel.getColumn()).append(DELIMITER);
        buffer.append(solarPanel.getYearInstalled()).append(DELIMITER);
        buffer.append(solarPanel.getMaterial()).append(DELIMITER);
        buffer.append(solarPanel.isTracking());
        return buffer.toString();

    }

    private SolarPanel deserialize(String line) {
        String[] fields = line.split(DELIMITER);
        if (fields.length != 7) {
            return null;
        }

        SolarPanel solarPanel = new SolarPanel(
                Integer.parseInt(fields[0]),
                restore(fields[1]),
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3]),
                Integer.parseInt(fields[4]),
                SolarPanelMaterial.valueOf(fields[5]),
                Boolean.parseBoolean(fields[6]));
        return solarPanel;
    }

    private void writeToFile(List<SolarPanel> solarPanels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("id,section,row,column,yearInstalled,material,isTracking");

            for (SolarPanel solarPanel : solarPanels) {
                String line = serialize(solarPanel);
                writer.println(line);
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not write to file path " + filePath);
        }

    }

    private int getNextId(List<SolarPanel> solarPanels) {
        int maxId = 0;
        for (SolarPanel solarPanel : solarPanels) {
            if (maxId < solarPanel.getId()) {
                maxId = solarPanel.getId();
            }
        }
        return maxId + 1;
    }






}
