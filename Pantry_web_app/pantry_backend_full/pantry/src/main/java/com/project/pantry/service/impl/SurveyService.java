package com.project.pantry.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pantry.Repository.SurveyRepository;
import com.project.pantry.model.Survey;
@Service
public class SurveyService {
	@Autowired
	SurveyRepository surveyrepo;

	public void addSurvey(HashMap<String, String> surveyRequest) throws Exception {
		try
		{
		Survey survey = new Survey();
		survey.setAge(surveyRequest.get("age"));
		survey.setCollege(surveyRequest.get("college"));
		survey.setEnthinicity(surveyRequest.get("ethnicity"));
		survey.setExperience(surveyRequest.get("experience"));
		survey.setLocation(surveyRequest.get("location"));
		survey.setMemeberliving(surveyRequest.get("members"));
		survey.setEmail(surveyRequest.get("email"));
		surveyrepo.save(survey);
		
		
	}
		catch(Exception e) {
		throw new Exception (e.getMessage());
	}

}
}
