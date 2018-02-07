package send;


import bp96.project.fase5.CastleF;
import bp96.project.fase5.Shooter;
import ir.pint.soltoon.soltoongame.shared.actions.Action;
import ir.pint.soltoon.soltoongame.shared.actions.AddKhadang;
import ir.pint.soltoon.soltoongame.shared.map.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;

public class UsefulMethod {

    public static int[] tempArr = new int[500];
    public static int[] tempInds = new int[500];
    public static ArrayList<Long> getNearInRange(Cell cell, int range, Set all) {
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

    public static int dist(Cell a, Cell b) {
        return abs(a.getX() - b.getX()) + abs(a.getY() - b.getY());
    }

    public static int dist(GameKhadang a, GameKhadang b) {
        return dist(a.getCell(), b.getCell());
    }

    public static Direction getDir(Cell s, Cell f) {
        if(s.getY() > f.getY())
        {
            return Direction.UP;
        }
        else if(s.getY() < f.getY())
        {
            return Direction.DOWN;
        }
        else if(s.getX() > f.getX())
        {
            return Direction.LEFT;
        }
        else
        {
            return Direction.RIGHT;
        }
    }

    public static int goodforBfs(int x1, int y1, int x2, int y2, int mindis)
    {
            if(abs(x2-x1) + abs(y2-y1) < mindis)
                return abs(x2-x1) + abs(y2-y1);
            return -1;
    }
    public static boolean isInRange(Game game, int x, int y, long id)
    {
        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            if(game.getOwner(gKh.getId()).getId() != id)
            {
                if(dist(gKh.getCell(), game.getCell(x,y)) <= gKh.getType().getShootingRange())
                    return true;
            }
        }
        return false;
    }

    public static int getDirBfs(Game game, Cell s, Cell f) {
       // System.out.println(" s : " + s.getY() + " " + s.getX());
        //System.out.println(" f : " + f.getY() + " " + f.getX());
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        ArrayList<Integer> dir = new ArrayList<Integer>();
        int gooddir = -1, mindis = 1000000;
        boolean[][] seen = new boolean[game.getMapHeight()][game.getMapWidth()];
        for (int i = 0; i < game.getMapHeight(); i++)
            for (int j = 0; j < game.getMapWidth(); j++)
                seen[i][j] = false;

        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            seen[gKh.getCell().getY()][gKh.getCell().getX()] = true;
        }

        seen[s.getY()][s.getX()] = true;
        if (s.getY() < game.getMapHeight() - 1 && !seen[s.getY()+1][s.getX()]) {
            x.add(s.getX());
            y.add(s.getY() + 1);
            dir.add(2);
            seen[s.getY() + 1][s.getX()] = true;
            if(goodforBfs(f.getX(), f.getY(), s.getX(), s.getY() + 1, mindis) > -1) {
                mindis = goodforBfs(f.getX(), f.getY(), s.getX(), s.getY() + 1, mindis);
                gooddir = 2;
            }
        }
        if (s.getX() < game.getMapWidth() - 1 && !seen[s.getY()][s.getX() + 1]) {
            x.add(s.getX() + 1);
            y.add(s.getY());
            dir.add(1);
            seen[s.getY()][s.getX() + 1] = true;
            if(goodforBfs(f.getX(), f.getY(), s.getX() + 1, s.getY(), mindis) > -1) {
                mindis = goodforBfs(f.getX(), f.getY(), s.getX() + 1, s.getY(), mindis);
                gooddir = 1;
            }
        }
        if (s.getY() > 0 && !seen[s.getY() - 1][s.getX()]) {
            x.add(s.getX());
            y.add(s.getY() - 1);
            dir.add(0);
            seen[s.getY() - 1][s.getX()] = true;
            if(goodforBfs(f.getX(), f.getY(), s.getX(), s.getY() - 1, mindis) > -1) {
                mindis = goodforBfs(f.getX(), f.getY(), s.getX(), s.getY() - 1, mindis);
                gooddir = 0;
            }
        }
        if (s.getX() > 0 && !seen[s.getY()][s.getX() - 1]) {
            x.add(s.getX() - 1);
            y.add(s.getY());
            dir.add(3);
            seen[s.getY()][s.getX() - 1] = true;
            if(goodforBfs(f.getX(), f.getY(), s.getX() - 1, s.getY(), mindis) > -1) {
                mindis = goodforBfs(f.getX(), f.getY(), s.getX() - 1, s.getY(), mindis);
                gooddir = 3;
            }
        }
        seen[f.getY()][f.getX()] = false;

