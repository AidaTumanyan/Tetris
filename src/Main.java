import tetris.JTetris;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JTetris tetris = new JTetris(16);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setVisible(true);
    }
}