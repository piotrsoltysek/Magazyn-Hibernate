package pl.camp.it.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductCategory {
    @Id
    int id;
    String name;
    boolean deleted;

}