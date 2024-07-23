package com.example.bootagit_project01.task.entity;


import com.example.bootagit_project01.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
//    private String user_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    // getters and setters
}