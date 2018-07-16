import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;


public class ScrollPane extends JScrollPane {

    private Box[] discard = new Box[2];
    private Stack<Tile> undoStack = new Stack<>();
    private int width = Tile.WIDTH + Tile.EDGE;
    private int height = Tile.HEIGHT + Tile.EDGE;
    private int count = 0;

    public ScrollPane() {
        setPreferredSize(new Dimension(width * 2 + 30, 0));
        setBorder(BorderFactory.createRaisedBevelBorder());

        discard[0] = new Box(BoxLayout.Y_AXIS);
        discard[1] = new Box(BoxLayout.Y_AXIS);
        discard[0].setPreferredSize(new Dimension(width, height));
        discard[1].setPreferredSize(new Dimension(width, height));


        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel(new BorderLayout());
        setViewportView(panel);

        panel.add(discard[0], BorderLayout.WEST);
        panel.add(discard[1], BorderLayout.EAST);

        discard[0].setBackground(new Color(206, 216, 229));
        discard[1].setBackground(new Color(206, 216, 229));
        panel.setBackground(new Color(206, 216, 229));


    }

    public void addToScrollPane(Tile t1, Tile t2) {

        undoStack.push(t1);
        undoStack.push(t2);

        Dimension size = new Dimension( width, ++count * height + 6);
        discard[0].setPreferredSize(size);
        discard[1].setPreferredSize(size);

        discard[0].add(t1);
        discard[1].add(t2);

        Rectangle	r = new Rectangle(0, count * height, width, height );
        getViewport().scrollRectToVisible(r);

        revalidate();
        repaint();

    }

    public void removeFromScrollPane()
    {
            Tile t1 = undoStack.pop();
            Tile t2 = undoStack.pop();

            Dimension size = new Dimension( width, --count * height + 6);
            discard[0].setPreferredSize(size);
            discard[1].setPreferredSize(size);

            discard[0].remove(t1);
            discard[1].remove(t2);

            Rectangle	r = new Rectangle(0, count * height, width, height );
            getViewport().scrollRectToVisible(r);

            revalidate();
            repaint();

    }

    public ArrayList<Tile> getNextTwoTiles()
    {

        ArrayList<Tile> tiles = new ArrayList<>();
        System.out.println(undoStack.size());
        tiles.add(undoStack.get(undoStack.size() - 1));
        tiles.add(undoStack.get(undoStack.size() - 2));

        return tiles;

    }

    public boolean twoOrMoreTilesOnStack()
    {
        return (undoStack.size() >= 2);
    }

    public int stackSize()
    {
        return undoStack.size();
    }


    public boolean isScrollPaneEmpty()
    {
        return undoStack.isEmpty();
    }

    public static void main(String[] args) {
        ScrollPane demo = new ScrollPane();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(demo);
        frame.setSize(118, 700);
        frame.setVisible(true);

        try
        {
            int	pause = 2000;

          /*  demo.addToScrollPane(new PictureTile("Chrysanthemum"), new PictureTile("Orchid"));
            Thread.sleep(pause);
            demo.addToScrollPane(new PictureTile("Plum"), new PictureTile("Bamboo"));
            Thread.sleep(pause);
            demo.addToScrollPane(new PictureTile("Spring"), new PictureTile("Summer"));
            Thread.sleep(pause);
            demo.addToScrollPane(new PictureTile("Fall"), new PictureTile("Winter"));
            Thread.sleep(pause);
*/
            demo.addToScrollPane(new CharacterTile('1'), new CharacterTile('2'));
            Thread.sleep(pause);
            demo.addToScrollPane(new CharacterTile('3'), new CharacterTile('4'));
            Thread.sleep(pause);
            demo.addToScrollPane(new CharacterTile('5'), new CharacterTile('6'));
            Thread.sleep(pause);
            demo.addToScrollPane(new CharacterTile('7'), new CharacterTile('8'));
    }
        catch (InterruptedException ie)
        {
            System.out.println(ie);
        }
}

}
