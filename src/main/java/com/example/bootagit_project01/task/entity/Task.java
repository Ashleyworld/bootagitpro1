package com.example.bootagit_project01.task.entity;


import com.example.bootagit_project01.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskid;
    private Long taskid;
    private String title;
    private String description;
    private String status;
//    private String user_id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User userid;
    // getters and setters
}