import java.awt.*;
import javax.swing.*;
public class WhiteDragonTile extends Tile
{

  public  WhiteDragonTile(){}


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        this.setToolTipText(toString());

        //Top and bottom Blue Rects
        g2.setColor(Color.BLUE);

        //Outer Rect
        g2.drawRoundRect(15,11, 37,41,0, 0);

        //Inner Rect
        g2.drawRoundRect(20,16,27,31,0,0);


        g2.fillRect(16,12,6,4);
        g2.fillRect(28, 12, 6, 4);
        g2.fillRect(40, 12, 6, 4);


        g2.fillRect(16,48,6,4);
        g2.fillRect(28, 48, 6, 4);
        g2.fillRect(40, 48, 6, 4);


        //Top and bottom White Rects
        g2.setColor(Color.WHITE);
        g2.fillRect(22,12, 6, 4);
        g.fillRect(34, 12, 6, 4);
        g2.fillRect(46, 12, 6, 4);

        g2.fillRect(22,48, 6, 4);
        g.fillRect(34, 48, 6, 4);
        g2.fillRect(46, 48, 6, 4);


        //Left Vertical Bar
        g2.setColor(Color.WHITE);
        g2.fillRect(16, 16, 4, 6);
        g2.fillRect(16, 28, 4, 6);
        g2.fillRect(16,40, 4, 8);

        g2.setColor(Color.BLUE);
        g2.fillRect(16, 22, 4, 6);
        g2.fillRect(16, 34, 4, 6);


        //Right Vertical Bar
        g2.setColor(Color.BLUE);
        g2.fillRect(48, 16, 4, 6);
        g2.fillRect(48, 28, 4, 6);
        g2.fillRect(48,40, 4, 8);

        g2.setColor(Color.WHITE);
        g2.fillRect(48, 22, 4, 6);
        g2.fillRect(48, 34, 4, 6);

    }


    @Override
    public String toString()
    {
        return "White Dragon";
    }


    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("White Dragon Tile");

        frame.add(new WhiteDragonTile());

        frame.pack();
        frame.setVisible(true);
    }
}

