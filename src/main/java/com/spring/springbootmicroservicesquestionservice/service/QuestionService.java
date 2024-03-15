package com.spring.springbootmicroservicesquestionservice.service;

import com.spring.springbootmicroservicesquestionservice.dao.QuestionDao;
import com.spring.springbootmicroservicesquestionservice.model.Question;
import com.spring.springbootmicroservicesquestionservice.model.QuestionWrapper;
import com.spring.springbootmicroservicesquestionservice.model.QuizAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getQuestions(){
        try
        {
            List<Question> questions = questionDao.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            List<Question> questions = questionDao.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question)
    {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, int numberOfQuestions) {
        try {
            List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);
            return new ResponseEntity<>(questionIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void initQuestions(String questionTitle, String option1, String option2, String option3, String option4,
                            String rightAnswer, String difficultyLevel, String category) {
        Question question = new Question();
        question.setQuestionTitle(questionTitle);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setRightAnswer(rightAnswer);
        question.setDifficultyLevel(difficultyLevel);
        question.setCategory(category);

        questionDao.save(question);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        try {
            List<QuestionWrapper> questionWrappers =new ArrayList<>();
            List<Question> questions = new ArrayList<>();
            for(Integer questionId : questionIds) {
                Question question = questionDao.findById(questionId).get();
                questions.add(question);
            }
            for(Question question : questions) {
                questionWrappers.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
            }

            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getScore(List<QuizAnswer> quizAnswers) {
        try {
            int score = 0;
            for(QuizAnswer quizAnswer : quizAnswers) {
                Question question = questionDao.findById(quizAnswer.getId()).get();
                if(question.getRightAnswer().equals(quizAnswer.getAnswer())) {
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
