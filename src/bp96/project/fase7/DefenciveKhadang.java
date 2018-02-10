package bp96.project.fase7;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.Shoot;
import ir.pint.soltoon.soltoongame.shared.agents.Khadang;
import ir.pint.soltoon.soltoongame.shared.map.Cell;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.GameKhadang;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by amirkasra on 2/2/2018 AD.
 */
public class DefenciveKhadang extends Khadang {
	GameKhadang me;
	int h,w;

	public DefenciveKhadang(KhadangType type) {
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
		me = game.getKhadang(getId());
		h = game.getMapHeight();
		w = game.getMapWidth();
		if (me.getRemainingReloadingTime()!=0) return null;
		Deque<GameKhadang> targets = getTargetsList(game);
		if (targets.isEmpty() || me.getRemainingReloadingTime()>0) return null;
		GameKhadang ans = targets.getFirst();
		targets.removeFirst();
		while (!targets.isEmpty() && targets.getFirst().getType().equals(KhadangType.GIANT)) {
			GameKhadang x = targets.getFirst();
			targets.removeFirst();
			int distance = me.getCell().getDistance(x.getCell());
			if (distance<me.getCell().getDistance(ans.getCell())) ans = x;
			else
				if (distance==me.getCell().getDistance(ans.getCell()) && x.getHealth()<ans.getHealth())
					ans = x;
		}
		return new Shoot(ans.getCell().getX(),ans.getCell().getY());
	}

	private Deque<GameKhadang> getTargetsList(Game game) {
		Deque <GameKhadang> dq = new ArrayDeque<>();
		int range = me.getType().getShootingRange();
		for (Cell c: Scenario6Soltoon.getFrame(game,me.getCell(),range)) {
				if (c.getDistance(me.getCell())>range) continue;
				GameKhadang z = c.getKhadang();
				if (z!=null && z.getOwner()!=me.getOwner()) {
					if (z.getType().equals(KhadangType.MUSKETEER)) dq.addLast(z);
					else dq.addFirst(z);
				}
		}
		return dq;
	}
}
