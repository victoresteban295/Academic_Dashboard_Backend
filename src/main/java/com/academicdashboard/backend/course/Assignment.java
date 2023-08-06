package com.academicdashboard.backend.course;

import org.bson.types.ObjectId; //MongoDB ObjectId (AutoGenerated)
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "assignment")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    @Id
    private ObjectId id; //MongoDB ObjectId
    
    //Assignment Detail
    private String title;
    private String description;
    private boolean isUpcoming;
    private String dueDate;
}
