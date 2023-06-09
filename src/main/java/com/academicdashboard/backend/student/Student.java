package com.academicdashboard.backend.student;

import org.springframework.data.mongodb.core.mapping.Document;

import com.academicdashboard.backend.user.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "student")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Profile {

    //Student Specific Information
    private String gradeLvl;
    private String major;
    private String minor;
    private String concentration;

}
