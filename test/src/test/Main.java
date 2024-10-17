package test;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookManagerGUI app = new BookManagerGUI();
            app.setVisible(true);
        });
    }
}
