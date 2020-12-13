package br.com.south.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Cliente {

    private String id;

    private String cnpj;

    private String name;

    private String businessArea;
}