        int curr = 0, tx = 0, ty = 0;
        if(x.size() > curr) {
            tx = x.get(curr);
            ty = y.get(curr);
        }
        while ( curr < x.size() - 1 && (tx != f.getX() || ty != f.getY())) {
            if (ty < game.getMapHeight() - 1 && !seen[ty + 1][tx]) {
                x.add(tx);
                y.add(ty + 1);
                dir.add(dir.get(curr));
                seen[ty + 1][tx] = true;
                if(goodforBfs(f.getX(), f.getY(), tx, ty + 1, mindis) > -1) {
                    mindis = goodforBfs(f.getX(), f.getY(), tx, ty + 1, mindis);
                    gooddir = dir.get(curr);
                }
            }
            if (tx < game.getMapWidth() - 1 && !seen[ty][tx + 1]) {
                x.add(tx + 1);
                y.add(ty);
                dir.add(dir.get(curr));
                seen[ty][tx + 1] = true;
                if(goodforBfs(f.getX(), f.getY(), tx+1, ty, mindis) > -1) {
                    mindis = goodforBfs(f.getX(), f.getY(), tx + 1, ty, mindis);
                    gooddir = dir.get(curr);
                }
            }
            if (ty > 0 && !seen[ty - 1][tx]) {
                x.add(tx);
                y.add(ty - 1);
                dir.add(dir.get(curr));
                seen[ty - 1][tx] = true;
                if(goodforBfs(f.getX(), f.getY(), tx, ty - 1, mindis) > -1) {
                    mindis = goodforBfs(f.getX(), f.getY(), tx, ty - 1, mindis);
                    gooddir = dir.get(curr);
                }
            }
            if (tx > 0 && !seen[ty][tx - 1]) {
                x.add(tx - 1);
                y.add(ty);
                dir.add(dir.get(curr));
                seen[ty][tx - 1] = true;
                if(goodforBfs(f.getX(), f.getY(), tx - 1, ty , mindis) > -1) {
                    mindis = goodforBfs(f.getX(), f.getY(), tx - 1, ty, mindis);
                    gooddir = dir.get(curr);
                }
            }
            curr++;
            tx = x.get(curr);
            ty = y.get(curr);
        }
        /*
        System.out.println("dirs : ");
        for (int i = 0; i < dir.size(); i++)
            System.out.print(dir.get(i) + " ");
        System.out.println();
        System.out.println("y : ");
        for (int i = 0; i < dir.size(); i++)
            System.out.print(y.get(i) + " ");
        System.out.println();
        System.out.println("x : ");
        for (int i = 0; i < dir.size(); i++)
            System.out.print(x.get(i) + " ");
        System.out.println();
*/

