package bp96.project.fase1;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Khadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;


public class SoltoonTest extends Soltoon {

    public static ArrayList<Long> getNear(Cell cell, int range, Set all) {
        ArrayList<Long> nears = new ArrayList<Long>();
        Iterator iter = all.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Cell pos = ((GameKhadang) entry.getValue()).getCell();
            if (abs(cell.getX() - pos.getX()) + abs(cell.getY() - pos.getY()) <= range)
                nears.add(((GameKhadang) entry.getValue()).getId());
        }
        return nears;

    }

    @Override
    public void init(Game game) {

    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        GameSoltoon me = game.getSoltoon(getId());
        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            System.out.println(gKh.getId());
            System.out.println(gKh.getOwner().getId());
            System.out.println(gKh.getCell().getX() + " " + gKh.getCell().getY());
            System.out.println(gKh.getType());
            ArrayList<Long> nears = getNear(gKh.getCell(), gKh.getType().getShootingRange(), allKh);
            for (Long id : nears) {
                System.out.println(id);
            }
        }
        return null;
    }
}
