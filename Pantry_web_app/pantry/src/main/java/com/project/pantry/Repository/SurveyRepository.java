package com.project.pantry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pantry.model.Survey;
@Repository
public interface SurveyRepository  extends JpaRepository<Survey, Long> {

}

