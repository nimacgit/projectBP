package bp96.project.fase7;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.*;

import java.util.*;

/**
 * Created by amirkasra on 2/3/2018 AD.
 */
public class Scenario6Soltoon extends Soltoon {
	int[][] rank;
	int h,w;
	GameSoltoon me;

	Queue <ToBeAdded> todo;

	Queue <AttackingKhadang> attackers;

	@Override
	public void init(Game game) {
		todo = new LinkedList();
		attackers = new LinkedList<>();
	}

	@Override
	public void lastThingsToDo(Game game) {

	}

	private void sendTroops() {
		todo = new LinkedList();
		while (!attackers.isEmpty()) {
			AttackingKhadang x = attackers.poll();
			x.attack();
		}
	}

	@Override
	public Action getAction(Game game) {
		if (game.getCurrentRound()==0) return null;
		fillRank(game);

		if (!todo.isEmpty()) {
			ToBeAdded x = todo.poll();
			if (me.getMoney()<x.type.getCost()) {
				sendTroops();
				return null;
			}
			AttackingKhadang gorzi = new AttackingKhadang(x.type,this);
			attackers.add(gorzi);
			return new AddKhadang(gorzi,x.x,x.y);
		} else sendTroops();

		Set<Cell>locations = getLocations(game);
		ArrayList<Cell> edges = new ArrayList<>();
		for (Cell c : locations)
			for (int i=0;i<4;i++) {
				Cell adj = game.getCell(c,Direction.get(i));
				if (adj!=null && (!locations.contains(adj) || adj.getKhadang()!=null))
					edges.add(c);
			}
		for (Cell c:edges)
			todo.add(new ToBeAdded(game.getCurrentRound()%2==0?KhadangType.MUSKETEER:KhadangType.GIANT,c.getX(),c.getY()));
		return null;
	}

	private Set<Cell> getLocations(Game game) {
		Set<Cell> ans = new HashSet<>();
		for (int i=0;i<w;i++)
			for (int j=0;j<h;j++) {
				boolean flag = true;
				Cell m = game.getCell(i,j);
				for (Cell c : getFrame(game, m, KhadangType.CASTLE.getShootingRange()))
					if (!c.getKhadang().getOwner().equals(me)) {
						if (c.getKhadang().getType().getShootingRange() >= c.getDistance(m)) {
							flag = false;
						}
					}
				if (flag) ans.add(m);
			}
		return ans;
	}

	public static ArrayList<Cell> getFrame(Game game, Cell cell, int rad) {
		int x = cell.getX();
		int y = cell.getY();
		int h = game.getMapHeight();
		int w = game.getMapWidth();
		ArrayList<Cell> ans = new ArrayList<>();
		for (int i=Math.max(0,x-rad);i<=Math.min(w-1,x+rad);i++)
			for (int j=Math.max(0,y-rad);j<=Math.min(h-1,y+rad);j++)
				if (game.getCell(i,j).getKhadang()!=null)
					ans.add(game.getCell(i,j));
		return ans;
	}

	private void fillRank(Game g) {
		h = g.getMapHeight(); w = g.getMapWidth();
		rank = new int[w][h];
		me = g.getSoltoon(getId());
		boolean[][] mark = new boolean[w][h];
		Queue<Cell> q = new LinkedList<>();
		for (int i=0;i<w;i++)
			for (int j=0;j<h;j++) {
				GameKhadang x = g.getCell(i, j).getKhadang();
				if (x != null && !x.getOwner().equals(me)) {
					q.add(x.getCell());
					mark[x.getCell().getX()][x.getCell().getY()] = true;
				}
			}
		while (!q.isEmpty()) {
			Cell c = q.poll();
			for (int d = 0;d<4;d++) {
				Cell adj = g.getCell(c, Direction.get(d));
				if (adj!=null && !mark[adj.getX()][adj.getY()]) {
					mark[adj.getX()][adj.getY()] = true;
					q.add(adj);
					rank[adj.getX()][adj.getY()] = rank[c.getX()][c.getY()]+1;
				}
			}
		}



	}

	public int getRank(int x,int y) { return rank[x][y];}
}
