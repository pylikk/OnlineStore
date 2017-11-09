package ua.com.tkachenko.onlinestore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "order_date")
    private Date date;

    @Column(name = "delivery_date")
    private Date delivery_date;

    @Column(name = "order_info")
    private String order_info;

    @Column(name = "order_status")
    private String order_status;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public Order() {
        date = new Date();
    }

    public Order(String phone, String address, String firstname, String lastname, Date delivery_date, String order_info, String order_status, Goods goods) {
        this.phone = phone;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.delivery_date = delivery_date;
        this.order_info = order_info;
        this.order_status = order_status;
        this.goods = goods;
        this.date = new Date();
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrder_info() {
        return order_info;
    }

    public void setOrder_info(String order_info) {
        this.order_info = order_info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (phone != null ? !phone.equals(order.phone) : order.phone != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (firstname != null ? !firstname.equals(order.firstname) : order.firstname != null) return false;
        if (lastname != null ? !lastname.equals(order.lastname) : order.lastname != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (delivery_date != null ? !delivery_date.equals(order.delivery_date) : order.delivery_date != null)
            return false;
        if (order_info != null ? !order_info.equals(order.order_info) : order.order_info != null) return false;
        if (order_status != null ? !order_status.equals(order.order_status) : order.order_status != null) return false;
        return goods != null ? goods.equals(order.goods) : order.goods == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (delivery_date != null ? delivery_date.hashCode() : 0);
        result = 31 * result + (order_info != null ? order_info.hashCode() : 0);
        result = 31 * result + (order_status != null ? order_status.hashCode() : 0);
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }
}
