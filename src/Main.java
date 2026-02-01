import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // we use invoke later because Swing is not thread safe so we have to ensure
        // that the event dispatch thread runs as soon as it is ready.
        SwingUtilities.invokeLater(() -> {
            new MenuGUI().setVisible(true);
        });
    }
}