package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
    public static JTextField messageBomb;
    public static JTextField messageUFO;
    public static JTextField messageBird;
    public static JTextField messageFlash;
    public static JButton add10Button;
    public static JButton addUFOButton;
    public static JButton addBirdButton;
    public static JButton addFlashButton;
    public static JButton quitButton;

    public MainWindow() {

        Container c = getContentPane();

        c.add(Main.gamePanel, "Center");

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(4, 1));
        messageUFO = new JTextField("Statics of UFO!");
        messageUFO.setFont(new Font("Courier New", Font.BOLD, 16));
        messageUFO.setEditable(false);
        northPanel.add(messageUFO);
        messageBomb = new JTextField("Statics of Bomb!");
        messageBomb.setFont(new Font("Courier New", Font.BOLD, 16));
        messageBomb.setEditable(false);
        northPanel.add(messageBomb);
        messageBird = new JTextField("Statics of Bird!");
        messageBird.setFont(new Font("Courier New", Font.BOLD, 16));
        messageBird.setEditable(false);
        northPanel.add(messageBird);
        messageFlash= new JTextField("Statics of Flash!");
        messageFlash.setFont(new Font("Courier New", Font.BOLD, 16));
        messageFlash.setEditable(false);
        northPanel.add(messageFlash);
        c.add(northPanel,"North");
        
        JPanel southPanel = new JPanel();
        //southPanel.setLayout(new GridLayout(2, 3));
        add10Button = new JButton("Add 10");
        southPanel.add(add10Button);
        addUFOButton = new JButton("Add UFO");
        southPanel.add(addUFOButton);
        addBirdButton = new JButton("Add Bird");
        southPanel.add(addBirdButton);
        addFlashButton = new JButton("Add Flash");
        southPanel.add(addFlashButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        ButtonListener buttonListener = new ButtonListener();
        add10Button.addActionListener(buttonListener);
        addUFOButton.addActionListener(buttonListener);
        addBirdButton.addActionListener(buttonListener);
        addFlashButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);

        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        // just have one Component "true", the rest must be "false"
        add10Button.setFocusable(false);
        addUFOButton.setFocusable(false);
        addBirdButton.setFocusable(false);
        addFlashButton.setFocusable(false);
        quitButton.setFocusable(false);
        messageBomb.setFocusable(false);
        messageUFO.setFocusable(false);
        messageBird.setFocusable(false);
        messageFlash.setFocusable(false);
    }

}
