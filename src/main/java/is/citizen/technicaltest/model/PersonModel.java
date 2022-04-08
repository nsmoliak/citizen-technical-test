package is.citizen.technicaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
    @Schema(description = "Firstname", example = "Fred")
    private String firstname;
    @Schema(description = "Surname", example = "Smith")
    private String surname;
    @Schema(description = "First address", example = "Customs House")
    @JsonProperty("address1")
    private String firstAddress;
    @Schema(description = "Second address", example = "1 Long Street")
    @JsonProperty("address2")
    private String secondAddress;
    @Schema(description = "City", example = "Glasgow")
    private String city;
    @Schema(description = "State", example = "Glasgow")
    private String state;
    @Schema(description = "PostCode", example = "G101AA")
    private String postcode;
    @Schema(description = "Country code", example = "UK")
    @JsonProperty("countrycode")
    private String countryCode;
    @Schema(description = "Gender", example = "M")
    private String gender;
    @Schema(description = "Date Of Birth", example = "1-1-1970")
    @JsonProperty("dateofbirth")
    private String dateOfBirth;
}