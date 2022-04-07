package is.citizen.technicaltest.service;

import is.citizen.technicaltest.model.PersonModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PersonService {
    List<PersonModel> processDataFromFile(MultipartFile file);

    List<PersonModel> sortAndFilter(List<PersonModel> personModels, String sort, String filter);

    List<PersonModel> normalize(List<PersonModel> personModels);

    Map<String, List<PersonModel>> groupPersons(List<PersonModel> persons, String group);
}