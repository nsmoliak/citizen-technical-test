package is.citizen.technicaltest.utils;

import is.citizen.technicaltest.dto.FilterDto;
import is.citizen.technicaltest.dto.SortDto;
import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.model.PersonModel;
import is.citizen.technicaltest.model.SortType;
import org.apache.commons.csv.CSVRecord;

import java.text.MessageFormat;

import static is.citizen.technicaltest.utils.CommonConstants.*;
import static is.citizen.technicaltest.utils.CommonConstants.DATE_OF_BIRTH_FIELD;

public class BuilderUtils {
    public static FilterDto buildFilterDtoFromString(String commaSeparatedString) {
        String[] params = commaSeparatedString.split(",");
        if (params.length != 2) {
            throw new BadRequestException("An invalid filter parameter was specified. "
                    + "Please enter the parameter in the format: filter=filter_parameter,filter_value");
        }
        return FilterDto.builder()
                .parameter(params[0])
                .value(params[1])
                .build();
    }

    public static SortDto buildSortDtoFromString(String commaSeparatedString) {
        String[] params = commaSeparatedString.split(",");
        if (params.length != 2) {
            throw new BadRequestException("An invalid sort parameter was specified. "
                    + "Please enter the parameter in the format: sort=sort_parameter,sort_value");
        }
        if (!SortType.contains(params[1])) {
            throw new BadRequestException(MessageFormat.format("The sort value parameter {0} is invalid. "
                    + "The value can only be of types: {1}.", params[1], SortType.getValuesString()));
        }

        return SortDto.builder()
                .parameter(params[0])
                .type(SortType.valueOf(params[1]))
                .build();
    }

    public static PersonModel buildPersonFromCsvRecord(CSVRecord csv) {
        return PersonModel.builder()
                .firstname(csv.get(FIRSTNAME_FIELD))
                .surname(csv.get(SURNAME_FIELD))
                .firstAddress(csv.get(FIRST_ADDRESS_FIELD))
                .secondAddress(csv.get(SECOND_ADDRESS_FIELD))
                .city(csv.get(CITY_FIELD))
                .state(csv.get(STATE_FIELD))
                .postcode(csv.get(POSTCODE_FIELD))
                .countryCode(csv.get(COUNTRY_CODE_FIELD))
                .gender(csv.get(GENDER_FIELD))
                .dateOfBirth(csv.get(DATE_OF_BIRTH_FIELD))
                .build();
    }

    public static String formatAddress(String address) {
        String result = address;
        if (address.contains(RD_CONSTANT)) {
            result = address.replace(RD_CONSTANT, ROAD_CONSTANT);
        } else if (address.contains(AVE_CONSTANT)) {
            result = address.replace(AVE_CONSTANT, AVENUE_CONSTANT);
        } else if (address.contains(LN_CONSTANT)) {
            result = address.replace(LN_CONSTANT, LANE_CONSTANT);
        }
        return result;
    }
}