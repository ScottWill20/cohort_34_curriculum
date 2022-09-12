package learn.foraging.domain;

import learn.foraging.data.*;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForageService {

    private final ForageRepository forageRepository;
    private final ForagerRepository foragerRepository;
    private final ItemRepository itemRepository;

    public ForageService(ForageRepository forageRepository, ForagerRepository foragerRepository, ItemRepository itemRepository) {
        this.forageRepository = forageRepository;
        this.foragerRepository = foragerRepository;
        this.itemRepository = itemRepository;
    }

    public List<Forage> findByDate(LocalDate date) {

        Map<String, Forager> foragerMap = foragerRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));
        Map<Integer, Item> itemMap = itemRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));

        List<Forage> result = forageRepository.findByDate(date);
        for (Forage forage : result) {
            forage.setForager(foragerMap.get(forage.getForager().getId()));
            forage.setItem(itemMap.get(forage.getItem().getId()));
        }

        return result;
    }

    public Result<Forage> add(Forage forage) throws DataException {
        Result<Forage> result = validate(forage);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(forageRepository.add(forage));

        return result;
    }

    // TODO reportKgPerDay()

//    public Map<Item, DoubleSummaryStatistics> reportKgPerDay(LocalDate date) {
//        findByDate(date);
//        List<Forage> forages = forageRepository.findByDate(date);
//        forages.stream().collect(Collectors.groupingBy(Forage::getItem,
//                        Collectors.summarizingDouble(Forage::getKilograms)));
//
//        for (Item item : forages.keySet()) {
//            DoubleSummaryStatistics itemWeight = foragesByDate.get(item);
//            BigDecimal roundItemWeight = BigDecimal.valueOf(itemWeight.getSum()).setScale(2, RoundingMode.HALF_UP);
//            System.out.println(item.getName() + ": " + roundItemWeight + " kg");
//        }
//
//        return forages;
//    }

    // TODO publicReportItemCategoryValue()



    public int generate(LocalDate start, LocalDate end, int count) throws DataException {

        if (start == null || end == null || start.isAfter(end) || count <= 0) {
            return 0;
        }

        count = Math.min(count, 500);

        ArrayList<LocalDate> dates = new ArrayList<>();
        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        List<Item> items = itemRepository.findAll();
        List<Forager> foragers = foragerRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Forage forage = new Forage();
            forage.setDate(dates.get(random.nextInt(dates.size())));
            forage.setForager(foragers.get(random.nextInt(foragers.size())));
            forage.setItem(items.get(random.nextInt(items.size())));
            forage.setKilograms(random.nextDouble() * 5.0 + 0.1);
            forageRepository.add(forage);
        }

        return count;
    }

    private Result<Forage> validate(Forage forage) {

        Result<Forage> result = validateNulls(forage);
        if (!result.isSuccess()) {
            return result;
        }

        validateFields(forage, result);
        if (!result.isSuccess()) {
            return result;
        }

        // TODO implement validateUnique in validate
        validateUnique(forage, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(forage, result);

        return result;
    }

    private Result<Forage> validateNulls(Forage forage) {
        Result<Forage> result = new Result<>();

        if (forage == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forage.getDate() == null) {
            result.addErrorMessage("Forage date is required.");
        }

        if (forage.getForager() == null) {
            result.addErrorMessage("Forager is required.");
        }

        if (forage.getItem() == null) {
            result.addErrorMessage("Item is required.");
        }
        return result;
    }

    // TODO validateUnique
    private void validateUnique(Forage forage, Result<Forage> result) {

        LocalDate date = forage.getDate();
        List<Forage> dupeForage = forageRepository.findByDate(date).stream()
                .filter(f -> f.getDate().equals(forage.getDate())
                        && f.getItem().getId() == forage.getItem().getId()
                        && f.getForager().getId().equals(forage.getForager().getId())
                        && !f.getId().equalsIgnoreCase(forage.getId())).toList();

        if (dupeForage.size() > 0) {
            result.addErrorMessage(String.format("Forage on %s of %s by %s %s is a duplicate.",
                    forage.getDate(),
                    forage.getItem().getName(),
                    forage.getForager().getFirstName(),
                    forage.getForager().getLastName()));
        }

    }

    private void validateFields(Forage forage, Result<Forage> result) {
        // No future dates.
        if (forage.getDate().isAfter(LocalDate.now())) {
            result.addErrorMessage("Forage date cannot be in the future.");
        }

        if (forage.getKilograms() <= 0 || forage.getKilograms() > 250.0) {
            result.addErrorMessage("Kilograms must be a positive number less than 250.0");
        }

    }

    private void validateChildrenExist(Forage forage, Result<Forage> result) {

        if (forage.getForager().getId() == null
                || foragerRepository.findById(forage.getForager().getId()) == null) {
            result.addErrorMessage("Forager does not exist.");
        }

        if (itemRepository.findById(forage.getItem().getId()) == null) {
            result.addErrorMessage("Item does not exist.");
        }
    }
}
