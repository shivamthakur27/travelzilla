package com.travelzilla.dto;

import lombok.Data;

@Data
public class RtnDto
{
    private int statusCode;
    private String message;
    private Object data;

}
