package is.citizen.technicaltest.service.impl;

import is.citizen.technicaltest.dto.FilterDto;
import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.model.PersonModel;
import is.citizen.technicaltest.dto.SortDto;
import is.citizen.technicaltest.model.SortType;
import is.citizen.technicaltest.service.PersonService;
import is.citizen.technicaltest.utils.BuilderUtils;
import is.citizen.technicaltest.utils.ComparativeHelper;
import is.citizen.technicaltest.utils.CsvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static is.citizen.technicaltest.utils.BuilderUtils.formatAddress;
import static is.citizen.technicaltest.utils.CommonConstants.*;
import static is.citizen.technicaltest.utils.ComparativeHelper.*;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<PersonModel> processDataFromFile(MultipartFile file) {
        log.info(MessageFormat.format("Processing data from the received file {0}.", file.getOriginalFilename()));
        if (CsvHelper.hasCsvFormat(file)) {
            log.info(MessageFormat.format("Process CSV format data from {0} file.", file.getOriginalFilename()));
            return CsvHelper.csvToPersons(file);
        } else {
            throw new BadRequestException("The file has an invalid format. Only the CSV file format is allowed.");
        }
    }

    @Override
    public List<PersonModel> sortAndFilter(List<PersonModel> persons, String sort, String filter) {
        if (filter != null) {
            FilterDto filterDto = BuilderUtils.buildFilterDtoFromString(filter);
            persons = filter(persons, filterDto);
        }
        if (sort != null) {
            SortDto sortDto = BuilderUtils.buildSortDtoFromString(sort);
            persons = sort(persons, sortDto);
        }
        return persons;
    }

    @Override
    public List<PersonModel> normalize(List<PersonModel> persons) {
        log.info("Normalise and complete all the missing data fields for all persons & addresses");
        return persons
                .stream()
                .peek(this::updateAddressInformation)
                .peek(p1 -> peekPersons(persons, p2 -> countryCodeCondition(p1, p2),
                        p2 -> p1.setCountryCode(p2.getCountryCode())))
                .peek(p1 -> peekPersons(persons, p2 -> stateCondition(p1, p2), p2 -> p1.setState(p2.getState())))
                .peek(p1 -> peekPersons(persons, p2 -> surnameCondition(p1, p2), p2 -> p1.setSurname(p2.getSurname())))
                .peek(p1 -> peekPersons(persons,
                        p2 -> !isEmpty(p2.getFirstname()) && !isEmpty(p2.getPostcode()),
                        p2 -> {
                            if (getSecondAddress(p1, p2) != null) {
                                p1.setSecondAddress(getSecondAddress(p1, p2));
                            }
                        }))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<PersonModel>> groupPersons(List<PersonModel> persons, String group) {
        log.info(MessageFormat.format("Group persons by the received parameter group = {0}.", group));
        return persons.stream()
                .collect(Collectors.groupingBy(person -> getFieldValue(person, group)));
    }

    private String getFieldValue(PersonModel person, String fieldName) {
        try {
            Field field = PersonModel.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(person).toString();
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new BadRequestException(MessageFormat.format("The parameter {0} is invalid", fieldName), ex);
        }
    }

    private List<PersonModel> sort(List<PersonModel> persons, SortDto sort) {
        log.info(MessageFormat.format("Sort persons by the received parameter sort = {0}.", sort));
        return persons
                .stream()
                .sorted(Comparator.comparing(person -> getFieldValue(person, sort.getParameter()),
                        (person1, person2) -> sort.getType().equals(SortType.ASC)
                                ? person1.compareTo(person2)
                                : person2.compareTo(person1)))
                .collect(Collectors.toList());
    }

    private List<PersonModel> filter(List<PersonModel> persons, FilterDto filter) {
        log.info(MessageFormat.format("Filter persons by the received parameter filter = {0}.", filter));
        return persons
                .stream()
                .filter(person -> Objects.equals(getFieldValue(person, filter.getParameter()), filter.getValue()))
                .collect(Collectors.toList());
    }

    private void updateAddressInformation(PersonModel person) {
        if (person.getCountryCode().equals(GB_COUNTRY_CODE)) {
            person.setCountryCode(UK_COUNTRY_CODE);
        }
        person.setPostcode(person.getPostcode().replaceAll(" ", ""));
        person.setFirstAddress(formatAddress(person.getFirstAddress()));
        person.setSecondAddress(formatAddress(person.getSecondAddress()));
    }

    private String getSecondAddress(PersonModel p1, PersonModel p2) {
        if ((ComparativeHelper.isEmpty(p1.getSecondAddress()) && !ComparativeHelper.isEmpty(p2.getFirstAddress()))) {
            if (p1.getFirstAddress().equals(p2.getFirstAddress()) && !ComparativeHelper.isEmpty(p2.getSecondAddress())) {
                return p2.getSecondAddress();
            }
            if (p1.getFirstAddress().equals(p2.getSecondAddress())) {
                return p2.getFirstAddress();
            }
            if (p1.getPostcode().equals(p2.getPostcode())) {
                return p1.getFirstAddress().equals(p2.getFirstAddress()) ? p2.getSecondAddress() : p2.getFirstAddress();
            }
        }
        return null;
    }

    private void peekPersons(List<PersonModel> persons, Predicate<PersonModel> condition, Consumer<PersonModel> action) {
        persons
                .stream()
                .filter(condition)
                .findFirst()
                .ifPresent(action);
    }
}