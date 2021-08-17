package br.ufc.ong.model;

public enum Status {

    PENDENTE("Ocorrencia pendente"),
    CONFIRMADO("Ocorrencia confirmada"),
    CANCELADO("Ocorrencia cancelada"),
    ADOTADO("Animal da ocorrencia adotado"),
    ADOCAO("Animal da Ocorrencia apto para a adoção");

    private String descricao;

    Status(String descricao){
        this.descricao = descricao;
    }

    public String getNome() {
        return descricao;
    }

}
