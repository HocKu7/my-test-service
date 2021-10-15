package ru.digitalleague.core.model;

import lombok.Data;

/**
 * Модель ответа клиенту.
 */
@Data
public class OrderResponse {

    private String lastName;

    private String firstName;

    private String carModel;
}
