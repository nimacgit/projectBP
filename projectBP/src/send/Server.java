package send;

import ir.pint.soltoon.soltoongame.server.ServerRunner;
import ir.pint.soltoon.soltoongame.shared.GameConfiguration;

public class Server {
    public static void main(String[] args) {
        GameConfiguration.ROUNDS = 200;
        GameConfiguration.PLAYERS_INITIAL_MONEY = 5000;
        ServerRunner.run();

    }
}