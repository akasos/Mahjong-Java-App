import java.awt.*;
import javax.swing.*;



public class CircleTile extends RankTile
{

    private Circle [] circles;

    public CircleTile(int rank)
    {
        super(rank);
        circles = new Circle[rank];
        this.setToolTipText(toString());

        switch(rank)
        {
            case 1:
                circles[0] = new Circle(34,30,6, Color.RED);
                break;

            case 2:
                circles[0] = new Circle (34, 20, 6, new Color(0, 132, 13));
                circles[1] = new Circle (34, 40, 6, Color.RED);
                break;

            case 3:
                circles[0] = new Circle (22, 10, 6, Color.BLUE);
                circles[1] = new Circle (34, 30, 6, Color.RED);
                circles[2] = new Circle(46, 50, 6, new Color(0, 132, 13));
                break;

            case 4:
                circles[0] = new Circle(22, 10, 6, Color.BLUE);
                circles[1] = new Circle(22, 50,6, new Color(0, 132, 13));
                circles[2] = new Circle(46, 10, 6, new Color(0, 132, 13));
                circles[3] = new Circle(46, 50, 6, Color.BLUE);
                break;

            case 5:
                circles[0] = new Circle(22,10,6, Color.BLUE);
                circles[1] = new Circle(22, 50, 6, new Color(0, 132, 13));
                circles[2] = new Circle(34,30, 6, Color.RED);
                circles[3] = new Circle(46, 10, 6, new Color(0, 132, 13));
                circles[4] = new Circle(46, 50, 6, Color.BLUE);
                break;

            case 6:
                circles[0] = new Circle(22,10,6, Color.BLUE);
                circles[1] = new Circle(22, 30, 6, new Color(0, 132, 13));
                circles[2] = new Circle(22,50,6, Color.RED);
                circles[3] = new Circle(46, 10, 6, new Color(0, 132, 13));
                circles[4] = new Circle(46, 30, 6, Color.BLUE);
                circles[5] = new Circle(46, 50, 6, Color.BLUE);
                break;

            case 7:
                circles[0] = new Circle(18,10,6, new Color(0, 132, 13));
                circles[1] = new Circle(34, 15, 6, new Color(0, 132, 13));
                circles[2] = new Circle(50,20,6, new Color(0, 132, 13));
                circles[3] = new Circle(24, 36, 6, Color.RED);
                circles[4] = new Circle(46, 36, 6, Color.RED);
                circles[5] = new Circle(24, 50, 6, Color.RED);
                circles[6] = new Circle(46, 50, 6, Color.RED);
                break;

            case 8:
                circles[0] = new Circle(22,6,6, Color.BLUE);
                circles[1] = new Circle(22, 22, 6, Color.BLUE);
                circles[2] = new Circle(22,38,6, Color.BLUE);
                circles[3] = new Circle(22, 54, 6, Color.BLUE);
                circles[4] = new Circle(46, 6, 6, Color.BLUE);
                circles[5] = new Circle(46, 22, 6, Color.BLUE);
                circles[6] = new Circle(46, 38, 6, Color.BLUE);
                circles[7] = new Circle(46, 54, 6, Color.BLUE);
                break;

            case 9:
                circles[0] = new Circle(20,10,6,  new Color(0, 132, 13));
                circles[1] = new Circle(20, 30, 6,  Color.RED);
                circles[2] = new Circle(20,50,6,  Color.BLUE);
                circles[3] = new Circle(34, 10, 6, new Color(0, 132, 13));
                circles[4] = new Circle(34, 30, 6, Color.RED);
                circles[5] = new Circle(34, 50, 6, Color.BLUE);
                circles[6] = new Circle(48, 10, 6, new Color(0, 132, 13));
                circles[7] = new Circle(48, 30, 6, Color.RED);
                circles[8] = new Circle(48, 50, 6, Color.BLUE);
                break;

        }

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (Circle c : circles)
            if (c != null)
                c.draw(g);
    }


    class Circle
    {
        private int x;
        private int y;
        private int radius;
        private Color color;
        public Circle (int x, int y, int radius, Color color)
        {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        public void draw (Graphics g)
        {
            if(circles.length == 1)
            {
                new Pancake(this , g);
            }
            g.setColor(this.color);
            g.fillOval(x - radius, y-radius, radius * 2, radius *2);
            g.setColor(Color.WHITE);

            g.drawLine(x-radius + 3 , y-radius + 3, (radius * 2 - 9) + x, (radius * 2 - 9) + y);
            g.drawLine(x-radius + 3 , y + radius - 3 , (radius * 2 - 9) + x, (radius * 2 - 15) + y);

        }
    }

    class Pancake
    {
        private Circle circle;
        private  double degrees;
        private  double x;
        private double y;


        public Pancake(Circle circle , Graphics g)
        {
            this.circle = circle;
            paintComponent(g);
        }

        public void paintComponent(Graphics g)
        {

            g.setColor( new Color(0, 132, 13));
            g.fillOval(circle.x - circle.radius - 15, circle.y - circle.radius - 15, circle.radius * 2 + 30, circle.radius * 2 + 30);
            g.setColor(Color.white);
            g.fillOval(circle.x - circle.radius - 1, circle.y - circle.radius - 1, circle.radius * 2 + 2, circle.radius * 2 + 2);
            g.setColor(Color.white);

            this.x = circle.x;
            this.y = circle.y;
            this.degrees = 0;

            g.fillOval((int) x, (int) y, 4, 4);

            for(int i = 0; i < 12; i++)
            {
                g.fillOval((int) (x + 14 * Math.cos(Math.toRadians(degrees))) - 2, (int) (y - +14 * Math.sin(Math.toRadians(degrees))) - 2, 4, 4);

                degrees += 30;
                x = 34;
                y = 30;
            }

        }
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Circle Tiles");


        frame.add(new CircleTile(1));
        frame.add(new CircleTile(2));
        frame.add(new CircleTile(3));
        frame.add(new CircleTile(4));
        frame.add(new CircleTile(5));
        frame.add(new CircleTile(6));
        frame.add(new CircleTile(7));
        frame.add(new CircleTile(8));
        frame.add(new CircleTile(9));

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString()
    {
        return "Circle " + rank;
    }


}
