package pl.camp.it.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private int id;
    private String name;
    private int amount;
    private long barcode;
    private ProductCategory category;


}
