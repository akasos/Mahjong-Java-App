import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import javax.swing.*;
import java.net.*;

public class SeasonTile extends PictureTile
{

    private ImageIcon   image;
    private URL url;

    public SeasonTile(String name)
    {
        super(name);

        url = SeasonTile.class.getResource("images/" + super.toString() + ".png");

        switch(name)
        {
            case "Spring":
                image = new ImageIcon(url);
                setToolTipText("Spring");
                break;

            case "Summer":
                image = new ImageIcon(url);
                setToolTipText("Summer");
                break;

            case "Fall":
                image = new ImageIcon(url);
                setToolTipText("Fall");
                break;

            case "Winter":
                image = new ImageIcon(url);
                setToolTipText("Winter");
                break;

        }

        image =  new  ImageIcon(image.getImage().getScaledInstance((int)(image.getImage().getWidth(this)* 0.7),-1,Image.SCALE_SMOOTH)); // -1

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
       g.drawImage(image.getImage(), 10 + ((48 - image.getImage().getWidth(this))/ 2), 0 + ((60 - image.getIconHeight())/ 2), this);

    }


    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Season Tiles");

        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));

        frame.pack();
        frame.setVisible(true);
    }
}
