package bp96.project.fase7;

import ir.pint.soltoon.soltoongame.shared.GameConfiguration;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.GameKhadang;
import ir.pint.soltoon.soltoongame.shared.map.GameSoltoon;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static bp96.project.UsefulMethod.mergeSort;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import static bp96.project.UsefulMethod.isInRange;
import static bp96.project.UsefulMethod.nextPosZigZag;



public class SoltoonShahtest extends Soltoon {

    private ArrayList<Action> jobs = new ArrayList<Action>();
    private ArrayList<Integer> costs = new ArrayList<Integer>();
    private ArrayList<Integer> posX = new ArrayList<Integer>();
    private ArrayList<Integer> posY = new ArrayList<Integer>();
    private int curround = 0;
    private int [][]ml;
    private int mlCanon = 8;
    private int mlCastle = 12;
    private int thresh = 7;
    private int threshCastlef = 8;
    private int treshMusCastlef = 4;

    void makeJob(Game game)
    {
        int[] arr = new int[game.getMapHeight()*game.getMapWidth()];
        int[] inds = new int[game.getMapHeight()*game.getMapWidth()];
        jobs.clear();
        posX.clear();
        posY.clear();
        costs.clear();
        for(int i = 0; i < game.getMapHeight(); i++)
        {
            for(int j = 0; j < game.getMapWidth(); j++)
            {
                inds[i * game.getMapWidth() + j] =i * game.getMapWidth() + j;
                arr[i * game.getMapWidth() + j] = ml[i][j];
            }
        }

        mergeSort(0, game.getMapHeight()*game.getMapWidth(), arr, inds);
        for(int i = 0; i < game.getMapHeight()*game.getMapWidth(); i++) {
            if(arr[i] >= threshCastlef)
            {
                jobs.add(new AddKhadang(new CastleF(KhadangType.GIANT), inds[i]%game.getMapWidth(), inds[i]/game.getMapWidth()));
                posX.add(inds[i]%game.getMapWidth());
                posY.add(inds[i]/game.getMapWidth());
                costs.add(KhadangType.GIANT.getCost());
            }
            else if(arr[i] >= thresh)
            {
                jobs.add(new AddKhadang(new Shooter(KhadangType.GIANT), inds[i]%game.getMapWidth(), inds[i]/game.getMapWidth()));
                posX.add(inds[i]%game.getMapWidth());
                posY.add(inds[i]/game.getMapWidth());
                costs.add(KhadangType.GIANT.getCost());
            }
            else if(arr[i] >= treshMusCastlef)
            {
                jobs.add(new AddKhadang(new CastleF(KhadangType.MUSKETEER), inds[i]%game.getMapWidth(), inds[i]/game.getMapWidth()));
                posX.add(inds[i]%game.getMapWidth());
                posY.add(inds[i]/game.getMapWidth());
                costs.add(KhadangType.MUSKETEER.getCost());
            }
            else if(arr[i] > 0)
            {
                jobs.add(new AddKhadang(new Shooter(KhadangType.MUSKETEER), inds[i]%game.getMapWidth(), inds[i]/game.getMapWidth()));
                posX.add(inds[i]%game.getMapWidth());
                posY.add(inds[i]/game.getMapWidth());
                costs.add(KhadangType.MUSKETEER.getCost());
            }
        }

    }



    void makeMLArr(Game game, long id)
    {
        for(int i = 0; i < game.getMapWidth(); i++)
            for(int j = 0; j < game.getMapHeight(); j++)
                ml[j][i] = 0;
        Iterator iter = game.getKhadangs().entrySet().iterator();
        while (iter.hasNext()) {
            GameKhadang gKh = (GameKhadang)((Map.Entry) iter.next()).getValue();
            int khX = gKh.getCell().getX(), khY = gKh.getCell().getY();
            int goodThresh;
            switch(gKh.getType())
            {
                case CANNON:
                    goodThresh = mlCanon;
                    break;
                case CASTLE:
                    goodThresh = mlCastle;
                    break;
                    default:
                        goodThresh = 5;

            }
            if(game.getOwner(gKh.getId()).getId() != id)
            {
                for(int i = max(0, khY - goodThresh); i < min(game.getMapHeight(), khY + goodThresh); i++)
                {
                    int wid = abs(abs(i - khY) - goodThresh);
                    for(int j = max(0, khX - wid); j < min(game.getMapWidth(), khX + wid); j++)
                    {
                        ml[i][j] += abs(abs(khX - j) + abs(i - khY) - goodThresh);
                    }
                }
            }
        }
        for(int i = 0; i < game.getMapHeight(); i++)
        {
            for(int j = 0; j < game.getMapWidth(); j++)
            {
                System.out.print(ml[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println(" ");
    }


    @Override
    public void init(Game game) {

        //GameSoltoon me = game.getSoltoon(getId());
        ml = new int[game.getMapHeight()][game.getMapWidth()];






    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        if(curround != game.getCurrentRound()) {
            curround = game.getCurrentRound();
            makeMLArr(game, getId());
            makeJob(game);
        }
        for (int i = 0; i < jobs.size(); i++) {
            if (costs.get(i) <= game.getSoltoon(this.getId()).getMoney() && !game.getCell(posX.get(i), posY.get(i)).hasKhadang() && !isInRange(game, posX.get(i), posY.get(i), getId())) {
                //System.out.println("ki");
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
