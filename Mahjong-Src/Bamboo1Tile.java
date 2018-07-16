import java.awt.*;
import javax.swing.*;
import java.net.*;
public class Bamboo1Tile extends PictureTile {

    private ImageIcon image;

    private URL url;


    public Bamboo1Tile()
    {
        super("Sparrow");
        url = Bamboo1Tile.class.getResource("images/" + super.toString() + ".png");
        image = new ImageIcon(url);
        image =  new  ImageIcon(image.getImage().getScaledInstance((int)(image.getImage().getWidth(this)* 0.7),-1,Image.SCALE_SMOOTH));
        setToolTipText(super.toString());

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 10 + ((48 - image.getImage().getWidth(this))/ 2), 0 + ((60 - image.getIconHeight())/ 2), this);

    }

    @Override
    public String toString() {
        return "Bamboo 1";
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bamboo");

        frame.add(new Bamboo1Tile());


        frame.pack();
        frame.setVisible(true);

    }
}

