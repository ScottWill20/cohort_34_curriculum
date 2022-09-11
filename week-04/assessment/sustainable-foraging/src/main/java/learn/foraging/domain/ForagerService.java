package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    // TODO add() method
    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

    // TODO validate() method

    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = validateNulls(forager);
        if (!result.isSuccess()) {
            return result;
        }

        validateUnique(forager, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private Result<Forager> validateNulls(Forager forager) {
        Result<Forager> result = new Result<>();

        if (forager == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("Forager first name is required.");
        }

        if (forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("Forager last name is required.");
        }

        if (forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("State is required.");
        }
        return result;
    }

    private void validateUnique(Forager forager, Result<Forager> result) {

        List<Forager> dupeForagers = repository.findAll().stream()
                .filter(f -> f.getFirstName().equalsIgnoreCase(forager.getFirstName())
                        && f.getLastName().equalsIgnoreCase(forager.getLastName())
                        && f.getState().equalsIgnoreCase(forager.getState())
                        && !f.getId().equalsIgnoreCase(forager.getId())).toList();

        if (dupeForagers.size() > 0) {
            result.addErrorMessage(String.format("Forager %s %s, %s is a duplicate.",
                        forager.getFirstName(), forager.getLastName(), forager.getState()));
        }

    }




}
