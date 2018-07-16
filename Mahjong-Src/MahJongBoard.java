import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class MahJongBoard extends JPanel implements MouseListener {

    private ArrayList<Tile> tileArrayList = new ArrayList<>();
    private MahJongModel mahJongModel;
    private	Border selected = BorderFactory.createLineBorder(Color.RED, 3);
    private Tile first = null;
    private Tile second = null;
    private boolean soundOnOFF = true;


    private long gameSeed;

    private int tileArrayLoopVariable;

    PlayClip clip = new PlayClip("audio/stone-scraping.wav", true);
    Fireworks fireworks;


    public MahJongBoard() {
       setPreferredSize(new Dimension(735, 500));
 /*       setBorder(BorderFactory.createRaisedBevelBorder());*/
      /*  setBorder(selected);*/
        setOpaque(false);
        setLayout(null);

        mahJongModel = new MahJongModel();
        gameSeed = mahJongModel.getSeed();

        drawTilesOnBoard();

    }

    public MahJongBoard(long seed)
    {
        setPreferredSize(new Dimension(735, 500));
       /* setBorder(BorderFactory.createRaisedBevelBorder());*/
        setLayout(null);
        setOpaque(false);
        mahJongModel = new MahJongModel(seed);
        gameSeed = mahJongModel.getSeed();

        drawTilesOnBoard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Tile tile = (Tile)e.getSource();
        tile.setZOrder();
        System.out.println(tile.getZOrder());
/*
        System.out.println(tile.getxInDataStruct());
        System.out.println(tile.getyInDataStruct());
        System.out.println(tile.getzInDataStruct());
*/

        if(SwingUtilities.isLeftMouseButton(e) && tile.equals(mahJongModel.getTileArray().get(0)))
        {
            selectingTiles(tile);
            return;
        }

        else if(SwingUtilities.isLeftMouseButton(e) && tile.equals(mahJongModel.getTileArray().get(1)))
        {
            selectingTiles(tile);
            return;
        }

        else if(SwingUtilities.isLeftMouseButton(e) && tile.equals(mahJongModel.getTileArray().get(2)) && mahJongModel.getTileArray().get(3).isOutlierTile() != true)
        {

            selectingTiles(tile);
            return;
        }

        else if(SwingUtilities.isLeftMouseButton(e) && tile.equals(mahJongModel.getTileArray().get(2)) && mahJongModel.getTileArray().get(3).isOutlierTile() == true)
        {
            return;
        }

        else if(SwingUtilities.isLeftMouseButton(e) && tile.equals(mahJongModel.getTileArray().get(3)))
        {
            selectingTiles(tile);
            return;
        }

        else if(tile.getLayerLocation() == 3 && mahJongModel.getTileArray().get(0).isOutlierTile() == true)
        {
            return;
        }

        else if((tile.getSortOrder() == 88 || tile.getSortOrder() == 100) && mahJongModel.getTileArray().get(1).isOutlierTile() == true)
        {
            return;
        }

        else if((tile.getSortOrder() == 111 || tile.getSortOrder() == 99) && mahJongModel.getTileArray().get(2).isOutlierTile() == true)
        {
            return;
        }

        else if(SwingUtilities.isLeftMouseButton(e) && mahJongModel.isTileOpen(tile.getxInDataStruct(), tile.getyInDataStruct(), tile.getzInDataStruct()))
        {
            selectingTiles(tile);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
   /*     Tile tile = (Tile)e.getSource();

        selectingTiles(tile);*/

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void selectingTiles(Tile tile)
    {

       if (first == null)
        {
            first = tile;
            first.setBorder(selected);
            return;
        }
        else if (tile == first)
        {
            first.setBorder(BorderFactory.createEmptyBorder());
            first = null;
            return;
        }

        second = tile;
     /*   first.setOutlierTile(false);
        second.setOutlierTile(false);
*//*        first.setZOrder();
        second.setZOrder();*//*
        remove(first);
        remove(second);
        first.setBorder(BorderFactory.createEmptyBorder());
        MahJong.removedTiles.addToScrollPane(first, second);
        first = second = null;
        startFireWorkReward();
        repaint();
        return;*/


        if(first.matches(second))
        {
            if(first.isOutlierTile() == true && second.isOutlierTile() == true)
            {
                if(soundOnOFF)
                {
                    clip.play();
                }

                first.setOutlierTile(false);
                second.setOutlierTile(false);
                first.setZOrder();
                remove(first);
                second.setZOrder();
                remove(second);
                first.removeMouseListener(this);
                second.removeMouseListener(this);

                remove(second);   first.setBorder(BorderFactory.createEmptyBorder());

                MahJong.removedTiles.addToScrollPane(first, second);
                first = second = null;
                startFireWorkReward();
                repaint();
                return;
            }

            else if(first.isOutlierTile() == true && second.isOutlierTile() == false)
            {
                if(soundOnOFF)
                {
                    clip.play();
                }
                first.setOutlierTile(false);
                first.setZOrder();
                remove(first);
                second.setZOrder();
                remove(second);
                first.removeMouseListener(this);
                second.removeMouseListener(this);


                first.setBorder(BorderFactory.createEmptyBorder());
                MahJong.removedTiles.addToScrollPane(first, second);
                mahJongModel.getTilesDataStructure()[second.getxInDataStruct()][second.getyInDataStruct()][second.getzInDataStruct()] = null;
                first = second = null;
                startFireWorkReward();
                repaint();
                return;
            }
            else if (first.isOutlierTile() == false && second.isOutlierTile() == true)
            {
                if(soundOnOFF)
                {
                    clip.play();
                }
                second.setOutlierTile(false);
                first.setZOrder();
                remove(first);
                second.setZOrder();
                remove(second);
                first.removeMouseListener(this);
                second.removeMouseListener(this);
                first.setBorder(BorderFactory.createEmptyBorder());
                MahJong.removedTiles.addToScrollPane(first, second);
                mahJongModel.getTilesDataStructure()[first.getxInDataStruct()][first.getyInDataStruct()][first.getzInDataStruct()] = null;
                first = second = null;
                startFireWorkReward();
                repaint();

                return;
            }
            else
            {
                if(soundOnOFF)
                {
                    clip.play();
                }
                first.setZOrder();
                remove(first);
                second.setZOrder();
                remove(second);
                first.removeMouseListener(this);
                second.removeMouseListener(this);

                first.setBorder(BorderFactory.createEmptyBorder());
                MahJong.removedTiles.addToScrollPane(first, second);
                mahJongModel.getTilesDataStructure()[first.getxInDataStruct()][first.getyInDataStruct()][first.getzInDataStruct()] = null;
                mahJongModel.getTilesDataStructure()[second.getxInDataStruct()][second.getyInDataStruct()][second.getzInDataStruct()] = null;
                first = second = null;
                startFireWorkReward();
                repaint();
                return;
            }
        }
        else
        {
            first.setBorder(BorderFactory.createEmptyBorder());
            first = second = null;
        }

        return;

    }

    public void undo()
    {

        if(MahJong.removedTiles.twoOrMoreTilesOnStack()) {

            ArrayList<Tile> tiles;
            tiles = MahJong.removedTiles.getNextTwoTiles();
            mahJongModel.getTilesDataStructure()[tiles.get(0).getxInDataStruct()][tiles.get(0).getyInDataStruct()][tiles.get(0).getzInDataStruct()] = tiles.get(0);
            mahJongModel.getTilesDataStructure()[tiles.get(1).getxInDataStruct()][tiles.get(1).getyInDataStruct()][tiles.get(1).getzInDataStruct()] = tiles.get(1);
            tiles.get(0).setLocation(tiles.get(0).getXLocation(), tiles.get(0).getYLocation());
            tiles.get(1).setLocation(tiles.get(1).getXLocation(), tiles.get(1).getYLocation());

            tiles.get(0).addMouseListener(this);

            add(tiles.get(0));
            setComponentZOrder(tiles.get(0), tiles.get(0).getZOrder());

            tiles.get(0).resetZOrder();

            tiles.get(1).addMouseListener(this);

            add(tiles.get(1));
            setComponentZOrder(tiles.get(1), tiles.get(1).getZOrder());

            tiles.get(1).resetZOrder();

            if(tiles.get(0).sortOrder == 100)
                tiles.get(0).setOutlierTile(true);
            if(tiles.get(1).sortOrder == 100)
                tiles.get(1).setOutlierTile(true);
            MahJong.removedTiles.removeFromScrollPane();
            repaint();

        }


    }

    private void drawTilesOnBoard()
    {

        //Set tile Location to be drawn on Board and add to arrayList to loop for Z ordering when adding to Jpanel.
        for (Tile[][] i : mahJongModel.getTilesDataStructure()) {
            for (Tile[] j : i) {
                for (Tile tile : j) {

                    if(!(tile == null)) {
                        tile.setLocation(tile.getXLocation(), tile.getYLocation());
                        tileArrayList.add(tile);

                    }
                }
            }
        }

        tileArrayList.sort(new CompareTiles());

        tileArrayLoopVariable = tileArrayList.size();

        mahJongModel.getTileArray().get(0).setLocation(mahJongModel.getTileArray().get(0).getXLocation(), mahJongModel.getTileArray().get(0).getYLocation());
        mahJongModel.getTileArray().get(0).addMouseListener(this);
        add(mahJongModel.getTileArray().get(0));

        mahJongModel.getTileArray().get(1).setLocation(mahJongModel.getTileArray().get(1).getXLocation(), mahJongModel.getTileArray().get(1).getYLocation());


        mahJongModel.getTileArray().get(2).setLocation(mahJongModel.getTileArray().get(2).getXLocation(), mahJongModel.getTileArray().get(2).getYLocation());


        mahJongModel.getTileArray().get(3).setLocation(mahJongModel.getTileArray().get(3).getXLocation(), mahJongModel.getTileArray().get(3).getYLocation());



        for(int i = 0; i < tileArrayLoopVariable; i++)
        {
            Tile t = tileArrayList.get(i);
            if(t.sortOrder == 86){
                t.addMouseListener(this);
                add(t);
                mahJongModel.getTileArray().get(1).addMouseListener(this);
                add(mahJongModel.getTileArray().get(1));

            }
            else if(t.sortOrder == 123)
            {
                t.addMouseListener(this);
                add(t);
                mahJongModel.getTileArray().get(2).addMouseListener(this);
                add(mahJongModel.getTileArray().get(2));
                mahJongModel.getTileArray().get(3).addMouseListener(this);
                add(mahJongModel.getTileArray().get(3));
            }
            else
            {
                t.addMouseListener(this);
                add(t);
            }
        }

    }

    public long getGameSeed() {
        return gameSeed;
    }


    public void setSoundONOFF(boolean onOff)
    {
        this.soundOnOFF = onOff;
    }

    public void startFireWorkReward()
    {
        if(MahJong.removedTiles.stackSize() == 144)
        {

            fireworks = new Fireworks(this);
            fireworks.setSound(soundOnOFF);
            fireworks.fire();
        }

    }

    public void stopFireWorkReward()
    {
        if(fireworks == null)
            return;

        fireworks.stop();
        fireworks = null;
    }

}


