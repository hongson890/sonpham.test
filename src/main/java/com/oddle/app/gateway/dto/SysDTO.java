package com.oddle.app.gateway.dto;

import lombok.Data;

@Data
public class SysDTO {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
}
