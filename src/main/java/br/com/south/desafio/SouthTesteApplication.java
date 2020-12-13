package br.com.south.desafio;

import br.com.south.desafio.model.Cliente;
import br.com.south.desafio.model.Item;
import br.com.south.desafio.model.Venda;
import br.com.south.desafio.model.Vendedor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SouthTesteApplication {

    public static void main(String[] args) {
        try (Stream<String> data = Files.lines(Path.of("data\\in\\relatorio.dat"))) {
            List<Cliente> clienteList = new ArrayList<>();
            List<Vendedor> vendedorList = new ArrayList<>();
            List<Venda> vendaList = new ArrayList<>();

            data.forEach(line -> {
                String[] arrayDta = line.split("ç");

                switch (arrayDta[0]) {
                    case "001":
                        vendedorList.add(
                                Vendedor.builder()
                                        .id(arrayDta[0])
                                        .cpf(arrayDta[1])
                                        .name(arrayDta[2])
                                        .salary(Double.parseDouble(arrayDta[3]))
                                        .build()
                        );
                        break;
                    case "002":
                        clienteList.add(
                                Cliente.builder()
                                        .id(arrayDta[0])
                                        .cnpj(arrayDta[1])
                                        .name(arrayDta[2])
                                        .businessArea(arrayDta[3])
                                        .build()
                        );
                        break;
                    case "003":
                        String arraySales = arrayDta[2].substring(1, (arrayDta[2].length() - 1));
                        String[] sales = arraySales.split(",");
                        List<Item> itemList = new ArrayList<>();

                        Arrays.stream(sales).forEach(sale -> {
                            String[] itemInfo = sale.split("-");

                            itemList.add(Item.builder()
                                    .id(Integer.parseInt(itemInfo[0]))
                                    .quantity(Integer.parseInt(itemInfo[1]))
                                    .itemPrice(Double.parseDouble(itemInfo[2]))
                                    .build());
                        });

                        double total = itemList.stream().reduce(0.0, (subtotal, item) ->
                                subtotal + (item.getQuantity() * item.getItemPrice()), Double::sum);

                        vendaList.add(Venda.builder()
                                .id(arrayDta[0])
                                .idSale(arrayDta[1])
                                .itens(itemList)
                                .total(total)
                                .salesManName(arrayDta[3])
                                .build());
                        break;
                }
            });

            Optional<Venda> vendaMaisCara = vendaList.stream().max(Comparator.comparing(Venda::getTotal));

            Map<String, Double> vendedoresGroup = vendaList.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Venda::getSalesManName,
                                    Collectors.summingDouble(Venda::getTotal)
                            ));
            Optional<Map.Entry<String, Double>> piorVendedor = vendedoresGroup.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));

            writterFile(clienteList.size(), vendedorList.size(), vendaMaisCara.get().getIdSale(), piorVendedor.get().getKey());

        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(SouthTesteApplication.class, args);
    }

    public static void writterFile(int qtdeClientes, int qtdeVendedores, String idDaVendamaisCara, String piorVendedor) throws IOException {

        FileWriter arq = new FileWriter("data\\out\\relatório.done.dat");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("Quantidade de clientes: " + qtdeClientes);
        gravarArq.println("Quantidade de vendedores: " + qtdeVendedores);
        gravarArq.println("Id da venda mais cara : " + idDaVendamaisCara);
        gravarArq.println("Pior vendedor : " + piorVendedor);

        arq.close();

        System.out.println("Aquivo salvo com sucesso!!");
    }

}
