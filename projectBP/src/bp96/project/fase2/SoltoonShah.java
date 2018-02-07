package bp96.project.fase2;

import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.agents.Soltoon;
import ir.pint.soltoon.soltoongame.shared.map.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;


public class SoltoonShah extends Soltoon {

    public static ArrayList<Long> getNear(Cell cell, int range, Set all) {
        ArrayList<Long> nears = new ArrayList<Long>();
        Iterator iter = all.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Cell pos = ((GameKhadang) entry.getValue()).getCell();
            if (abs(cell.getX() - pos.getX()) + abs(cell.getY() - pos.getY()) <= range)
                nears.add(((GameKhadang) entry.getValue()).getId());
        }
        return nears;

    }
    int x, y;
    static ArrayList<Integer> jobsX;
    static ArrayList<Integer> jobsY;
    static ArrayList<Boolean> done;

    public static void lineX(int fx, int fy, int sx, int sy)
    {
        int len = sx - fx + 1;
        for(int i = 0; i < len; i++) {
            jobsX.add(fx + i);
            jobsY.add(fy);
        }
    }
    public static void lineY(int fx, int fy, int sx, int sy)
    {
        int len = sy - fy + 1;
        for(int i = 0; i < len; i++) {
            jobsX.add(fx);
            jobsY.add(fy + i);
        }
    }
    public static void lineM(int fx, int fy, int sx, int sy)
    {
        int len = sx - fx + 1;
        int sgn = 1;
        if(fy > sy)
            sgn = -1;
        for(int i = 0; i < len; i++) {
            jobsX.add(fx + i);
            jobsY.add(fy + sgn*i);
        }
    }

    @Override
    public void init(Game game) {
        jobsX = new  ArrayList<Integer>();
        jobsY = new  ArrayList<Integer>();
        done = new ArrayList<Boolean>();
        System.out.println("HiY");
        int X0 = 5, Y0 = 12;
        int x0 = X0, y0 = Y0;
        int l = 5;
        lineY(x0, y0, x0, y0 + l);
        lineM(x0, y0, x0 + l, y0 + l);
        lineY(x0 + l, y0, x0 + l, y0 + l);

        x0 = x0 + l + 2;
        lineX(x0, y0, x0 + l /2, y0);
        lineY(x0 + l/4, y0, x0 + l /4, y0 + l);
        lineX(x0, y0 + l, x0 + l /2, y0 + l);

        x0 = x0 + l/2 + 2;
        lineY(x0, y0, x0, y0 + l);
        lineM(x0, y0, x0 + l, y0 + l);
        x0 = x0 + l;
        lineM(x0, y0 + l, x0 + l, y0);
        lineY(x0 + l, y0, x0 + l, y0 + l);

        x0 = x0 + l + 2;
        lineY(x0, y0, x0, y0 + l);
        lineY(x0 + l / 2, y0, x0 + l / 2, y0 + l);
        lineX(x0, y0, x0 + l /2, y0);
        lineX(x0, y0 + l/2, x0 + l /2, y0 + l/2);

        x0 = x0 + l / 2 + 2;
        lineX(x0, y0, x0 + l /2, y0);
        lineX(x0, y0 + l, x0 + l /2, y0 + l);
        lineY(x0, y0, x0, y0 + l);

        x0 = X0;
        y0 = Y0 + 2;
        lineX(x0 , y0 + l, x0 + 4*l+8 + l/ 2, y0 + l);
        y0 = y0 + 2;
        lineX(x0 + l , y0 + l, x0 + 3*l+8 + l/ 2, y0 + l);
        y0 = y0 + 2;
        lineX(x0 + 2*l , y0 + l, x0 + 2*l+8 + l/2 , y0 + l);

    }

    @Override
    public void lastThingsToDo(Game game) {

    }


    @Override
    public Action getAction(Game game) {
        GameSoltoon me = game.getSoltoon(getId());
        int l = done.size();
        System.out.println("Hi" + jobsX.size());
        while(l < jobsX.size())
        {
            done.add(true);
            if(!game.getCell(jobsX.get(l), jobsY.get(l)).hasKhadang()) {
                System.out.println(jobsX.get(l) + " " + jobsY.get(l));
                return new AddKhadang(new Shooter(KhadangType.CASTLE), jobsX.get(l), jobsY.get(l));
            }
            else
                System.out.println("has khan");
            l = done.size();
        }
        return null;

    }
}
