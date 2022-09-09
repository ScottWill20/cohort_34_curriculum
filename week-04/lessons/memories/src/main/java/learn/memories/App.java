package learn.memories;

import learn.memories.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan // 2. Tells Spring to scan all classes in this package or its children.
public class App {

    public static void main(String[] args) {
        // 1. We pass the App.class, this class, as a constructor argument.
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        // 3. The context works the same as the XML context.
        Controller controller = context.getBean(Controller.class);
        // Run the app!
        controller.run();
    }

}
