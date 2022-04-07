package is.citizen.technicaltest.service.impl;

import is.citizen.technicaltest.dto.FilterDto;
import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.model.PersonModel;
import is.citizen.technicaltest.dto.SortDto;
import is.citizen.technicaltest.model.SortType;
import is.citizen.technicaltest.service.PersonService;
import is.citizen.technicaltest.utils.BuilderUtils;
import is.citizen.technicaltest.utils.CsvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<PersonModel> normalize(List<PersonModel> personModels) {
        log.info("Normalise and complete all the missing data fields for all persons & addresses");

        return null;
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
}