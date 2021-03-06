package bp96.project.fase5;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.Move;
import ir.pint.soltoon.soltoongame.shared.actions.Nothing;
import ir.pint.soltoon.soltoongame.shared.actions.Shoot;
import ir.pint.soltoon.soltoongame.shared.agents.Khadang;
import ir.pint.soltoon.soltoongame.shared.map.Direction;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import static bp96.project.UsefulMethod.*;


public class Shooter extends Khadang {
    public Shooter(KhadangType type) {
        super(type);
    }

    @Override
    public void init(Game game) {

    }

    @Override
    public void lastThingsToDo(Game game) {

    }

    @Override
    public Action getAction(Game game) {
        Long fKh = getNearestEnemy(game, game.getKhadang(getId()));
        if (fKh < 0)
            return new Nothing();
        else {
            //System.out.println(game.getKhadang(fKh).getCell().getX() + " , " + game.getKhadang(fKh).getCell().getY());
            if (dist(game.getKhadang(fKh).getCell(), game.getKhadang(getId()).getCell()) > game.getKhadang(getId()).getType().getShootingRange()) {
                //System.out.println("move yeah in : " + game.getKhadang(fKh).getCell().getX() + " " + game.getKhadang(fKh).getCell().getY());
                //System.out.println("hey : " + game.getKhadang(getId()).getCell().getY() + " " + game.getKhadang(getId()).getCell().getX() + " enemy : "+
                        //game.getKhadang(fKh).getCell().getY() + " " + game.getKhadang(fKh).getCell().getX() + " : " +
                        //getDirBfs(game, game.getKhadang(getId()).getCell(), game.getKhadang(fKh).getCell()));
                int gooddir = getDirBfs(game, game.getKhadang(getId()).getCell(), game.getKhadang(fKh).getCell());
                if (gooddir == 0) {
                    return new Move(Direction.UP);
                } else if (gooddir == 1) {
                    return new Move(Direction.RIGHT);
                } else if (gooddir == 2) {
                    return new Move(Direction.DOWN);
                } else if (gooddir == 3){
                    return new Move(Direction.LEFT);
                }
                else
                    return new Nothing();
            } else {
                //System.out.println("shoot yeah" + " : " + game.getKhadang(fKh).getCell().getX() + " " + game.getKhadang(fKh).getCell().getY());
                return new Shoot(game.getKhadang(fKh).getCell().getX(), game.getKhadang(fKh).getCell().getY());
            }
        }
    }
}
