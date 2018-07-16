import java.awt.*;
import javax.swing.*;
public class BambooTile extends RankTile
{
    private Bamboo bamboos [];

    public BambooTile(int rank)
    {
        super(rank);

        bamboos = new Bamboo[rank];
        this.setToolTipText(toString());

        switch(rank)
        {
            case 2:
                bamboos[0] = new Bamboo(34, 10, Color.BLUE);
                bamboos[1] = new Bamboo( 34, 32, new Color(0,132,13));
                break;

            case 3:
                bamboos[0] = new Bamboo( 34, 10, Color.BLUE);
                bamboos[1] = new Bamboo( 26, 32, new Color(0,132,13));
                bamboos[2] = new Bamboo( 42, 32, new Color(0,132,13));
                break;

            case 4:
                bamboos[0] = new Bamboo( 26, 10, Color.BLUE);
                bamboos[1] = new Bamboo( 26, 32, new Color(0,132,13));
                bamboos[2] = new Bamboo( 42, 10, Color.BLUE);
                bamboos[3] = new Bamboo( 42, 32, new Color(0,132,13));
                break;

            case 5:
                bamboos[0] = new Bamboo( 21, 10, new Color(0,132,13));
                bamboos[1] = new Bamboo( 47, 10, Color.BLUE);
                bamboos[2] = new Bamboo( 34, 21, Color.RED);
                bamboos[3] = new Bamboo( 21, 32, Color.BLUE);
                bamboos[4] = new Bamboo( 47, 32, new Color(0,132,13));
                break;

            case 6:
                bamboos[0] = new Bamboo( 21, 10, new Color(0,132,13));
                bamboos[1] = new Bamboo( 34, 10,  new Color(0,132,13));
                bamboos[2] = new Bamboo( 47, 10,  new Color(0,132,13));
                bamboos[3] = new Bamboo( 21, 32, Color.BLUE);
                bamboos[4] = new Bamboo( 34, 32, Color.BLUE);
                bamboos[5] = new Bamboo( 47, 32, Color.BLUE);

                break;

            case 7:
                bamboos[0] = new Bamboo( 34, 1, Color.RED);
                bamboos[1] = new Bamboo( 21, 21,  new Color(0,132,13));
                bamboos[2] = new Bamboo( 34, 21,  new Color(0,132,13));
                bamboos[3] = new Bamboo( 47, 21,  new Color(0,132,13));
                bamboos[4] = new Bamboo( 21, 41, Color.BLUE);
                bamboos[5] = new Bamboo( 34, 41, Color.BLUE);
                bamboos[6] = new Bamboo( 47, 41, Color.BLUE);
                break;

            case 8:
                bamboos[0] = new Bamboo( 21, 1, new Color(0,132,13));
                bamboos[1] = new Bamboo( 34, 1, new Color(0,132,13));
                bamboos[2] = new Bamboo( 47, 1, new Color(0,132,13));
                bamboos[3] = new Bamboo( 25, 21, Color.RED);
                bamboos[4] = new Bamboo( 43, 21, Color.RED);
                bamboos[5] = new Bamboo( 21, 41, Color.BLUE);
                bamboos[6] = new Bamboo( 34, 41, Color.BLUE);
                bamboos[7] = new Bamboo( 47, 41, Color.BLUE);
                break;

            case 9:
                bamboos[0] = new Bamboo( 21, 1, Color.RED);
                bamboos[1] = new Bamboo( 34, 1, Color.BLUE);
                bamboos[2] = new Bamboo( 47, 1, new Color(0,132,13));
                bamboos[3] = new Bamboo( 21, 21, Color.RED);
                bamboos[4] = new Bamboo( 34, 21, Color.BLUE);
                bamboos[5] = new Bamboo( 47, 21, new Color(0,132,13));
                bamboos[6] = new Bamboo( 21, 41, Color.RED);
                bamboos[7] = new Bamboo( 34, 41, Color.BLUE);
                bamboos[8] = new Bamboo( 47, 41, new Color(0,132,13));
                break;


        }
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for(Bamboo b : bamboos)
        {
            if(b != null)
                b.draw(g);
        }

    }

    class Bamboo {
        private Color color;
        private int x;
        private int y;



        public Bamboo(int x, int y, Color c) {
            this.x = x;
            this.y = y;
            this.color = c;


        }

        public void draw(Graphics g) {

            g.setColor(color);
            g.fillRect(x - 2, y, 4, 18);
            g.fillArc(x - 5, y, 9, 10, 0, 180);
            g.fillArc(x - 5, y + 14, 9, 10, 0, 180);

            g.setColor(Color.WHITE);
            g.drawLine(x, y, x, y + 18);
            g.setColor(color);
            g.fillArc(x - 5, y + 7, 9, 10, 0, 180);

        }
    }


    @Override
    public String toString()
    {
        return "Bamboo " + rank;
    }


    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bamboo Tiles");

        frame.add(new BambooTile(2));
        frame.add(new BambooTile(3));
        frame.add(new BambooTile(4));
        frame.add(new BambooTile(5));
        frame.add(new BambooTile(6));
        frame.add(new BambooTile(7));
        frame.add(new BambooTile(8));
        frame.add(new BambooTile(9));

        frame.pack();
        frame.setVisible(true);
    }
}
