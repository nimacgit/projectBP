package bp96.project.fase7;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.Move;
import ir.pint.soltoon.soltoongame.shared.actions.Shoot;
import ir.pint.soltoon.soltoongame.shared.agents.Khadang;
import ir.pint.soltoon.soltoongame.shared.map.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by amirkasra on 2/2/2018 AD.
 */
public class AttackingKhadang extends Khadang{
	int h,w;
	GameKhadang me;
	Scenario6Soltoon dad;
	private boolean standby = true;

	public AttackingKhadang(KhadangType type, Scenario6Soltoon dad) {
		super(type);
		this.dad = dad;
	}

	@Override
	public void init(Game game) {
		h = game.getMapHeight();
		w = game.getMapWidth();
	}

	@Override
	public void lastThingsToDo(Game game) {

	}

	@Override
	public Action getAction(Game game) {
		me = game.getKhadang(getId());
		if (standby) return null;

		if (me.getRemainingReloadingTime()==0) {
			int range = me.getType().getShootingRange();
			for (Cell c: Scenario6Soltoon.getFrame(game,me.getCell(),range)) {
					if (c.getDistance(me.getCell())>range) continue;
					GameKhadang z = c.getKhadang();
					if (c.getKhadang().getOwner()!=me.getOwner())
						return new Shoot(z.getCell().getX(),z.getCell().getY());
			}
		}

		boolean[][] mark = new boolean[w][h];
		Cell[][] par = new Cell[w][h];
		Queue<Cell> q = new LinkedList<>();
		q.add(me.getCell());
		mark[me.getCell().getX()][me.getCell().getY()] = true;
		while (!q.isEmpty()) {
			Cell c = q.poll();
			if (dad.getRank(c.getX(),c.getY())<=me.getType().getShootingRange()) {
				Cell t = c;
				if (t==me.getCell()) continue;
				while (par[t.getX()][t.getY()]!=me.getCell())
					t = par[t.getX()][t.getY()];

				for (int i=0;i<4;i++)
					if (t== game.getCell(me.getCell(),Direction.get(i)))
						return new Move(Direction.get(i));
			}
			for (int i=0;i<4;i++) {
				Cell w = game.getCell(c,Direction.get(i));
				if (w!=null && w.getKhadang()==null && !mark[w.getX()][w.getY()]) {
					mark[w.getX()][w.getY()] = true;
					q.add(w);
					par[w.getX()][w.getY()] = c;
				}
			}
		}

		return null;
	}

	public void attack() {
		standby = false;
	}
}
