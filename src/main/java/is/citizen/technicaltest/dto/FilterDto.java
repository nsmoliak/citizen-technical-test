package is.citizen.technicaltest.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class FilterDto {
    private String parameter;
    private String value;
}