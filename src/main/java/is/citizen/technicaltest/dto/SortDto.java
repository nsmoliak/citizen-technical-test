package is.citizen.technicaltest.dto;

import is.citizen.technicaltest.model.SortType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SortDto {
    private SortType type;
    private String parameter;
}