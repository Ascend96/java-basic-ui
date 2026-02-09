import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MenuGUI extends JFrame {
    private JTextArea textArea;
    private final Random random = new Random();

    // constants for the GUI
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final String LOG_FILE_NAME = "log.txt";
    private static final int GREEN_BASE = 150;
    private static final int GREEN_RANGE = 106;
    private static final int RED_BLUE_MAX = 50;

    public MenuGUI() {
        initializeFrame();
        initializeComponents();
        createMenuBarAndItems();
    }

    // handles writing the text in the text area to a file called log.txt
    private void logToFile() {
        String content = textArea.getText();
        if (content == null || content.trim().isEmpty()) {
            showError("Cannot log empty content");
            return;
        }

        try (FileWriter writer = new FileWriter(LOG_FILE_NAME, true)) {
            writer.write(content + System.lineSeparator());
            showSuccess("Successfully wrote to " + LOG_FILE_NAME);
        } catch (IOException ex) {
            showError("Error writing to file: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        textArea.setText(message);
    }

    // private method for creating menu items and setting event listeners
    private void createMenuBarAndItems() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        addMenuItem(menuBar, MenuAction.SHOW_DATE, this::showDateTime);
        addMenuItem(menuBar, MenuAction.LOG_TO_FILE, this::logToFile);
        addMenuItem(menuBar, MenuAction.RANDOM_GREEN, this::randomShadeOfGreen);
        addMenuItem(menuBar, MenuAction.EXIT, this::exitApplication);
    }

    private void addMenuItem(JMenuBar menuBar, MenuAction action, Runnable handler) {
        JMenuItem menuItem = new JMenuItem(action.getLabel());
        menuItem.addActionListener(e -> handler.run());
        menuBar.add(menuItem);
    }

    // handles changing the background to a random shade of green
    private void randomShadeOfGreen() {
        Color greenShade = generateRandomGreenShade();
        textArea.setBackground(greenShade);
        textArea.setText("Changed color to: RGB(" + greenShade.getRed() +
                ", " + greenShade.getGreen() + ", " + greenShade.getBlue() + ")");
    }

    private Color generateRandomGreenShade() {
        int green = GREEN_BASE + random.nextInt(GREEN_RANGE);
        int red = random.nextInt(RED_BLUE_MAX);
        int blue = random.nextInt(RED_BLUE_MAX);
        return new Color(red, green, blue);
    }

    // use a formatter for a more user friendly date and time
    private void showDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        textArea.setText(formatter.format(dateTime));
    }

    private void initializeComponents() {
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    // method that is a cleaner exit menu to show confirmation as opposed to just ending the application
    private void exitApplication() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void initializeFrame() {
        setTitle("Menu GUI");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center on screen
    }
}
