package solarfarm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import solarfarm.data.SolarPanelFileRepository;
import solarfarm.domain.SolarPanelService;
import solarfarm.ui.Controller;
import solarfarm.ui.View;

@ComponentScan
@PropertySource("classpath:data.properties")
public class App {
    public static void main(String[] args) {
        configureWithXMLandRun();
    }

    private static void configureWithAnnotationsAndRun() {
        ApplicationContext container = new AnnotationConfigApplicationContext(App.class);
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    private static void configureWithXMLandRun() {
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-config.xml");
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    private static void configureManuallyAndRun() {
        SolarPanelFileRepository repository = new SolarPanelFileRepository("./data/solar-panels.csv");
        View view = new View();
        SolarPanelService service = new SolarPanelService(repository);
        Controller controller = new Controller(view,service);

        controller.run();
    }
}
