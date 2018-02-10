package bp96.project.fase6;

import ir.pint.soltoon.soltoongame.server.ServerRunner;
import ir.pint.soltoon.soltoongame.shared.GameConfiguration;

public class Server {
    public static void main(String[] args) {
        GameConfiguration.ROUNDS = 500;
        GameConfiguration.DEFENCE_INITIAL_MONEY = 100000;
        ServerRunner.run();

    }
}