import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CharacterTile extends Tile
{
    protected char symbol;

    public static HashMap<Character,Character> engChinMapping = new HashMap<>();

    static
    {
        engChinMapping.put('1', '\u4E00');
        engChinMapping.put('2', '\u4E8C');
        engChinMapping.put('3', '\u4E09');
        engChinMapping.put('4', '\u56DB');
        engChinMapping.put('5', '\u4E94');
        engChinMapping.put('6', '\u516D');
        engChinMapping.put('7', '\u4E03');
        engChinMapping.put('8', '\u516B');
        engChinMapping.put('9', '\u4E5D');
        engChinMapping.put('N', '\u5317');
        engChinMapping.put('E', '\u6771');
        engChinMapping.put('W', '\u897F');
        engChinMapping.put('S', '\u5357');
        engChinMapping.put('C', '\u4E2D');
        engChinMapping.put('F', '\u767C');
        engChinMapping.put('A', '\u842C');

    }

    public CharacterTile(char symbol)
    {

        this.symbol = symbol;
        this.setToolTipText(toString());
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        FontMetrics fm;

        //length of various symbols for centering
        int widSymbol;
        int widKey;
        int wan;


        for (Character c : engChinMapping.keySet())
        {
            //Draw Key
            if(c.charValue() == this.symbol )
            {
                Font f = g.getFont();
                f = f.deriveFont(f.getSize2D() * 0.9F);
                g.setFont(f);
                fm = g.getFontMetrics();
                widKey = fm.charWidth(c.charValue());
                g.setColor(Color.RED);
                g.drawString(Character.toString(c), ((48 - widKey) / 2) + 25, 15);

                //Symbol, N,E,S,W, Red, Green
                if(c.charValue() > '9')
                {
                    f = f.deriveFont(f.getSize2D() * 3.3F);
                    g.setFont(f);
                    fm = g.getFontMetrics();
                    widSymbol = fm.charWidth(engChinMapping.get(symbol));
                    if(c.charValue() =='C')
                    {
                        g.setColor(Color.RED);
                        g.drawString(Character.toString(engChinMapping.get(symbol)), ((48 - widSymbol) / 2) + 10, 48);
                    }
                    else if (c.charValue() == 'F')
                    {
                        g.setColor(new Color(0, 153, 0));
                        g.drawString(Character.toString(engChinMapping.get(symbol)), ((48 - widSymbol) / 2) + 10, 48);
                    }
                    else
                    {
                        g.setColor(Color.BLACK);
                        g.drawString(Character.toString(engChinMapping.get(symbol)), ((48 - widSymbol) / 2) + 10, 48);

                    }
                }

                else
                {
                    //Draw Chines Symbol for the values 1, 2, 3, 4,
                    f = f.deriveFont(f.getSize2D() * 1.5F);
                    g.setFont(f);
                    fm = g.getFontMetrics();
                    widSymbol = fm.charWidth(engChinMapping.get(symbol));
                    g.setColor(Color.BLACK);
                    g.drawString(Character.toString(engChinMapping.get(symbol)), ((48 - widSymbol) / 2) + 10, 20);
                }

                //Draws Wan
                if(c.charValue() <= '9')
                {
                    f = f.deriveFont(f.getSize2D()*1.8F);
                    g.setFont(f);
                    fm = g.getFontMetrics();
                    wan = fm.charWidth(engChinMapping.get('A'));
                    g.setColor(Color.RED);
                    g.drawString(Character.toString(engChinMapping.get('A')), ((48 - wan) / 2) + 10, 50);
                }
            }
        }

    }


    public static void main(String[] args)
    {
        JFrame		frame = new JFrame();
        JPanel		tiles = new JPanel();
        JScrollPane	scroller = new JScrollPane(tiles);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Character Tiles");
        frame.add(scroller);

        // Try something like this if your tiles don't fit on the screen.
        // Replace "tile width" and "tile height" with your values.
        //scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));
        tiles.add(new CharacterTile('1'));
        tiles.add(new CharacterTile('2'));
        tiles.add(new CharacterTile('3'));
        tiles.add(new CharacterTile('4'));
        tiles.add(new CharacterTile('5'));
        tiles.add(new CharacterTile('6'));
        tiles.add(new CharacterTile('7'));
        tiles.add(new CharacterTile('8'));
        tiles.add(new CharacterTile('9'));
        tiles.add(new CharacterTile('N'));
        tiles.add(new CharacterTile('E'));
        tiles.add(new CharacterTile('W'));
        tiles.add(new CharacterTile('S'));
        tiles.add(new CharacterTile('C'));
        tiles.add(new CharacterTile('F'));

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public boolean matches(Tile otherObject)
    {
        if (super.matches(otherObject))
        {
            CharacterTile other = (CharacterTile) otherObject;
            return this.symbol == other.symbol;
        } else {
            return false;
        }
    }



  @Override
    public String toString()
    {
        if (this.symbol >= '1' && this.symbol <= '9')
        {
            return "Character " + this.symbol;
        }
        else
        {
            switch (this.symbol) {
                case 'N':
                    return "North Wind";

                case 'E':
                    return "East Wind";
                case 'W':
                    return "West Wind";

                case 'S':
                    return "South Wind";

                case 'C':
                    return "Red Dragon";

                case 'F':
                    return "Green Dragon";

                    default:
                        return "We have a problem";
            }

        }

    }
}
