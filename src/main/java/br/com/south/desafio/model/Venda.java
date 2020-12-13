package br.com.south.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Venda {

    private String id;

    private String idSale;

    private List<Item> itens;

    private Double total;

    private String salesManName;
}
