package br.com.south.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Vendedor {

    private String id;

    private String cpf;

    private String name;

    private Double salary;
}
