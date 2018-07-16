import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel
{

    static final int WIDTH = 48;
    static final int HEIGHT = 60;
    static final int EDGE = 11;

    private int xLocation;
    private int yLocation;
    private int layer;
    int sortOrder;
    private int zOrder;

    private int xInDataStruct;
    private int yInDataStruct;
    private int zInDataStruct;



    boolean OutlierTile = false;


    public Tile()
    {
   /*     setPreferredSize(new Dimension(59, 71));*/
        setMaximumSize(new Dimension(59, 71));
        setSize(59, 71);
        setOpaque(false);
    }

    public boolean matches(Tile otherObject)
    {
        if (this == otherObject)
            return true;

        else if (otherObject == null)
            return false;

        else if (getClass() != otherObject.getClass())
            return false;

        else return true;


    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Gradient for top rectangle and draw method.
        GradientPaint grad = new GradientPaint(59, 0,  new Color(244, 164,96), 10, 60, Color.WHITE);
        g2.setPaint(grad);
        g2.fillRect(10, 0, 48, 60);
        g2.setColor(Color.black);
        g2.draw3DRect(10,0, 48, 60, true);




        // Gradient for the 1st layer, left polygon and draw method.
        grad = new GradientPaint(10, 0 ,new Color(244,164,96) , 5, 65, Color.WHITE);

        int xPoints [] = {5, 10, 10, 5};
        int yPoints[] = {5, 0, 60, 65};
        int nPoints = 4;
        Polygon p0 = new Polygon(xPoints,yPoints,nPoints);
        g2.setPaint(grad);
        g2.fill(p0);
        g2.setColor(Color.black);
        g2.draw(p0);


        //Gradient for the 1st layer, bottom polygon and draw method.
        grad = new GradientPaint(59,60, new Color(244,164,96), 5,65, Color.WHITE);

        int xPoints1 []= {58, 53, 5, 10};
        int yPoints1 [] = {60, 65, 65, 60};
        int nPoints1 = 4;
        p0 = new Polygon(xPoints1,yPoints1,nPoints);
        g2.setPaint(grad);
        g2.fill(p0);
        g2.setColor(Color.black);
        g2.draw(p0);



        //Gradient for the 2nd layer, left polygon and draw method.
        grad = new GradientPaint(5,10, new Color(6, 73, 160), 0,70, Color.WHITE);

        int xPoints2 []= {0, 5, 5, 0};
        int yPoints2 [] = {10, 5, 65, 70};
        int nPoints2 = 4;
        p0 = new Polygon(xPoints2,yPoints2,nPoints2);
        g2.setPaint(grad);
        g2.fill(p0);
        g2.setColor(Color.black);
        g2.draw(p0);


        //Gradient for the 2nd layer, bottom polygon and draw method.
        grad = new GradientPaint(59,75, new Color(6, 73, 160), 0,75, Color.WHITE);

        int xPoints3 []= {53, 48, 0, 5};
        int yPoints3 [] = {65, 70, 70, 65};
        int nPoints3 = 4;
        p0 = new Polygon(xPoints3,yPoints3,nPoints3);
        g2.setPaint(grad);
        g2.fill(p0);
        g2.setColor(Color.black);
        g2.draw(p0);


    }


    public int getXLocation()
    {
        return xLocation;
    }

    public int getYLocation() { return yLocation; }

    public int getLayerLocation()
    {
        return layer;
    }

    public int getSortOrder() { return sortOrder; }

    public int getZOrder () {return zOrder;}

    public int getxInDataStruct() { return xInDataStruct; }

    public int getyInDataStruct() { return yInDataStruct; }

    public int getzInDataStruct() {return zInDataStruct;}

    public boolean isOutlierTile() { return OutlierTile; }


    public void setSortOrder(int sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public void setZOrder() { this.zOrder = getParent().getComponentZOrder(this);}

    public void setXLocation(int xLocation) { this.xLocation = xLocation; }

    public void setYLocation(int yLocation) { this.yLocation = yLocation; }

    public void setLayerLocation(int lLocation) { this.layer = lLocation; }


    public void setxInDataStruct(int xInDataStruct) { this.xInDataStruct = xInDataStruct; }

    public void setyInDataStruct(int yInDataStruct) { this.yInDataStruct = yInDataStruct; }

    public void setzInDataStruct(int zInDataStruct) { this.zInDataStruct = zInDataStruct; }

    public void setOutlierTile(boolean outlierTile) { OutlierTile = outlierTile; }


    public void resetZOrder()
    {
        getParent().setComponentZOrder(this, this.zOrder);
    }





    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tile");

        frame.add(new Tile());

        frame.pack();
        frame.setVisible(true);
    }



}
