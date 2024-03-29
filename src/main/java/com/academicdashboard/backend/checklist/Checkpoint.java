package com.academicdashboard.backend.checklist;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Checkpoint {
    
    String index; //Index with Checklist's Array
    String content;
    List<Checkpoint> subpoints;
    List<Checkpoint> completedSubpoints;

}
