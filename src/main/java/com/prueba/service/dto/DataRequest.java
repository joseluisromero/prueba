package com.prueba.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String opportunityId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String source;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String section;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cellPhone;
}
