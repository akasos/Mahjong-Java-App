import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MahJongModel
{
    static final int ROWS = 8;
    static final int COLS = 12;
    static final int LAYERS = 4;

    private Tile t;

    private int sortOrderCount;

    private long seed;
    private Random rand;
    private boolean restart;

    private Tile[][][] tilesDataStructure;
    private ArrayList<Tile> tileArray;


    public MahJongModel()
    {
        tilesDataStructure = new Tile[ROWS][COLS][LAYERS];

        tileArray = new ArrayList<>();

        rand = new Random();

        seed = System.currentTimeMillis() % 1000000;

        rand.setSeed(seed);

        addTilesToArray();

        Collections.shuffle(tileArray, rand);

        addingTileToThreeDimenonalArray();



    }

    public MahJongModel(long seed)
    {

        tilesDataStructure = new Tile[ROWS][COLS][LAYERS];

        tileArray = new ArrayList<>();

        rand = new Random();

        rand.setSeed(seed);

        addTilesToArray();

        Collections.shuffle(tileArray, rand);

        addingTileToThreeDimenonalArray();

    }

    //position each tile once it is instantiated
    void positionTile(Tile t, int x, int y, int z, int order)
    {
        t.setXLocation(x);
        t.setYLocation(y);
        t.setLayerLocation(z);
        t.setSortOrder(order);
    }

    void tilesPostionInDataStruct(Tile t, int x, int y, int z)
    {
        t.setxInDataStruct(x);
        t.setyInDataStruct(y);
        t.setzInDataStruct(z);

    }


    //Check to see if a tile is open.
    public boolean isTileOpen(int x, int y, int z)
    {
        if (y == 0 || y == 11 || z == 3)
            return true;

  /*      System.out.println(tilesDataStructure[x][y - 1][z]);
        System.out.println(tilesDataStructure[x][y + 1][z]);*/
        if(tilesDataStructure[x][y][z + 1] == null)
        {
            if(tilesDataStructure[x][y - 1][z] == null || tilesDataStructure[x][y + 1][z] == null)
            {
                return true;
            }
        }
      return false;

    }

    private void addTilesToArray()
    {
        //adding 1-9 Character tiles * 4
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                tileArray.add(new CharacterTile((char) (j + 48)));
            }
        }

        //adding Wind tiles, Red Dragon, Green Dragon
        for (int i = 0; i < 4; i++) {

            tileArray.add(new CharacterTile('N'));
            tileArray.add(new CharacterTile('E'));
            tileArray.add(new CharacterTile('S'));
            tileArray.add(new CharacterTile('W'));
            tileArray.add(new CharacterTile('C'));
            tileArray.add(new CharacterTile('F'));
        }

        //adding Bamboo tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j == 1) {
                    tileArray.add(new Bamboo1Tile());
                } else {
                    tileArray.add(new BambooTile(j));
                }
            }
        }

        //adding Circle tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                tileArray.add(new CircleTile(j));
            }
        }

        //adding WhiteDragon tiles
        for (int i = 0; i < 4; i++) {
            tileArray.add(new WhiteDragonTile());
        }

        //adding Flower tiles
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    tileArray.add(new FlowerTile("Chrysanthemum"));
                    break;

                case 1:
                    tileArray.add(new FlowerTile("Orchid"));
                    break;

                case 2:
                    tileArray.add(new FlowerTile("Plum"));
                    break;

                case 3:
                    tileArray.add(new FlowerTile("Bamboo"));
                    break;
            }

        }

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    tileArray.add(new SeasonTile("Spring"));
                    break;

                case 1:
                    tileArray.add(new SeasonTile("Summer"));
                    break;

                case 2:
                    tileArray.add(new SeasonTile("Fall"));
                    break;

                case 3:
                    tileArray.add(new SeasonTile("Winter"));
                    break;
            }

        }

    }

    private void addingTileToThreeDimenonalArray()
    {
        //adding tiles to 3-Dimensional Array.

        //Bottom layer 0
        for(int i = 0; i < ROWS; i++)
        {
            if(i == 0)
                sortOrderCount = 132;
            else if(i == 1)
                sortOrderCount = 124;
            else if( i == 2)
                sortOrderCount = 114;
            else if( i == 3)
                sortOrderCount = 100;
            else if(i == 4)
                sortOrderCount = 88;
            else if(i == 5)
                sortOrderCount = 77;
            else if(i == 6)
                sortOrderCount = 69;
            else if( i == 7)
                sortOrderCount = 57;
            if(i == 0)
            {
                for (int j = 0; j < 12; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);


                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;

                }
            }
            else if(i == 1)
            {
                for(int j = 2; j < 10; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }
            else if(i == 2)
            {

                for(int j = 1; j < 11; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }

            else if(i == 3)
            {
                for(int j = 0; j < 12; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t,(j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }

            else if (i == 4)
            {
                for(int j = 0; j < 12; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }

            else if (i == 5)
            {
                for(int j = 1; j < 11; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t,(j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }

            }

            else if(i == 6)
            {
                for(int j = 2; j < 10; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }

            else if(i == 7)
            {
                for(int j = 0; j < 12; j++)
                {
                    t = tileArray.remove(tileArray.size() - 1);

                    positionTile(t, (j * Tile.WIDTH) + 48, (i * Tile.HEIGHT), 0, sortOrderCount);

                    tilesPostionInDataStruct(t, i, j, 0);

                    tilesDataStructure[i][j][0] = t;

                    sortOrderCount++;
                }
            }

        }
        //Layer 1
        for(int i = 1; i < 7; i++)
        {
            if(i == 1)
                sortOrderCount = 51;
            else if(i == 2)
                sortOrderCount = 45;
            else if( i == 3)
                sortOrderCount = 39;
            else if( i == 4)
                sortOrderCount = 33;
            else if(i == 5)
                sortOrderCount = 27;
            else if(i == 6)
                sortOrderCount = 21;
            for(int j = 3; j < 9; j++)
            {
                t = tileArray.remove(tileArray.size() - 1);

                positionTile(t, (j * Tile.WIDTH + Tile.EDGE) + 48, (i * Tile.HEIGHT - Tile.EDGE ), 1, sortOrderCount);

                tilesPostionInDataStruct(t, i, j, 1);

                tilesDataStructure[i][j][1] = t;

                sortOrderCount++;
            }

        }

        //layer 2
        for(int i = 2; i < 6; i++)
        {
            if(i == 2)
                sortOrderCount = 17;
            else if(i == 3)
                sortOrderCount = 13;
            else if( i == 4)
                sortOrderCount = 9;
            else if( i == 5)
                sortOrderCount = 5;

            for(int j = 4; j < 8; j++)
            {
                t = tileArray.remove(tileArray.size() - 1);

                positionTile(t, (j * Tile.WIDTH + 2 * Tile.EDGE) + 48, (i * Tile.HEIGHT - 2 * Tile.EDGE), 2, sortOrderCount);

                tilesPostionInDataStruct(t, i, j, 2);

                tilesDataStructure[i][j][2] = t;

                sortOrderCount++;

            }
        }
        //layer 3
        for(int i = 3; i < 5; i++)
        {
            if(i == 3)
                sortOrderCount = 3;
            else
                sortOrderCount = 1;

            for(int j = 5; j < 7; j++)
            {
                t = tileArray.remove(tileArray.size() - 1);

                positionTile(t, (j * Tile.WIDTH + 3 * Tile.EDGE) + 48, (i * Tile.HEIGHT - 3 * Tile.EDGE), 3, sortOrderCount);

                tilesPostionInDataStruct(t, i, j, 3);

                tilesDataStructure[i][j][3] = t;
                sortOrderCount++;

            }
        }

        //Position the 4 left over tiles.
        for( int i = 0; i < tileArray.size(); i++)
        {
            switch(i)
            {
                case 0:
                    positionTile(tileArray.get(0), 356, 171, 10, 100);
                    tileArray.get(0).setOutlierTile(true);
                    break;
                case 1:
                    positionTile(tileArray.get(1), 0, 210, 10, 100);
                    tileArray.get(1).setOutlierTile(true);
                    break;
                case 2:
                    positionTile(tileArray.get(2), 623, 210, 10, 100);
                    tileArray.get(2).setOutlierTile(true);
                    break;
                case 3:
                    positionTile(tileArray.get(3), 671, 210, 10, 100);
                    tileArray.get(3).setOutlierTile(true);
                    break;

            }
        }
    }


    public Tile[][][] getTilesDataStructure() {
        return tilesDataStructure;
    }

    public ArrayList<Tile> getTileArray() {
        return tileArray;
    }

    public long getSeed(){ return seed;}


}


