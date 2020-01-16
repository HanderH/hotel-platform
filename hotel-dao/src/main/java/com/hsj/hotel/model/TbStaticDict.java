package com.hsj.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbStaticDict {

    private String codeType;
    private String codeValue;
    private String codeName;
    private String codeDesc;

}
