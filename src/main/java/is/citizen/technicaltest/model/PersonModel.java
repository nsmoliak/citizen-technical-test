package is.citizen.technicaltest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class PersonModel {
    @JsonIgnore
    private UUID id;
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