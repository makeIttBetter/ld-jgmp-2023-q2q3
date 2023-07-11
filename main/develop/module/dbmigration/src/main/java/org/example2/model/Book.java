package org.example2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author")
  private Author author;

}
