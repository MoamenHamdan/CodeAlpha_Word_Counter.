import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAlpha_MoamenHamdan extends JFrame {
    private JTextArea paragraphTextArea;
    private JButton countButton;
    private JButton clearButton;
    private JLabel wordCountLabel;
    private JLabel charCountLabel;
    private JLabel sentenceCountLabel;
    private JLabel paragraphCountLabel;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        paragraphTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(paragraphTextArea);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        countButton = new JButton("Count");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paragraphTextArea.setText("");
                clearCounts();
            }
        });

        buttonPanel.add(countButton);
        buttonPanel.add(clearButton);

        textPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(textPanel, BorderLayout.CENTER);

        JPanel countPanel = new JPanel();
        countPanel.setLayout(new GridLayout(4, 2, 5, 5));
        countPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        wordCountLabel = new JLabel("Word Count: 0");
        charCountLabel = new JLabel("Character Count: 0");
        sentenceCountLabel = new JLabel("Sentence Count: 0");
        paragraphCountLabel = new JLabel("Paragraph Count: 0");

        countPanel.add(wordCountLabel);
        countPanel.add(charCountLabel);
        countPanel.add(sentenceCountLabel);
        countPanel.add(paragraphCountLabel);

        mainPanel.add(countPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void countWords() {
        String paragraph = paragraphTextArea.getText();
        int wordCount = countWords(paragraph);
        int charCount = countCharacters(paragraph);
        int sentenceCount = countSentences(paragraph);
        int paragraphCount = countParagraphs(paragraph);

        wordCountLabel.setText("Word Count: " + wordCount);
        charCountLabel.setText("Character Count: " + charCount);
        sentenceCountLabel.setText("Sentence Count: " + sentenceCount);
        paragraphCountLabel.setText("Paragraph Count: " + paragraphCount);
    }

    private int countWords(String paragraph) {
        if (paragraph == null || paragraph.isEmpty()) {
            return 0;
        }
        String[] words = paragraph.trim().split("\\s+");
        return words.length;
    }

    private int countCharacters(String paragraph) {
        if (paragraph == null || paragraph.isEmpty()) {
            return 0;
        }
        return paragraph.length();
    }

    private int countSentences(String paragraph) {
        if (paragraph == null || paragraph.isEmpty()) {
            return 0;
        }
        Pattern pattern = Pattern.compile("[.!?]+");
        Matcher matcher = pattern.matcher(paragraph);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private int countParagraphs(String paragraph) {
        if (paragraph == null || paragraph.isEmpty()) {
            return 0;
        }
        String[] paragraphs = paragraph.trim().split("\n\n+");
        return paragraphs.length;
    }

    private void clearCounts() {
        wordCountLabel.setText("Word Count: 0");
        charCountLabel.setText("Character Count: 0");
        sentenceCountLabel.setText("Sentence Count: 0");
        paragraphCountLabel.setText("Paragraph Count: 0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCounter();
            }
        });
    }
}
