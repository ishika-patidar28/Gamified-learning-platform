package com.rurallearning.platform.controller;

import com.rurallearning.platform.entity.Quiz;
import com.rurallearning.platform.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/subject/{subjectId}")
    public List<Quiz> getQuizzesBySubject(
            @PathVariable Long subjectId) {

        return quizService.getQuizzesBySubject(subjectId);
    }

    @DeleteMapping("/{id}")
    public String deleteQuiz(@PathVariable Long id) {

        quizService.deleteQuiz(id);

        return "Quiz deleted successfully";
    }
}
