package com.academicdashboard.backend.profile;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder //Experimental
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    private ObjectId id; //MongoDB ObjectId
    private String username;

    //Personal Information
    private String firstname;
    private String middlename;
    private String lastname;
    private String birthMonth; 
    private int birthDay;
    private int birthYear;
}
