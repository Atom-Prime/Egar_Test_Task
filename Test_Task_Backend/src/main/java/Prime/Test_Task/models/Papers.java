package Prime.Test_Task.models;



import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table
@ToString(of={"id","paper","price"})
public class Papers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    private String paper;
    private String price;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(this.date);
        }
        catch (ParseException e) {
            return null;
        }
    }

    public void setDate(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.date = formatter.parse(date);
        }
        catch (ParseException e) {
            this.date = null;
        }
    }
}
