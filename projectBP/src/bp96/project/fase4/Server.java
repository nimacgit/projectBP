package bp96.project.fase4;

import ir.pint.soltoon.soltoongame.server.ServerRunner;
import ir.pint.soltoon.soltoongame.shared.GameConfiguration;

public class Server {
    public static void main(String[] args) {
        GameConfiguration.ROUNDS = 1000;
        GameConfiguration.DEFENCE_INITIAL_MONEY = 10000000;
        ServerRunner.runDefence();

    }
}