package br.com.south.desafio;

import br.com.south.desafio.model.Cliente;
import br.com.south.desafio.model.Item;
import br.com.south.desafio.model.Venda;
import br.com.south.desafio.model.Vendedor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
public class SouthTesteApplication {

    public static void main(String[] args) {


        SpringApplication.run(SouthTesteApplication.class, args);
    }



}
