import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Hashtable;

public class QuizGame {
    private Hashtable<String, String> questionsAndAnswers = new Hashtable<>();
    private int score = 0;

    public QuizGame() {
        initializeDefaultQuestions();
    }

    private void initializeDefaultQuestions() {
        questionsAndAnswers.put("What is the capital of France?", "Paris");
        questionsAndAnswers.put("Which planet is known as the Red Planet?", "Mars");
        questionsAndAnswers.put("What is the largest mammal in the world?", "Blue Whale");
    }

    private void addQuestion(String question, String answer) {
        questionsAndAnswers.put(question, answer);
    }

    private void startGame() {
        if (questionsAndAnswers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No questions available. Please add questions.");
            return;
        }

        for (String currentQuestion : questionsAndAnswers.keySet()) {
            String correctAnswer = questionsAndAnswers.get(currentQuestion);
            handleUserAnswer(currentQuestion, correctAnswer);
        }

        showGameResult();
        resetGame();
    }

    private void handleUserAnswer(String currentQuestion, String correctAnswer) {
        String userAnswer = JOptionPane.showInputDialog(null, currentQuestion);
        if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(null, "Correct! Next question.");
            score++;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect! The correct answer is: " + correctAnswer);
        }
    }

    private void showGameResult() {
        JOptionPane.showMessageDialog(null, "Game Over. Your Score: " + score + "/" + questionsAndAnswers.size());
    }

    private void resetGame() {
        score = 0;
    }

    private static void createAndShowGUI() {
        QuizGame quizGame = new QuizGame();

        JFrame frame = new JFrame("QuizGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new LineBorder(Color.RED)); // Set black line border
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JButton startGame = new JButton("Start Game");
        JButton addQuestions = new JButton("Add Questions");
        JButton exit = new JButton("Exit");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Optional: Add some padding

        panel.add(startGame, gbc);

        gbc.gridy = 1;
        panel.add(addQuestions, gbc);

        gbc.gridy = 2;
        panel.add(exit, gbc);

        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quizGame.startGame();
            }
        });

        addQuestions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String question = JOptionPane.showInputDialog(null, "Enter the question:");
                String answer = JOptionPane.showInputDialog(null, "Enter the answer:");
                if (question != null && answer != null) {
                    quizGame.addQuestion(question, answer);
                    JOptionPane.showMessageDialog(null, "Question added successfully!");
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set the minimum size of the window
        frame.setMinimumSize(new Dimension(400, 300));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}