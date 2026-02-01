import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class MenuGUI extends JFrame {
    private JTextArea textArea;
    private Random random = new Random();

    public MenuGUI() {
        // set up the frame
        setTitle("Menu GUI");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // setup the text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        createMenuBarAndItems();
    }

    // handles displaying the current date and time to the text area
    private void showDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        textArea.setText(dateTime.toString());
    }

    // handles writing the text in the text area to a file called log.txt
    private void logToFile() {
        try(FileWriter writer = new FileWriter("log.txt", true)){
            writer.write(textArea.getText() + System.lineSeparator());
            textArea.setText("Sucessfully wrote to the file log.txt");
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this,
                    "Error attempting to write to file",
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // handles changing the background to a random shade of green
    private void randomShadeOfGreen() {
        // we need the green part of the RGB to be high so well start at 150
        // and take a random number between 0-106.
        // this gets us a random shade of green because the base being 150 + any number between 0-106 will be from 150-255 which is the max
        int green = 150 + random.nextInt(106);
        int red = random.nextInt(50); // this will keep the red low (< 50)
        int blue = random.nextInt(50); // this will keep the blue low (< 50)
        Color greenShade =  new Color(red, green, blue);
        textArea.setBackground(greenShade);
        repaint();
        textArea.setText("Changed color to: " + greenShade);
    }

    // private method for creating menu items and setting event listeners
    private void createMenuBarAndItems() {
        // menu setup
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem menuItem1 = new JMenuItem("Show Date");
        menuBar.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Log Text to File");
        menuBar.add(menuItem2);

        JMenuItem menuItem3 = new JMenuItem("Random Green Hue");
        menuBar.add(menuItem3);

        JMenuItem menuItem4 = new JMenuItem("Exit");
        menuBar.add(menuItem4);

        // actions for menu items
        menuItem1.addActionListener(e -> showDateTime());
        menuItem2.addActionListener(e -> logToFile());
        menuItem3.addActionListener(e -> randomShadeOfGreen());
        menuItem4.addActionListener(e -> System.exit(0));
    }
}
