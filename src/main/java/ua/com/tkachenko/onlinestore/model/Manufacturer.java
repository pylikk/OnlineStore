package ua.com.tkachenko.onlinestore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    List<Goods> goods;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public Manufacturer() {
    }

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manufacturer that = (Manufacturer) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return goods != null ? goods.equals(that.goods) : that.goods == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }
}
