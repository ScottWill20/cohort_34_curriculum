package learn.solarfarm;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelJdbcRepositoryTemplate;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.SolarPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public class App {
    public static void main(String[] args) throws DataAccessException {
        System.out.println("Welcome to Solar Farm!");

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        SolarPanelRepository repository = context.getBean(SolarPanelJdbcRepositoryTemplate.class);

        List<SolarPanel> panels = repository.findAll();

        for (SolarPanel solarPanel : panels) {
            System.out.println(solarPanel);
        }

    }

}
