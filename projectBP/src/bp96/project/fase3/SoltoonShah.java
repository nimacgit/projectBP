package bp96.project.fase3;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.*;



public class SoltoonShah extends Soltoon {

    Boolean isSet;
    @Override
    public void init(Game game) {
        isSet = false;
    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        GameSoltoon me = game.getSoltoon(getId());
        if(!isSet) {
            isSet = true;
            return new AddKhadang(new Shooter(KhadangType.GIANT), 0, 0);
        }
        return null;

    }
}
