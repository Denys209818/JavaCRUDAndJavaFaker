package models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class New {
    @Setter(AccessLevel.PRIVATE) @Getter public int Id;
    @Setter(AccessLevel.PRIVATE) @Getter public String Title;
    @Setter(AccessLevel.PRIVATE) @Getter public String Text;
    @Setter(AccessLevel.PRIVATE) @Getter public Date DateCreated;
    @Setter(AccessLevel.PRIVATE) @Getter public int CategoryId;

    public New() {}

    public New(int Id, String Title, String Text, Date DateCreated, int CategoryId)
    {
        this.Id = Id;
        this.Title = Title;
        this.Text = Text;
        this.DateCreated = DateCreated;
        this.CategoryId = CategoryId;
    }

    @Override
    public String toString() {
        return "New{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", Text='" + Text + '\'' +
                ", DateCreated=" + DateCreated +
                ", CategoryId=" + CategoryId +
                '}';
    }
}
