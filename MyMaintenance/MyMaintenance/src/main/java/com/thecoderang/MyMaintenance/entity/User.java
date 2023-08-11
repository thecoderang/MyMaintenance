package com.thecoderang.MyMaintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name="USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(name = "USER_NAME")
    private String user_name;
    @Column(name = "USER_ROLE")
    private String user_role;
    @Column(name = "CREATED_AT")
    private Calendar created_at;
}
