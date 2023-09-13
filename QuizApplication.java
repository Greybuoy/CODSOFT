import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private int timeLeft;

    // Define your quiz questions and answers here
    private String[] questions = {
        "Question 1: What is the capital of France?",
        "Question 2: Which planet is known as the Red Planet?",
        // Add more questions
    };
    
    private String[] correctAnswers = {
        "Paris",
        "Mars",
        // Add more correct answers
    };

    public QuizApplication() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsPanel.add(options[i]);
            optionGroup.add(options[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);

        currentQuestionIndex = 0;
        score = 0;
        timeLeft = 10; // Set the initial time for each question (in seconds)

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    // Time is up for the current question, move to the next question
                    showMessage("Time's up! Moving to the next question.");
                    moveToNextQuestion();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                moveToNextQuestion();
            }
        });

        displayQuestion();
        timer.start();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            for (int i = 0; i < 4; i++) {
                options[i].setText("Option " + (i + 1));
            }
            timer.restart();
        } else {
            endQuiz();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && options[i].getText().equals(correctAnswers[currentQuestionIndex])) {
                score++;
                break;
            }
        }
    }

    private void moveToNextQuestion() {
        currentQuestionIndex++;
        timeLeft = 10; // Reset the timer for the next question
        optionGroup.clearSelection();
        displayQuestion();
    }

    private void endQuiz() {
        timer.stop();
        showMessage("Quiz is over. Your score: " + score + "/" + questions.length);
        System.exit(0);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
