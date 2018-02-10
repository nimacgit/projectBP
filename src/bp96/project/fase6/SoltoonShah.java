package bp96.project.fase6;

import ir.pint.soltoon.soltoongame.shared.GameConfiguration;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.GameSoltoon;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import java.util.ArrayList;

import static bp96.project.UsefulMethod.*;


public class SoltoonShah extends Soltoon {

    private ArrayList<Action> jobs = new ArrayList<Action>();
    private ArrayList<Integer> costs = new ArrayList<Integer>();
    private ArrayList<Integer> posX = new ArrayList<Integer>();
    private ArrayList<Integer> posY = new ArrayList<Integer>();


    void jobMaker(GameSoltoon me, int MAXX, int MAXY)
    {

        int maxX = MAXX - 1, maxY = MAXY - 1;
        int mon = me.getMoney();
        int x = 0, y = maxY;
        int[] posArr = new int[2];
        while( mon > mon % 1000 + 2000)
        {
            jobs.add(new AddKhadang(new Shooter(KhadangType.CASTLE), x, y));
            posX.add(x);
            posY.add(y);
            costs.add(KhadangType.CASTLE.getCost());
            mon -= KhadangType.CASTLE.getCost();
            posArr = nextPosZigZag(x,y, maxX, maxY);
            x = posArr[0];
            y = posArr[1];
        }
        while(mon >= KhadangType.CANNON.getCost())
        {
            jobs.add(new AddKhadang(new Shooter(KhadangType.CANNON), x, y));
            posX.add(x);
            posY.add(y);
            costs.add(KhadangType.CANNON.getCost());
            mon -= KhadangType.CANNON.getCost();
            posArr = nextPosZigZag(x,y, maxX, maxY);
            x = posArr[0];
            y = posArr[1];
            System.out.println(x + " : " + y);
        }



    }
    @Override
    public void init(Game game) {





    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        if(jobs.size() == 0)
            jobMaker(game.getSoltoon(getId()), game.getMapWidth(),game.getMapHeight());
        for (int i = 0; i < jobs.size(); i++) {
            if (costs.get(i) <= game.getSoltoon(this.getId()).getMoney() && !game.getCell(posX.get(i), posY.get(i)).hasKhadang() && !isInRange(game, posX.get(i), posY.get(i), getId())) {
                System.out.println("ki");
                Action temp = jobs.get(i);
                jobs.remove(i);
                costs.remove(i);
                posX.remove(i);
                posY.remove(i);
                return temp;
            }
        }
        return null;
    }
}
