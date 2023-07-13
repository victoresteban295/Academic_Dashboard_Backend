package com.academicdashboard.backend.account;

import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.academicdashboard.backend.checklist.Checklist;
import com.academicdashboard.backend.checklist.Grouplist;
import com.academicdashboard.backend.course.Course;
import com.academicdashboard.backend.reminder.ReminderList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private ObjectId id; //MongoDB ObjectId 

    @Indexed(unique = true)
    private String userId; //Public Id (JNanoId)

    //User Information
    private String firstname;
    private String lastname;

    //Account Information
    private String profileType; //STUDENT || PROFESSOR

    @Indexed(unique = true)
    private String email;

    private String phone;
    private String username;
    private String password;
    private Role role;

    //Academic Institution Information
    private String schoolName;
    private String schoolId;

    @DocumentReference
    private AccountType accountType;

    //Data Relationships
    @DocumentReference
    private List<Course> courses;

    @DocumentReference
    private List<Calendar> calendars;

    @DocumentReference
    private List<Grouplist> grouplists;

    @DocumentReference
    private List<Checklist> checklists;

    @DocumentReference
    private List<ReminderList> reminderLists;
}
