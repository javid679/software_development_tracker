package com.project.pantry.Repository;
// THis Survey Repository referencing to Survey Table in DB
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pantry.model.Survey;
@Repository
public interface SurveyRepository  extends JpaRepository<Survey, Long> {

}

