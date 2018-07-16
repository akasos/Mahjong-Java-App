abstract public class RankTile extends Tile
{
    protected int rank;

    public RankTile(int rank)
    {
        this.rank = rank;
    }

    @Override
    public boolean matches(Tile otherObject)
    {
        if(super.matches(otherObject))
        {
            RankTile other = (RankTile)otherObject;
            return this.rank == other.rank;
        }
        else
        {
            return false;
        }

    }
}
