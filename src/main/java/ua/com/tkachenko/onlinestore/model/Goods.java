package ua.com.tkachenko.onlinestore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Goods() {
    }

    public Goods(String name, Manufacturer manufacturer, String description, int price, int quantity, String image) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (id != goods.id) return false;
        if (price != goods.price) return false;
        if (quantity != goods.quantity) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (manufacturer != null ? !manufacturer.equals(goods.manufacturer) : goods.manufacturer != null) return false;
        if (description != null ? !description.equals(goods.description) : goods.description != null) return false;
        return image != null ? image.equals(goods.image) : goods.image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + quantity;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
