import java.awt.*;
import javax.swing.*;
import java.net.*;

public class FlowerTile extends PictureTile
{
   /* priavte Image;*/
    private ImageIcon image;
    private URL url;

    public FlowerTile(String name)
    {
        super(name);

        url = FlowerTile.class.getResource("images/" + super.toString() + ".png");
        switch(name)

        {
            case "Chrysanthemum":
            /*    image = Toolkit.getDefaultToolkit().getImage(url);*/
                image = new ImageIcon(url);
                setToolTipText("Chrysanthemum");
                break;

            case "Orchid":
         /*       image = Toolkit.getDefaultToolkit().getImage(url);*/
                image = new ImageIcon(url);
                setToolTipText("Orchid");
                break;

            case "Plum":
               /* image = Toolkit.getDefaultToolkit().getImage(url);*/
                image = new ImageIcon(url);
                setToolTipText("Plum");
                break;

            case "Bamboo":
              /*  image = Toolkit.getDefaultToolkit().getImage(url);*/
                image = new ImageIcon(url);
                setToolTipText("Bamboo");
                break;

        }

     /*   MediaTracker tracker = new MediaTracker(this);

        tracker.addImage(image, 0);

        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException ie)
        {
            JOptionPane.showMessageDialog(this, "Unable to load Image", "Image error", JOptionPane.ERROR_MESSAGE);
        }*/

        /*image = image.getScaledInstance((int)(image.getWidth(this)* 0.7),-1,Image.SCALE_SMOOTH); // -1*/
        image =  new  ImageIcon(image.getImage().getScaledInstance((int)(image.getImage().getWidth(this)* 0.7),-1,Image.SCALE_SMOOTH));


      /*  tracker.addImage(image, 0);

        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException ie)
        {
            JOptionPane.showMessageDialog(this, "Unable to load Image", "Image error", JOptionPane.ERROR_MESSAGE);
        }*/

    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
/*        g.drawImage(image, 10 + ((48 - image.getWidth(this))/ 2), 0 + ((60 - image.getHeight(this)) / 2), this);*/
        g.drawImage(image.getImage(), 10 + ((48 - image.getImage().getWidth(this))/ 2), 0 + ((60 - image.getIconHeight())/ 2), this);


    }
    
    


    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flower Tiles");

        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));

        frame.pack();
        frame.setVisible(true);
    }
}
