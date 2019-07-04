package ru.sber.seq.tasks.graphic;

import ru.sber.seq.tasks.steps.Step;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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

        JScrollPane scroll = new JScrollPane(myPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(text);
        mainPanel.add(scroll);

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

class MyPanel extends JPanel implements Scrollable {

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

        List<GraphTop> tops = new ArrayList<>();
        List<List<Integer>> matrix = getMatrix(steps);  //method helps to get adjacency list from adjacency matrix

        Integer X = 50;
        Integer Y = 20;

        Integer X_previous = 0; //vars help to draw edge of graph
        Integer Y_previous = 0;

        for (int i = 0; i < steps.size(); i++){
            tops.add(new GraphTop(steps.get(i).getNumber(), false, X, Y, matrix.get(i))); //getting adjacency list
        }

        printFirst(g, tops, 0);

        Y += 50;

        for (int i = 0; i < tops.size(); i++){
            List<Integer> arr = tops.get(i).getMatrix();

            X_previous = tops.get(i).getX();
            Y_previous = tops.get(i).getY();

            for (int j = 0; j < arr.size(); j++) {
                if (!tops.get(arr.get(j)).getPrinted()) {
                    tops.get(arr.get(j)).setX(X);
                    tops.get(arr.get(j)).setY(Y);
                }

                drawEdge(g, X_previous, Y_previous, tops.get(arr.get(j)).getX(), tops.get(arr.get(j)).getY());

                if (!tops.get(arr.get(j)).getPrinted()) {
                    printTop(g, tops.get(arr.get(j)));
                }

                X += 40 * 2;
            }

            X = (i+30)*30/(int)Math.pow((i+2),2);

            Y += 70;
        }

    }

    private void drawEdge(Graphics g, Integer x_previous, Integer y_previous, Integer x, Integer y) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);

        g2d.setStroke(new BasicStroke(3));
        g.drawLine(x_previous, y_previous, x, y);

        g2d.setColor(Color.YELLOW);
        g.fillOval(x_previous, y_previous, 7,7);
        g.fillOval(x, y, 7,7);
    }

    private void printFirst(Graphics g, List<GraphTop> tops, int i) {
        g.setFont(new Font("Calibri", Font.PLAIN, 30));
        g.setColor(Color.GREEN);

        g.drawString(tops.get(i).getNumber().toString(), tops.get(i).getX(), tops.get(i).getY());
        tops.get(i).setPrinted(true);
    }

    private void printTop(Graphics g, GraphTop top) {
        g.setFont(new Font("Calibri", Font.PLAIN, 30));

        if (!isFallen.get(top.getNumber())){
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }

        g.drawString(top.getNumber().toString(), top.getX(), top.getY());
        top.setPrinted(true);
    }

    private List<List<Integer>> getMatrix(List<Step> steps) {
        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < steps.size(); i++){
            matrix.add(new ArrayList<>());

            for (int j = 0; j < steps.size(); j++){
                if (steps.get(i).getRelatedSteps().get(j) == 1){
                    matrix.get(i).add(j);
                }
            }
        }
        return matrix;
    }

    public Dimension getPreferredSize() {
        return getPreferredScrollableViewportSize();
    }

    public Dimension getPreferredScrollableViewportSize() {
        if (getParent() == null)
            return getSize();
        Dimension d = getParent().getSize();
        int c = (int) Math
                .floor((d.width - getInsets().left - getInsets().right) / 50.0);
        if (c == 0)
            return d;
        int r = 20 / c;
        if (r * c < 20)
            ++r;
        return new Dimension(c * 200, r * 200);
    }
    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation, int direction) {
        return 100;
    }
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
                                          int direction) {
        return 30;
    }
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    public boolean getScrollableTracksViewportWidth() {
        return getParent() != null ? getParent().getSize().width > getPreferredSize().width
                : true;
    }
}

class MainPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
