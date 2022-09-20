package learn.foraging.ui;

import learn.foraging.data.DataException;
import learn.foraging.domain.ForageService;
import learn.foraging.domain.ForagerService;
import learn.foraging.domain.ItemService;
import learn.foraging.domain.Result;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Controller {

    private final ForagerService foragerService;
    private final ForageService forageService;
    private final ItemService itemService;
    private final View view;

    @Autowired
    public Controller(ForagerService foragerService, ForageService forageService, ItemService itemService, View view) {
        this.foragerService = foragerService;
        this.forageService = forageService;
        this.itemService = itemService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Sustainable Foraging");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_FORAGES_BY_DATE:
                    viewByDate();
                    break;
                case VIEW_FORAGERS_BY_STATE:
                    viewForagersByState();
                    break;
                case VIEW_ITEMS:
                    viewItems();
                    break;
                case ADD_FORAGE:
                    addForage();
                    break;
                case ADD_FORAGER:
                    addForager();
                    break;
                case ADD_ITEM:
                    addItem();
                    break;
                case REPORT_KG_PER_ITEM:
                    itemDayInKg();
                    break;
                case REPORT_CATEGORY_VALUE:
                    categoryValueByDay();
                    break;
                case GENERATE:
                    generate();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    // top level menu
    private void viewByDate() {
        LocalDate date = view.getForageDate();
        List<Forage> forages = forageService.findByDate(date);
        view.displayForages(forages);
        view.enterToContinue();
    }
    private void viewForagersByState() {
        String state = view.getForagerStateAbbreviation();
        List<Forager> foragers = foragerService.findByState(state);
        view.displayHeader("Foragers in " + state);
        view.displayForagers(foragers);
        view.enterToContinue();
    }

    private void viewItems() {
        view.displayHeader(MainMenuOption.VIEW_ITEMS.getMessage());
        Category category = view.getItemCategory();
        List<Item> items = itemService.findByCategory(category);
        view.displayHeader("Items");
        view.displayItems(items);
        view.enterToContinue();
    }

    private void addForage() throws DataException {
        view.displayHeader(MainMenuOption.ADD_FORAGE.getMessage());
        Forager forager = getForager();
        if (forager == null) {
            return;
        }
        Item item = getItem();
        if (item == null) {
            return;
        }
        Forage forage = view.makeForage(forager, item);
        Result<Forage> result = forageService.add(forage);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Forage of %s by %s %s on %s created.",
                    result.getPayload().getItem().getName(),
                    result.getPayload().getForager().getFirstName(),
                    result.getPayload().getForager().getLastName(),
                    result.getPayload().getDate());
            view.displayStatus(true, successMessage);
        }
    }

    // TODO addForager()
    private void addForager() throws DataException {
        Forager forager = view.makeForager();
        Result<Forager> result = foragerService.add(forager);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Forager %s %s, %s created.",
                    result.getPayload().getFirstName(),
                    result.getPayload().getLastName(),
                    result.getPayload().getState());

            view.displayStatus(true, successMessage);
        }
    }

    private void addItem() throws DataException {
        Item item = view.makeItem();
        Result<Item> result = itemService.add(item);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Item %s created.", result.getPayload().getId());
            view.displayStatus(true, successMessage);
        }
    }

    // TODO itemDayInKg()
    // Nearly had this figured out - values of each item were all correct sums, but key was null -
        // I wanted to make sure I had a working method for code review
    // To ensure I could write tests I tried to drop the HashMap creation to ForageService method,
        // that code is commented out in ForageService
    // fixing the structure of these reports would hopefully allow me to sort alphabetically/by total value
        // AFTER the summation
    // Print methods were not the cause of null key value, but those key values are not visible in debugging until
        // the print method is called
    private void itemDayInKg() {
        view.displayHeader(MainMenuOption.REPORT_KG_PER_ITEM.getMessage());
        LocalDate date = view.getForageDate();
        List<Forage> forages = forageService.findByDate(date);
        Map<Category, DoubleSummaryStatistics> foragesByDate = forageService.reportKgPerDay(date);

        view.displayItemDayInKg(foragesByDate);

//        Map<Item, DoubleSummaryStatistics> foragesByDate = forages.stream()
//                .collect(Collectors.groupingBy(Forage::getItem,
//                        Collectors.summarizingDouble(Forage::getKilograms)));
//    // Change I would make: No need to print Inedible and Poisonous items, sum of each plants total forage is always zero
//        for (Item item : foragesByDate.keySet()) {
//            DoubleSummaryStatistics itemWeight = foragesByDate.get(item);
//            BigDecimal roundItemWeight = BigDecimal.valueOf(itemWeight.getSum()).setScale(2, RoundingMode.HALF_UP);
//            System.out.println(item.getName() + ": " + roundItemWeight + " kg");
//        }
        view.enterToContinue();

    }

    // TODO categoryValueByDay()
    private void categoryValueByDay() {
        view.displayHeader(MainMenuOption.REPORT_CATEGORY_VALUE.getMessage());
        LocalDate date = view.getForageDate();
        List<Forage> forages = forageService.findByDate(date);

        Map<Category, DoubleSummaryStatistics> sumValueCategory = forages.stream()
                .collect(Collectors.groupingBy(i -> i.getItem().getCategory(),
                        Collectors.summarizingDouble(i -> i.getValue().doubleValue())));

        // Change I would make: No need to print Inedible and Poisonous categories, sum of each category is always zero
        for (Category category : sumValueCategory.keySet()) {
            DoubleSummaryStatistics sumValue = sumValueCategory.get(category);
            BigDecimal roundSumValue = BigDecimal.valueOf(sumValue.getSum()).setScale(2,RoundingMode.HALF_UP);
            System.out.println(category + ": $" + roundSumValue);
        }



    }

    private void generate() throws DataException {
        GenerateRequest request = view.getGenerateRequest();
        if (request != null) {
            int count = forageService.generate(request.getStart(), request.getEnd(), request.getCount());
            view.displayStatus(true, String.format("%s forages generated.", count));
        }
    }

    // support methods
    private Forager getForager() {
        String lastNamePrefix = view.getForagerNamePrefix();
        List<Forager> foragers = foragerService.findByLastName(lastNamePrefix);
        return view.chooseForager(foragers);
    }

    private Item getItem() {
        Category category = view.getItemCategory();
        List<Item> items = itemService.findByCategory(category);
        return view.chooseItem(items);
    }
}
