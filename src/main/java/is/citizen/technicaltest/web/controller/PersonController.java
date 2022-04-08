package is.citizen.technicaltest.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Getting persons from file", description = "API to get persons information from a file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping("/file")
    public ResponseEntity<?> getPersonsFromFile(
            @RequestParam(name = "file") MultipartFile file,
            @Parameter(example = "city,ASC") @RequestParam(value = "sort", required = false) String sort,
            @Parameter(example = "city,London") @RequestParam(value = "filter", required = false) String filter,
            @Parameter(example = "city") @RequestParam(value = "group", required = false) String group,
            @Parameter(example = "true") @RequestParam(value = "normalize", required = false) boolean isNormalize) {
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

    @Operation(summary = "Getting persons from JSON", description = "API to get persons information from a JSON data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPersons(
            @Parameter(example = "city,ASC") @RequestParam(value = "sort", required = false) String sort,
            @Parameter(example = "city,London") @RequestParam(value = "filter", required = false) String filter,
            @Parameter(example = "city") @RequestParam(value = "group", required = false) String group,
            @Parameter(example = "true") @RequestParam(value = "normalize", required = false) boolean isNormalize,
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
