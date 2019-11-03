import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Menu")
public class Dish {
    @Id
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer weight;
    @Column()
    private boolean isDiscount = false;

    public Dish() {
    }

    public Dish(String name, Double price, Integer weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(Double newPrice) {
        if (newPrice < price) {
            price = newPrice;
            isDiscount = true;
        } else {
            System.out.println("Такая себе скидка!Скидка не установлена");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return isDiscount == dish.isDiscount &&
                Objects.equals(name, dish.name) &&
                Objects.equals(price, dish.price) &&
                Objects.equals(weight, dish.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, weight, isDiscount);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", isDiscount=" + isDiscount +
                '}';
    }
}
