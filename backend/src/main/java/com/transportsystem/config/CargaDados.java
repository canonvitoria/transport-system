package com.transportsystem.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.transportsystem.model.Cidade;
import com.transportsystem.model.Distancia;
import com.transportsystem.repository.CidadeRepository;
import com.transportsystem.repository.DistanciaRepository;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CargaDados implements CommandLineRunner {

    private final CidadeRepository cidadeRepository;
    private final DistanciaRepository distanciaRepository;

    public CargaDados(CidadeRepository cidadeRepository, DistanciaRepository distanciaRepository) {
        this.cidadeRepository = cidadeRepository;
        this.distanciaRepository = distanciaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem dados para não duplicar ao reiniciar
        if (cidadeRepository.count() > 0) {
            System.out.println("Banco de dados já populado. Pulando carga inicial.");
            return;
        }

        System.out.println("Iniciando carga de dados do CSV...");

        // Lê o arquivo da pasta resources
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("DistanciasCidadesCSV.csv").getInputStream()))) {

            // 1. Lê a primeira linha (Cabeçalho) para criar as Cidades
            String headerLine = reader.readLine();
            if (headerLine == null) return;

            String[] nomesCidades = headerLine.split(";");
            List<Cidade> cidadesSalvas = new ArrayList<>();

            // Começa do índice 0 ou 1 dependendo se o CSV tem uma coluna vazia no canto
            for (String nome : nomesCidades) {
                if (!nome.trim().isEmpty()) {
                    Cidade cidade = new Cidade();
                    cidade.setNome(nome.trim());
                    // Salva e guarda na lista para usar o ID depois
                    cidadesSalvas.add(cidadeRepository.save(cidade));
                }
            }

            // 2. Lê as linhas seguintes (Matriz de Distâncias)
            String linha;
            int linhaIndex = 0;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                
                // O primeiro item da linha é a cidade de origem
                String nomeOrigem = dados[0];
                Cidade cidadeOrigem = buscarCidadeNaLista(cidadesSalvas, nomeOrigem);

                if (cidadeOrigem == null) {
                    System.err.println("Cidade de origem não encontrada: " + nomeOrigem);
                    continue;
                }

                // Os próximos itens são as distâncias para as outras cidades
                for (int i = 1; i < dados.length; i++) {
                    if (i - 1 < cidadesSalvas.size()) {
                        Cidade cidadeDestino = cidadesSalvas.get(i - 1);
                        
                        try {
                            int km = Integer.parseInt(dados[i].trim());

                            if (km > 0) {
                                Distancia dist = new Distancia();
                                dist.setOrigem(cidadeOrigem);
                                dist.setDestino(cidadeDestino);
                                dist.setKm(km);
                                distanciaRepository.save(dist);
                            }
                        } catch (NumberFormatException e) {
                            // Ignora células vazias ou inválidas
                        }
                    }
                }
                linhaIndex++;
            }
            System.out.println("Carga de dados finalizada com sucesso!");
        }
    }

    // Método auxiliar simples para achar a cidade na lista em memória
    private Cidade buscarCidadeNaLista(List<Cidade> cidades, String nome) {
        return cidades.stream()
                .filter(c -> ((String) c.getNome(nome)).equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }
}