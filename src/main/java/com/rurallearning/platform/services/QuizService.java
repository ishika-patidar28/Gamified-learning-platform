package com.rurallearning.platform.services;

import com.rurallearning.platform.entity.Quiz;
import com.rurallearning.platform.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    public List<Quiz> getQuizzesBySubject(Long subjectId) {
        return quizRepository.findBySubjectId(subjectId);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
