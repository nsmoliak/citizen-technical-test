package is.citizen.technicaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
    private String firstname;
    private String surname;
    @JsonProperty("address1")
    private String firstAddress;
    @JsonProperty("address2")
    private String secondAddress;
    private String city;
    private String state;
    private String postcode;
    @JsonProperty("countrycode")
    private String countryCode;
    private String gender;
    @JsonProperty("dateofbirth")
    private String dateOfBirth;
}