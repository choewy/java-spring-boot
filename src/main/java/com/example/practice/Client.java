package com.example.practice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  @Column(length = 200)
  private String email;

  @CreatedDate
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
  private List<Question> questions;

  @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
  private List<Answer> answers;
}
