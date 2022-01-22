package models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public class Product {


    @Setter(AccessLevel.PRIVATE) @Getter int Id;
    @Setter(AccessLevel.PRIVATE) @Getter public String Name;
    @Setter(AccessLevel.PRIVATE) @Getter public BigDecimal Price;
    @Setter(AccessLevel.PRIVATE) @Getter public String Description;

    public Product()
    {

    }

    public Product(int Id, String Name, BigDecimal Price, String Description)
    {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Description='" + Description + '\'' +
                '}';
    }
}
