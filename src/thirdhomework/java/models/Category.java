package models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Category {

    @Setter(AccessLevel.PRIVATE) @Getter public int Id;
    @Setter(AccessLevel.PRIVATE) @Getter public String Title;
    @Setter(AccessLevel.PRIVATE) @Getter public String Description;

    public Category() {}
    public Category(int Id, String Title, String Description)
    {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
