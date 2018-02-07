package bp96.project.fase5;

import bp96.project.UsefulMethod;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.Cell;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.GameSoltoon;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;
import static bp96.project.UsefulMethod.*;

import java.util.ArrayList;


public class SoltoonShah extends Soltoon {

    ArrayList<Action> jobs = new ArrayList<Action>();
    ArrayList<Integer> costs = new ArrayList<Integer>();
    ArrayList<Integer> posX = new ArrayList<Integer>();
    ArrayList<Integer> posY = new ArrayList<Integer>();

    @Override
    public void init(Game game) {

        GameSoltoon me = game.getSoltoon(getId());
        int x = game.getMapWidth()/2, y = game.getMapHeight()/2;
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT),1 , 7));
        posX.add(1);
        posY.add(7);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT),1 , 8));
        posX.add(1);
        posY.add(8);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT),1 , 6));
        posX.add(1);
        posY.add(6);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 13, 7));
        posX.add(13);
        posY.add(7);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 13, 8));
        posX.add(13);
        posY.add(8);
        costs.add(KhadangType.GIANT.getCost());
//        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 14, 6));
//        posX.add(14);
//        posY.add(6);
//        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 7, 1));
        posX.add(7);
        posY.add(1);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 8, 1));
        posX.add(8);
        posY.add(1);
        costs.add(KhadangType.GIANT.getCost());
//        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 6, 0));
//        posX.add(6);
//        posY.add(0);
//        costs.add(KhadangType.GIANT.getCost());

        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 7, 13));
        posX.add(7);
        posY.add(13);
        costs.add(KhadangType.GIANT.getCost());
        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 8, 13));
        posX.add(8);
        posY.add(13);
        costs.add(KhadangType.GIANT.getCost());
//        jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), 6, 14));
//        posX.add(6);
//        posY.add(14);
//        costs.add(KhadangType.GIANT.getCost());


        addShooterLineUR(0, 3, 4,jobs,costs,KhadangType.GIANT, posX, posY);
        addShooterLineUR(10, 15, 6,jobs,costs,KhadangType.GIANT, posX, posY);
        //addShooterLineDR(8, 13, 2,jobs,costs,KhadangType.GIANT);
        addShooterLineDR(11, 0, 5,jobs,costs,KhadangType.GIANT, posX, posY);
        addShooterLineDR(0, 11, 5,jobs,costs,KhadangType.GIANT, posX, posY);


        addShooterLineU(13, 8, 3,jobs,costs,KhadangType.MUSKETEER, posX, posY);

        addShooterLineU(1, 8, 3,jobs,costs,KhadangType.MUSKETEER, posX, posY);

        addShooterLineR(6, 13, 3,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addShooterLineR(6, 1, 3,jobs,costs,KhadangType.MUSKETEER, posX, posY);

        addCasstleFLineR(0, 7, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);

        addShooterLineU(13, 8, 3,jobs,costs,KhadangType.GIANT, posX, posY);
        //addShooterLineU(14, 9, 5,jobs,costs,KhadangType.GIANT, posX, posY);
        addShooterLineU(1, 8, 3,jobs,costs,KhadangType.GIANT, posX, posY);
        //addShooterLineU(15, 9, 5,jobs,costs,KhadangType.GIANT);

        addShooterLineR(6, 13, 3,jobs,costs,KhadangType.GIANT, posX, posY);
        //addShooterLineR(5, 14, 5,jobs,costs,KhadangType.GIANT, posX, posY);
        addShooterLineR(6, 1, 3,jobs,costs,KhadangType.GIANT, posX, posY);
        // addShooterLineR(5, 15, 5,jobs,costs,KhadangType.GIANT);

        addCasstleFLineR(0, 8, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 6, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 5, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 9, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 4, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 3, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 10, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);
        addCasstleFLineR(0, 11, 15,jobs,costs,KhadangType.MUSKETEER, posX, posY);



    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {

        for (int i = 0; i < jobs.size(); i++) {
            if (costs.get(i) <= game.getSoltoon(this.getId()).getMoney() && !game.getCell(posX.get(i), posY.get(i)).hasKhadang() && !isInRange(game, posX.get(i), posY.get(i), getId())) {
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
