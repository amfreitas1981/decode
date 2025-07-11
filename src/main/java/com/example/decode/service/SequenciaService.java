package com.example.decode.service;

import com.example.decode.model.SequenciaResultado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class SequenciaService {

    private final List<SequenciaResultado> historico = new ArrayList<>();

    public SequenciaResultado resolverExpandido(List<String> entrada) {
        List<String> resultado;

        if (entrada.stream().allMatch(s -> s.matches("-?\\d+"))) {
            List<Integer> dadosNumericos = entrada.stream().map(Integer::parseInt).toList();
            resultado = resolverNumerica(dadosNumericos);
        } else if (isSequenciaLetra(entrada)) {
            resultado = resolverSequenciaLetra(entrada);
        } else {
            resultado = List.of("?", "?");
        }

        List<String> dadosCompletos = new ArrayList<>(entrada);
        dadosCompletos.addAll(resultado);

        SequenciaResultado item = new SequenciaResultado(dadosCompletos);
        historico.add(item);
        return item;
    }

    public List<SequenciaResultado> getHistorico() {
        return historico;
    }

    // Progressão aritmética simples
    private boolean isAritmetica(List<Integer> entrada) {
        int passo = entrada.get(1) - entrada.get(0);
        return IntStream.range(0, entrada.size() - 1)
                .allMatch(i -> entrada.get(i + 1) - entrada.get(i) == passo);
    }

    private List<String> resolverAritmetica(List<Integer> entrada) {
        int passo = entrada.get(1) - entrada.get(0);
        int ultimo = entrada.get(entrada.size() - 1);
        return List.of(
                String.valueOf(ultimo + passo),
                String.valueOf(ultimo + 2 * passo));
    }

    // Alternância fixa (ex: 9,4,10,4,11,4,…)
    private boolean isAlternanciaFixada(List<Integer> entrada) {
        if (entrada.size() < 4) return false;
        int fixo = entrada.get(1);
        for (int i = 1; i < entrada.size(); i += 2) {
            if (!Objects.equals(entrada.get(i), fixo)) return false;
        }
        return true;
    }

    private List<String> resolverAlternanciaFixada(List<Integer> entrada) {
        int ultimoProgressivo = entrada.get(entrada.size() - 2);
        int proximo = ultimoProgressivo + 1;
        int fixo = entrada.get(1);
        return List.of(String.valueOf(proximo), String.valueOf(fixo));
    }

    // Multiplicações sequenciais com fator crescente (ex: 7, 14, 42, 168,…)
    private boolean isMultiplicacaoSequencial(List<Integer> entrada) {
        if (entrada.size() < 3) return false;
        for (int i = 2; i < entrada.size(); i++) {
            int anterior = entrada.get(i - 1) / entrada.get(i - 2);
            int atual = entrada.get(i) / entrada.get(i - 1);
            if (atual != anterior + 1) return false;
        }
        return true;
    }

    private List<String> resolverMultiplicacaoSequencial(List<Integer> entrada) {
        int ultimo = entrada.get(entrada.size() - 1);
        int fator = entrada.get(entrada.size() - 1) / entrada.get(entrada.size() - 2) + 1;
        int p1 = ultimo * fator;
        int p2 = p1 * (fator + 1);
        return List.of(String.valueOf(p1), String.valueOf(p2));
    }

    // Sequência de letras com passo fixo (ex: A, C, E,…)
    private boolean isSequenciaLetra(List<String> entrada) {
        return entrada.stream().allMatch(e -> e.length() == 1 && Character.isLetter(e.charAt(0)));
    }

    private List<String> resolverSequenciaLetra(List<String> entrada) {
        char penultimo = entrada.get(entrada.size() - 2).charAt(0);
        char ultimo = entrada.get(entrada.size() - 1).charAt(0);
        int passo = ultimo - penultimo;
        char p1 = (char) (ultimo + passo);
        char p2 = (char) (p1 + passo);
        return List.of(String.valueOf(p1), String.valueOf(p2));
    }

    // Organiza lógica de priorização
    private List<String> resolverNumerica(List<Integer> entrada) {
        if (isAritmetica(entrada)) return resolverAritmetica(entrada);
        if (isAlternanciaFixada(entrada)) return resolverAlternanciaFixada(entrada);
        if (isMultiplicacaoSequencial(entrada)) return resolverMultiplicacaoSequencial(entrada);
        return List.of("?", "?");
    }
}
