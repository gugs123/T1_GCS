public class Item {
    private String descricaoItem;
    private int valorUnitario;
    private int quantidade;
    private int totalDoItem;

    public Item (String descricaoItem, int valorUnitario, int quantidade) {
        this.descricaoItem = descricaoItem;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.totalDoItem = this.quantidade * this.valorUnitario;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTotalDoItem() {
        return totalDoItem;
    }
}
