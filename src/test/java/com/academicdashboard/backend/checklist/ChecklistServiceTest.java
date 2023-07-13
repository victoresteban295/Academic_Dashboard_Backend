package com.academicdashboard.backend.checklist;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.academicdashboard.backend.account.Account;
import com.academicdashboard.backend.account.AccountRepository;
import com.academicdashboard.backend.exception.ApiRequestException;

@Testcontainers
@DataMongoTest
public class ChecklistServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private AccountRepository accountRepository;

    private ChecklistService checklistService;

    @BeforeEach
    public void setUp() {
        this.checklistService = new ChecklistService(
                checklistRepository, 
                mongoTemplate);
    }

    @AfterEach
    public void cleanup() {
        this.checklistRepository.deleteAll();
        mongoTemplate.dropCollection(Account.class);
        mongoTemplate.createCollection(Account.class);
    }

    @Test
    @DisplayName("Should Create a New Checklist Under Student")
    public void shouldCreateNewChecklist() {
        //Create New User
        var account = Account.builder()
            .userId("123973789abjdrfklwi75")
            .firstname("Victor")
            .lastname("Benitez")
            .checklists(new ArrayList<>())
            .grouplists(new ArrayList<>())
            .build();
        accountRepository.save(account);

        //When
        checklistService.createChecklist("123973789abjdrfklwi75", "listTitle");

        //Then
        Assertions.assertThat(checklistRepository.findAll().get(0).getTitle()).isEqualTo("listTitle");

        Assertions.assertThat(mongoTemplate.findAll(Account.class)
                .get(0).getChecklists().get(0).getTitle()).isEqualTo("listTitle");
    }

    @Test
    @DisplayName("Should Modify an Existing Checklist")
    public void modifyExistingChecklistTitle() {
        //Given
        this.checklistRepository.insert(new Checklist("id01", "oldTitle"));
        
        //When
        checklistService.modifyChecklist("id01", "newTitle");

        //Then
        Checklist returnedValue = this.checklistRepository.findChecklistByListId("id01").get();
        Assertions.assertThat(returnedValue.getTitle()).isEqualTo("newTitle");
    }

    @Test
    @DisplayName("Should Throw an ApiRequestException When Modifying Non-existent Checklist")
    public void throwExceptionModifyingNonexistentChecklist() {
        //Given
        this.checklistRepository.insert(new Checklist("id01", "title01"));
        this.checklistRepository.insert(new Checklist("id02", "title02"));
        this.checklistRepository.insert(new Checklist("id03", "title03"));

        //Then
        Assertions.assertThatThrownBy(() -> {
            checklistService.modifyChecklist("id04", "title04");
        }).isInstanceOf(ApiRequestException.class)
            .hasMessage("Checklist You Wanted to Modify Doesn't Exist");
    } 

    @Test
    @DisplayName("Should Delete Checklist with its Checkpoints")
    public void shouldDeleteChecklistWithCheckpoints() {
        Checklist checklist = new Checklist("id01", "title01");
        Checkpoint point01 = mongoTemplate.save(new Checkpoint("12345", "content01", false, false));
        Checkpoint point02 = mongoTemplate.save(new Checkpoint("09876", "content02", false, false));
        List<Checkpoint> points = new ArrayList<>();
        points.add(point01);
        points.add(point02);
        checklist.setCheckpoints(points);

        //Given
        this.checklistRepository.insert(checklist);

        //When
        checklistService.deleteChecklist("id01");

        //Then
        Assertions.assertThat(mongoTemplate.findAll(Checkpoint.class).isEmpty()).isTrue();
        Assertions.assertThat(mongoTemplate.findAll(Checklist.class).isEmpty()).isTrue();
    }

    //Test Deleting Checklist with no checkpoints
    @Test
    @DisplayName("Should Delete Checklist")
    public void shouldDeleteChecklist() {
        Checklist checklist = new Checklist("id01", "title01");

        //Given
        this.checklistRepository.insert(checklist);

        //When
        checklistService.deleteChecklist("id01");

        //Then
        Assertions.assertThat(mongoTemplate.findAll(Checklist.class).isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should Throw an ApiRequestException When Modifying Non-existent Checklist")
    public void throwExceptionDeletingNonexistentChecklist() {
        //Given
        this.checklistRepository.insert(new Checklist("id01", "title01"));
        this.checklistRepository.insert(new Checklist("id02", "title02"));
         
        //Then
        Assertions.assertThatThrownBy(() -> {
            checklistService.deleteChecklist("id03");
        }).isInstanceOf(ApiRequestException.class)
            .hasMessage("Checklist You Wanted to Delete Doesn't Exist");

    }
}
