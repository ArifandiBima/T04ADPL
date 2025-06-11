package AdminView;

public class MainProduct extends Product {
    public int id;
    public String nama;
    public int price;
    public int qty;
    public boolean[] modifiable;

    public MainProduct(int id, String nama, int price, int qty, boolean[] modifiable) {
        this.id = id;
        this.nama = nama;
        this.price = price;
        this.qty = qty;
        this.modifiable = modifiable;
    }

    public MainProduct(int id, String nama, int price) {
        this(id, nama, price, 0, new boolean[1]);
    }

    public MainProduct(int id, String nama, int price, boolean[] modifiable) {
        this(id, nama, price, 0, modifiable);
    }

    @Override
    public int countPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.nama;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MainProduct)) return false;
        return this.id == ((MainProduct) other).id;
    }
}
