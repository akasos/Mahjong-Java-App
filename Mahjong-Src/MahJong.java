import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;


public class MahJong extends JFrame
{
    private MahJongBoard mb;
    private ImageIcon image;
    private URL url;
    private JLayeredPane jLayeredPane;
    private JPanel panel;
    private final JTextField textField = new JTextField(5);


    static ScrollPane removedTiles;


    private long seed;


    public MahJong()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        setSize(1000, 1000);
        setTitle("MahJong");


        mb = new MahJongBoard();
        removedTiles = new ScrollPane();

        seed = mb.getGameSeed();

        url = MahJong.class.getResource("images/dragon_bg.png");

        image = new ImageIcon(url);
        Display dp = new Display();

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(206, 216, 229));

        jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(750,520));
        jLayeredPane.setLocation((this.getWidth() - 731)/2, (this.getHeight() - 498) / 2);

        dp.setBounds(20,0, image.getIconWidth(), image.getIconHeight());
        mb.setBounds(2,8, 735,500);

        jLayeredPane.add(mb,  new Integer(1));
        jLayeredPane.add(dp, new Integer(0));
        panel.add(jLayeredPane);
        panel.setOpaque(false);


        add(panel);
        add(removedTiles, BorderLayout.WEST);

        makeMenu();

        pack();

        setMinimumSize(new Dimension(930, 595));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        setVisible(true);

    }

    class Display extends JPanel
    {
        public Display()
        {
            setPreferredSize(new Dimension(image.getIconWidth(), (image.getIconHeight())));
        }

        public void paintComponent(Graphics g)
        {

            image.paintIcon(this, g, 0, 0);
        }

    }

    private void makeMenu()
    {
        //Creating the menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);
        menuBar.add(game);


        JMenu sound = new JMenu("Sound");
        sound.setMnemonic(KeyEvent.VK_S);
        menuBar.add(sound);


        JMenu move = new JMenu("Move");
        move.setMnemonic(KeyEvent.VK_M);
        menuBar.add(move);


        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        menuBar.add(help);

        //Adding menu items and setting tooltips

        //Adding to 'game' menu
        JMenuItem play = new JMenuItem("Play", KeyEvent.VK_P);
        JMenuItem restart = new JMenuItem("Restart", KeyEvent.VK_R);


        play.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
        restart.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));


        play.setToolTipText("Start a new game");
        restart.setToolTipText("Restart the current game");


        game.add(play);
        game.add(restart);



        //Adding to 'sound' menu
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem on = new JRadioButtonMenuItem("On", true);
        JRadioButtonMenuItem off = new JRadioButtonMenuItem("Off", false);

        on.setMnemonic(KeyEvent.VK_O);
        off.setMnemonic(KeyEvent.VK_F);

        on.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        off.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));

        on.setToolTipText("Turn on sound");
        off.setToolTipText("Turn off sound");

        group.add(on);
        group.add(off);

        sound.add(on);
        sound.add(off);


        //Adding to 'move' menu

        JMenuItem undo = new JMenuItem("Undo", KeyEvent.VK_U);

        undo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));

        undo.setToolTipText("Undo your last move");

        move.add(undo);


        //Adding to 'help' menu

        JMenuItem operation = new JMenuItem("Operation", KeyEvent.VK_I);
        JMenuItem gameRules = new JMenuItem("Game Ruels", KeyEvent.VK_B);

        operation.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        gameRules.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));

        operation.setToolTipText("");
        gameRules.setToolTipText("How to play");

        help.add(operation);
        help.add(gameRules);

        menuBar.add(Box.createHorizontalGlue());
        JLabel numberedGameText = new JLabel("Numbered Game: ");
        menuBar.add(numberedGameText);
        textField.setText(String.valueOf(seed));
        textField.setCaretPosition(textField.getText().length());
        textField.setMaximumSize(textField.getPreferredSize());
        menuBar.add(textField);


        //Adding action listeners
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });


        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    if(textField.getText().equals(""))
                        return;
                    seed = Long.parseLong(textField.getText().trim());
                    restartGame();
                }

                catch(NumberFormatException nfe)
                {
                    error();
                }
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mb.undo();
            }
        });

        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mb.setSoundONOFF(true);

            }
        });

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mb.setSoundONOFF(false);
            }
        });

        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Help help = new Help("help/GameRules.html", "Game Rules");
                help.display();
            }
        });

        operation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Help help = new Help("help/Operation.html", "Game Operation");
                help.display();
            }
        });

    }

    private void newGame()
    {
        if(removedTiles.isScrollPaneEmpty()) {
            mb.stopFireWorkReward();
            jLayeredPane.remove(mb);
            remove(removedTiles);
            removedTiles = new ScrollPane();
            add(removedTiles, BorderLayout.WEST);
            mb = new MahJongBoard();
            seed = mb.getGameSeed();
            textField.setText(Long.toString(seed));
            mb.setBounds(2, 8, 731, 498);
            mb.repaint();
            jLayeredPane.add(mb, new Integer(1));
            revalidate();
        }
        else
        {
            int option = JOptionPane.showConfirmDialog(this,"End current game?", "Confirm to start a new game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.YES_OPTION)
            {
                mb.stopFireWorkReward();
                jLayeredPane.remove(mb);
                remove(removedTiles);
                removedTiles = new ScrollPane();
                add(removedTiles, BorderLayout.WEST);
                mb = new MahJongBoard();
                seed = mb.getGameSeed();
                textField.setText(Long.toString(seed));
                mb.setBounds(2, 8, 731, 498);
                jLayeredPane.add(mb, new Integer(1));
                revalidate();
            }
        }
    }

    private void restartGame()
    {

        if(removedTiles.isScrollPaneEmpty()) {
            mb.stopFireWorkReward();
            jLayeredPane.remove(mb);
            remove(removedTiles);
            removedTiles = new ScrollPane();
            add(removedTiles, BorderLayout.WEST);
            mb = new MahJongBoard(seed);
            mb.setBounds(2, 8, 731, 498);
            jLayeredPane.add(mb, new Integer(1));
            revalidate();

        }
        else
        {
            int option = JOptionPane.showConfirmDialog(this,"Reset game?", "Confirm to reset game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.YES_OPTION)
            {
                mb.stopFireWorkReward();
                jLayeredPane.remove(mb);
                remove(removedTiles);
                removedTiles = new ScrollPane();
                add(removedTiles, BorderLayout.WEST);
                mb = new MahJongBoard(seed);
                mb.setBounds(2, 8, 731, 498);
                jLayeredPane.add(mb, new Integer(1));
                revalidate();
            }
        }

    }

    private void close()
    {

        if((!removedTiles.isScrollPaneEmpty()))
        {
            int option = JOptionPane.showConfirmDialog(this,"Exit Game?", "Confirm Game End", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        else
        {
            System.exit(0);
        }

    }

    private void  error()
    {
        JOptionPane.showMessageDialog(this, "Must be a number", "Invalid input", JOptionPane.ERROR_MESSAGE);
    }


    public static void main(String[] args)
    {
        new MahJong();
    }

}














