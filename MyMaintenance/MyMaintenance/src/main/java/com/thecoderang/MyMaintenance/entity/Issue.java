package com.thecoderang.MyMaintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ISSUES")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issue_id;
    @Column(name = "CREATOR_USER_ID")
    private int creator_user_id;
    @Column(name = "ASSIGNED_USER_ID")
    private int assigned_user_id;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED_AT")
    private Calendar created_at;
    @Column(name = "UPDATED_AT")
    private Calendar updated_at;
}
