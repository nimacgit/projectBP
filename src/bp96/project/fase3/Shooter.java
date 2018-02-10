package bp96.project.fase3;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.Move;
import ir.pint.soltoon.soltoongame.shared.actions.Nothing;
import ir.pint.soltoon.soltoongame.shared.actions.Shoot;
import ir.pint.soltoon.soltoongame.shared.agents.Khadang;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import static bp96.project.UsefulMethod.dist;
import static bp96.project.UsefulMethod.getDir;
import static bp96.project.UsefulMethod.getNearestEnemy;


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
        if (fKh == -1)
            return new Nothing();
        else {

            if (dist(game.getKhadang(fKh).getCell(), game.getKhadang(getId()).getCell()) > game.getKhadang(getId()).getType().getShootingRange()) {


                return new Move(getDir(game.getKhadang(getId()).getCell(),game.getKhadang(fKh).getCell()));
            } else {

                return new Shoot(game.getKhadang(fKh).getCell().getX(), game.getKhadang(fKh).getCell().getY());
            }
        }
    }
}
