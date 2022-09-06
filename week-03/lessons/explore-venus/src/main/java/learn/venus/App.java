package learn.venus;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterFileRepository;
import learn.venus.domain.OrbiterService;
import learn.venus.ui.Controller;
import learn.venus.ui.View;

public class App {

    public static void main(String[] args) throws DataAccessException {
        OrbiterFileRepository repository = new OrbiterFileRepository("./data/orbiters.csv");

        View view = new View();
        OrbiterService service = new OrbiterService(repository);
        Controller controller = new Controller(service,view);

        controller.run();


    }
}
