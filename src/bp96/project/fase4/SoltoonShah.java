package bp96.project.fase4;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.GameSoltoon;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import java.util.ArrayList;


public class SoltoonShah extends Soltoon {

    ArrayList<Action> jobs = new ArrayList<Action>();
    int currjob = 0;
    ArrayList<Integer> costs = new ArrayList<Integer>();

    @Override
    public void init(Game game) {

        GameSoltoon me = game.getSoltoon(getId());
        int x = game.getMapWidth(), y = game.getMapHeight();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4 - i; j++) {
                jobs.add(new AddKhadang(new Shooter(KhadangType.CANNON), i, y-1-j));
                costs.add(KhadangType.CANNON.getCost());
            }
        }

        //jobs.add(new AddKhadang(new Shooter(KhadangType.CASTLE), x-1, y-1));
        //costs.add(KhadangType.CASTLE.getCost());

        jobs.add(new AddKhadang(new Shooter(KhadangType.CANNON), 3, y-2));
        costs.add(KhadangType.CANNON.getCost());
        jobs.add(new AddKhadang(new Shooter(KhadangType.CANNON), 2, y-3));
        costs.add(KhadangType.CANNON.getCost());
        jobs.add(new AddKhadang(new Shooter(KhadangType.CANNON), 1, y-4));
        costs.add(KhadangType.CANNON.getCost());



    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        if (currjob < jobs.size()) {
            if (costs.get(currjob) <= game.getSoltoon(this.getId()).getMoney()) {
                currjob++;
                return jobs.get(currjob - 1);
            }
        }
        return null;
    }
}
