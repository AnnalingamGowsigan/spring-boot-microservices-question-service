package com.spring.springbootmicroservicesquestionservice.controller;

import com.spring.springbootmicroservicesquestionservice.model.Question;
import com.spring.springbootmicroservicesquestionservice.model.QuestionWrapper;
import com.spring.springbootmicroservicesquestionservice.model.QuizAnswer;
import com.spring.springbootmicroservicesquestionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }


    @GetMapping("generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numberOfQuestions) {
        return questionService.generateQuestions(category, numberOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizAnswer> quizAnswers) {
        return questionService.getScore(quizAnswers);
    }

    @GetMapping("initQuestions")
    public String initQuestions(){
        // Add Java questions
        questionService.initQuestions("What is Java?", "A programming language", "A type of coffee", "An island in Indonesia", "None of the above",
                "A programming language", "Easy", "Java");

        questionService.initQuestions("What is a class in Java?", "A blueprint for objects", "A group of students", "A type of function", "A data structure",
                "A blueprint for objects", "Intermediate", "Java");

        questionService.initQuestions("What is a constructor in Java?", "A method with the same name as the class", "A person who builds houses", "A built-in function", "A control statement",
                "A method with the same name as the class", "Intermediate", "Java");

        questionService.initQuestions("What is inheritance in Java?", "A way to reuse code", "A way to create new classes", "A type of function", "A data structure",
                "A way to reuse code", "Intermediate", "Java");

        questionService.initQuestions("What is a static method in Java?", "A method that can be called without instance", "A method that can only be called once", "A method that is not visible", "A method that can be overridden",
                "A method that can be called without instance", "Intermediate", "Java");

        // Add Python questions
        questionService.initQuestions("What is Python?", "A type of snake", "A programming language", "A type of food", "A tropical fruit",
                "A programming language", "Easy", "Python");

        questionService.initQuestions("What is a list in Python?", "A group of people", "A collection of elements", "A type of function", "A data structure",
                "A collection of elements", "Intermediate", "Python");

        questionService.initQuestions("What is a tuple in Python?", "A sequence of values", "A type of snake", "A type of food", "A tropical fruit",
                "A sequence of values", "Intermediate", "Python");

        questionService.initQuestions("What is a dictionary in Python?", "A type of snake", "A type of food", "A collection of key-value pairs", "A tropical fruit",
                "A collection of key-value pairs", "Intermediate", "Python");

        questionService.initQuestions("What is a module in Python?", "A group of functions", "A type of snake", "A way to organize code", "A tropical fruit",
                "A way to organize code", "Intermediate", "Python");

        // Add JavaScript questions
        questionService.initQuestions("What is JavaScript?", "A type of coffee", "A programming language", "A type of function", "A data structure",
                "A programming language", "Easy", "JavaScript");

        questionService.initQuestions("What is a variable in JavaScript?", "A way to store information", "A type of function", "A type of data", "A control statement",
                "A way to store information", "Intermediate", "JavaScript");

        questionService.initQuestions("What is an array in JavaScript?", "A collection of elements", "A type of snake", "A type of food", "A tropical fruit",
                "A collection of elements", "Intermediate", "JavaScript");

        questionService.initQuestions("What is a function in JavaScript?", "A type of snake", "A type of food", "A block of code", "A tropical fruit",
                "A block of code", "Intermediate", "JavaScript");

        questionService.initQuestions("What is an object in JavaScript?", "A way to represent data", "A type of snake", "A type of food", "A tropical fruit",
                "A way to represent data", "Intermediate", "JavaScript");

        // Add questions for other programming languages similarly
        return "Questions added successfully";
    }

}
