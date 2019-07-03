package ru.sber.seq.tasks.graphic;

import ru.sber.seq.tasks.steps.Step;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private MainPanel mainPanel;
    private MyPanel myPanel;

    public MainFrame(Map<Integer, Boolean> isFallen, List<Step> steps) {
        JFrame jf = new JFrame();
        jf.setTitle("Graph of Steps");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jf.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jf.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        mainPanel = new MainPanel();                    //external panel for two elements
        mainPanel.setLayout(new GridLayout());

        TextArea text = getText(isFallen);            //one from two entities contains some info
        myPanel = new MyPanel(isFallen, steps);             //two from two elements consists of picture of graph

        mainPanel.add(text);
        mainPanel.add(myPanel);

        jf.add(mainPanel);

        jf.setVisible(true);
    }

    private TextArea getText(Map<Integer, Boolean> isFallen) {
        TextArea textArea = new TextArea();
        textArea.setFont(new Font("Calibri ", Font.BOLD, 12));

        for (Map.Entry elem : isFallen.entrySet()){
            if (elem.getValue().equals(false)){
                textArea.append("Step " + elem.getKey() + " was run successfully\n");
            } else {
                textArea.append("Step " + elem.getKey() + " was fallen\n");
            }
        }

        return textArea;
    }
}

class MyPanel extends JPanel {

    private Map<Integer, Boolean> isFallen;
    private List<Step> steps;

    public MyPanel(Map<Integer, Boolean> isFallen, List<Step> steps) {
        this.isFallen = isFallen;
        this.steps = steps;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    private void draw(Graphics g) {
        List<List<Integer>> matrix = new ArrayList<>();

        g.setFont(new Font("Calibri", Font.PLAIN, 30));
        g.setColor(Color.WHITE);

        for (int i = 0; i < steps.size(); i++){
            matrix.add(steps.get(i).getRelatedSteps());
        }

        int x = 100;
        int y = 5;

        for (int i = 0; i < matrix.size(); i++){
            for (int j = 0; j < matrix.size(); j++){
                if (matrix.get(i).get(j) == -1){
                    g.fillOval(x, y, 10, 10);
                    x += 20;
                }
                x = 5;
                y += 10;
            }
        }
    }
}

class MainPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
