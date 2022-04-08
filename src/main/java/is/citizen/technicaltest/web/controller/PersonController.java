package is.citizen.technicaltest.web.controller;

import is.citizen.technicaltest.model.PersonModel;
import is.citizen.technicaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/file")
    public ResponseEntity<?> getPersonsFromFile(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "group", required = false) String group,
            @RequestParam(value = "normalize", required = false) boolean isNormalize) {
        List<PersonModel> persons = personService.processDataFromFile(file);
        if (isNormalize) {
            persons = personService.normalize(persons);
        }
        if (sort != null || filter != null) {
            persons = personService.sortAndFilter(persons, sort, filter);
        }
        return group != null
                ? new ResponseEntity<>(personService.groupPersons(persons, group), HttpStatus.OK)
                : new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPersons(
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "group", required = false) String group,
            @RequestParam(value = "normalize", required = false) boolean isNormalize,
            @RequestBody List<PersonModel> persons) {
        if (isNormalize) {
            persons = personService.normalize(persons);
        }
        if (sort != null || filter != null) {
            persons = personService.sortAndFilter(persons, sort, filter);
        }
        return group != null
                ? new ResponseEntity<>(personService.groupPersons(persons, group), HttpStatus.OK)
                : new ResponseEntity<>(persons, HttpStatus.OK);
    }
}
