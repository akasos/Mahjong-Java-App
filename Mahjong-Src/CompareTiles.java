import java.util.Comparator;
public class CompareTiles implements Comparator<Tile>
{
    public int compare(Tile t1, Tile t2)
    {
       return t1.sortOrder - t2.sortOrder;
    }
}
