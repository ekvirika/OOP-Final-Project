package DAO;

import Models.Enums.QuestionType;
import Models.Question;
import com.google.gson.Gson;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionDAO {

    private BasicDataSource dataSource;

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Question ReadQuestion(int questionId) {
        String query = "SELECT * FROM Question WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToQuestion(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void CreateQuestion(Question question) {
        String query = "INSERT INTO Question (quizId, questionType, questionText, " +
                "singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, " +
                "multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, " +
                "matchingPairs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            setStatement(question, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStatement(Question question, PreparedStatement statement) throws SQLException {
        Gson gson = new Gson();
        statement.setInt(1, question.getQuizId());
        statement.setInt(2, question.getQuestionType().ordinal());
        statement.setString(3, question.getQuestionText());
        statement.setString(4, question.getSingleQuestionAnswer());
        statement.setString(5, gson.toJson(question.getAlternativeAnswers()));
        statement.setString(6, gson.toJson(question.getMultipleChoiceAnswers()));
        statement.setString(7, gson.toJson(question.getMultipleChoiceCorrectIndexes()));
        statement.setString(8, question.getQuestionImage());
        statement.setString(9, gson.toJson(question.getMultipleAnswerFields()));
        statement.setString(10, gson.toJson(question.getMatchingPairs()));
    }

    public void updateQuestion(Question question) {
        String query = "UPDATE Question SET quizId = ?, questionType = ?, " +
                "questionText = ?, singleQuestionAnswer = ?, alternativeAnswers = ?, " +
                "multipleChoiceAnswers = ?, multipleChoiceCorrectIndexes = ?, " +
                "questionImage = ?, multipleAnswerFields = ?, matchingPairs = ? " +
                "WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            setStatement(question, statement);
            statement.setInt(11, question.getQuestionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int questionId) {
        String query = "DELETE FROM Question WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Question mapRowToQuestion(ResultSet resultSet) throws SQLException {
        Gson gson = new Gson();
        Question question = new Question(resultSet.getInt("questionId"));
        question.setQuizId(resultSet.getInt("quizId"));
        question.setQuestionType(QuestionType.values()[resultSet.getInt("questionType")]);
        question.setQuestionText(resultSet.getString("questionText"));
        question.setSingleQuestionAnswer(resultSet.getString("singleQuestionAnswer"));
        question.setAlternativeAnswers(gson.fromJson(resultSet.getString("alternativeAnswers"), HashSet.class));
        question.setMultipleChoiceAnswers(gson.fromJson(resultSet.getString("multipleChoiceAnswers"),ArrayList.class));
        question.setMultipleChoiceCorrectIndexes(gson.fromJson(resultSet.getString("multipleChoiceCorrectIndexes"),ArrayList.class));
        question.setQuestionImage(resultSet.getString("questionImage"));
        question.setMultipleAnswerFields(gson.fromJson(resultSet.getString("multipleAnswerFields"), ArrayList.class));
        question.setMatchingPairs(gson.fromJson(resultSet.getString("matchingPairs"),HashMap.class));
        return question;
    }
}
