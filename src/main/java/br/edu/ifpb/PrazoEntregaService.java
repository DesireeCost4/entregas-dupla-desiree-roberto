package br.edu.ifpb;

public class PrazoEntregaService {

    public int calcularPrazo(int distanciaKm) {
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("Distancia invalida");
        }

        if (distanciaKm <= 50) {
            return 1;
        }

        if (distanciaKm <= 200) {
            return 3;
        }

        return 5;
    }

    public int calcularPrazo(int distanciaKm, boolean expressa) {
        int prazoNormal = calcularPrazo(distanciaKm);

        if (!expressa) {
            return prazoNormal;
        }
        return Math.max(1, prazoNormal - 1);
    }

    public int calcularTaxa(int distanciaKm) {
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("Distância deve ser maior que zero");
        }
        if (distanciaKm <= 50) {
            return 10;
        }
        if (distanciaKm <= 200) {
            return 20;
        }
        return 40;
    }

    public int calcularTaxa(int distanciaKm, boolean clientePrime) {
        int taxaNormal = calcularTaxa(distanciaKm);

        if (clientePrime) {
            return taxaNormal / 2;
        }

        return taxaNormal;
    }


}
