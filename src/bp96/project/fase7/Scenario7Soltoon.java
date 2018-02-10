package bp96.project.fase7;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import java.util.ArrayList;

/**
 * Created by amirkasra on 2/2/2018 AD.
 */
public class Scenario7Soltoon extends Soltoon {
	ArrayList<ToBeAdded> todo;

	@Override
	public void init(Game game) {
		todo = new ArrayList<>();
		int x=0,y=0;
		int base = 5;
		for (int i=0;i<=(game.getMapWidth()-1)/base;i++)
			for (int j=0;j<=(game.getMapHeight()-1)/base;j++)
				todo.add(new ToBeAdded(KhadangType.CANNON,i*base,j*base));
	}

	@Override
	public void lastThingsToDo(Game game) {

	}

	@Override
	public Action getAction(Game game) {
		if (todo.size()==0) return null;
		ToBeAdded a = todo.get(0);
		todo.remove(0);
		return new AddKhadang(new DefenciveKhadang(a.type),a.x,a.y);
	}
}