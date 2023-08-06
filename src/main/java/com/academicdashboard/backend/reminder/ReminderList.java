package com.academicdashboard.backend.reminder;

import java.util.List;

import org.bson.types.ObjectId; //MongoDB ObjectId (AutoGenerated)
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "reminder_list")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReminderList {

    @Id
    private ObjectId id; //MongoDB ObjectId
    
    //Reminders List's Details
    private String title;
    private String description;

    //Data Relationship: Individual Reminders under this List
    @DocumentReference
    private List<Reminder> reminders; //Individual Reminders

    //Constructor: User first creates a Reminder List (Called by PUT Method) 
    public ReminderList(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
