package am.itspace.authorbookee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {

    private int id;
    private String title;
    private double price;
    private int qty;
    private Author author;

}
