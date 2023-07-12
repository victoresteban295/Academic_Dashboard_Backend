package com.academicdashboard.backend.checklist;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers //Register Testcontainer
@DataMongoTest
public class ChecklistRepositoryTest {

    @Autowired
    private ChecklistRepository checklistRepository;

    @AfterEach
    public void cleanup() {
        this.checklistRepository.deleteAll();
    }

    @Test
    @DisplayName("Should Insert Checklist to Repository")
    public void canInsertChecklistToRepository() {
        Checklist expectedValue = new Checklist("id01", "title01");
        this.checklistRepository.insert(expectedValue);
        List<Checklist> checklists = checklistRepository.findAll();
        Assertions.assertThat(checklists.contains(expectedValue)).isTrue();  
    }

    @Test
    @DisplayName("Should Find Checklist Using ListId")
    public void canFindChecklistByListId() {
        Checklist expectedValue = new Checklist("id01", "title01");
        this.checklistRepository.insert(expectedValue);
        Checklist returnedValue = checklistRepository.findChecklistByListId("id01").get(); 
        Assertions.assertThat(returnedValue.getTitle()).isEqualTo(expectedValue.getTitle());
    }
}
