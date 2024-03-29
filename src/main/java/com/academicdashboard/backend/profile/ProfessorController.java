package com.academicdashboard.backend.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/v1.0/profiles/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    //Get Professor's Profile Information || Returns Professor
    @GetMapping("/{username}")
    public ResponseEntity<Professor> getProfessorProfile(
            @PathVariable String username) {

        return new ResponseEntity<Professor>(
                professorService.getProfessorProfile(username, "PROFESSOR"),
                HttpStatus.OK);
    }

}
