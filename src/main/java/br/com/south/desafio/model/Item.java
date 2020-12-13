package br.com.south.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Item {

    private Integer id;

    private Integer quantity;

    private double itemPrice;
}