       return gooddir;

    }


    public static Long getNearestEnemy(Game game, GameKhadang Kh) {
        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();
        int mindis = 100000;
        Long fKh = -1L;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            if (gKh.getOwner().getId() != Kh.getOwner().getId()) {
                if (dist(gKh.getCell(), Kh.getCell()) < mindis) {
                    mindis = dist(gKh.getCell(), Kh.getCell());
                    fKh = gKh.getId();
                }
            }
        }
        return fKh;
    }

    public static Long getNearestEnemyInRangesh(Game game, GameKhadang Kh) {
        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();
        int mindis = 100000;
        Long fKh = -1L;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            if (gKh.getOwner().getId() != Kh.getOwner().getId()) {
                if (dist(gKh.getCell(), Kh.getCell()) < mindis && dist(gKh.getCell(), Kh.getCell()) <= gKh.getType().getShootingRange()) {
                    mindis = dist(gKh.getCell(), Kh.getCell());
                    fKh = gKh.getId();
                }
            }
        }
        return fKh;
    }

    public static Long getNearestEnemyType(Game game, GameKhadang Kh, KhadangType khadType)
    {
        Set allKh = game.getKhadangs().entrySet();
        Iterator iter = allKh.iterator();
        int mindis = 100000;
        Long fKh = -1L;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            GameKhadang gKh = (GameKhadang) entry.getValue();
            if (gKh.getOwner().getId() != Kh.getOwner().getId() && gKh.getType().equals(khadType)) {
                if (dist(gKh.getCell(), Kh.getCell()) < mindis) {
                    mindis = dist(gKh.getCell(), Kh.getCell());
                    fKh = gKh.getId();
                }
            }
        }
        return fKh;
    }

    public static void makeBlocked(Game game, int[][] world)
    {

    }
    public static void addShooterLineUR(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new Shooter(khadType),x + i,y - i));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x+i);
            posY.add(y-i);
        }
    }
    public static void addShooterLineDR(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new Shooter(khadType),x + i,y + i));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x+i);
            posY.add(y+i);
        }
    }
    public static void addShooterLineR(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new Shooter(khadType),x + i,y));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x+i);
            posY.add(y);
        }
    }
    public static void addShooterLineU(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new Shooter(khadType),x,y-i));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x);
            posY.add(y-i);
        }
    }

    public static void addCasstleFLineU(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new CastleF(khadType),x,y-i));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x);
            posY.add(y-i);
        }
    }

    public static void addCasstleFLineR(int x, int y,int len, ArrayList<Action> jobs, ArrayList<Integer> costs, KhadangType khadType, ArrayList<Integer> posX, ArrayList<Integer> posY)
    {
        for(int i = 0; i < len; i++)
        {
            jobs.add(new AddKhadang(new CastleF(khadType),x + i,y));
            costs.add(KhadangType.GIANT.getCost());
            posX.add(x + i);
            posY.add(y);
        }
    }


    public static int[] nextPosZigZag(int x, int y, int maxX, int maxY)
    {
        int[] a = new int[2];
        if(x == 0)
        {
            if(y == 0)
            {
                x = maxX;
                y = maxY-1;
            }
            else {
                x = maxY - y + 1;
                y = maxY;
            }
        }
        else if(y == 0)
        {
            y = maxY - 1 - x;
            x = maxX;
        }
        else
        {
            x--;
            y--;
        }
        a[0] = x;
        a[1] = y;
        return a;
    }

    public static void mergeSort(int s, int e, int[] arr, int[] inds)
    {
        if(e - s < 2)
            return;
        int mid = (s+e)/2, i = 0, j = 0;
        mergeSort(s, mid, arr, inds);
        mergeSort(mid, e, arr, inds);
        while(i < mid - s || j < e - mid)
        {
            if(i == mid - s) {
                tempArr[j+i] = arr[mid + j];
                tempInds[j+i] = inds[mid + j];
                j++;
            }
            else if(j == e - mid) {
                tempArr[i+j] = arr[s + i];
                tempInds[i+j] = inds[s + i];
                i++;
            }
            else
            {
                if(arr[s+i] > arr[mid+j])
                {
                    tempArr[i+j] = arr[s+i];
                    tempInds[i+j] = inds[s+i];
                    i++;
                }
                else
                {
                    tempArr[i+j] = arr[mid+j];
                    tempInds[i+j] = inds[mid+j];
                    j++;
                }
            }
        }
        i = s;
        j = 0;
        while(i < e)
        {
            inds[i] = tempInds[j];
            arr[i] = tempArr[j];
            i++;
            j++;
        }
    }


}
