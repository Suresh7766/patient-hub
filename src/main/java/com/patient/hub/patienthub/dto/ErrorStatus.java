package com.patient.hub.patienthub.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorStatus {

    private int statusCode;

    private List<String> errorMessages = new ArrayList<>(1);

}
