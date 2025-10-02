package domain;

public abstract class Item {
    protected String itemName;
    protected TipoItem tipoItem;

    public Item(String itemName, TipoItem tipoItem) {
        this.itemName = itemName;
        this.tipoItem = tipoItem;
    }


}
