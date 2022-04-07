package is.citizen.technicaltest.utils;

import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.model.PersonModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CsvHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<PersonModel> csvToPersons(MultipartFile file) {
        log.info("Parse CSV data into a person list.");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                        .withIgnoreHeaderCase().withTrim())) {
            List<PersonModel> persons = new ArrayList<>();
            for (CSVRecord csv : csvParser.getRecords()) {
                persons.add(BuilderUtils.buildPersonFromCsvRecord(csv));
            }
            return persons;
        } catch (IOException e) {
            throw new BadRequestException("Fail to parse CSV file. Something is wrong with the contents of the file. "
                    + "Please check and try again.", e);
        }
    }
}