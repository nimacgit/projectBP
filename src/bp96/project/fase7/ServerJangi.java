package bp96.project.fase7;
import ir.pint.soltoon.soltoongame.server.ServerRunner;
import ir.pint.soltoon.soltoongame.shared.GameConfiguration;
import ir.pint.soltoon.soltoongame.shared.map.Game;
import ir.pint.soltoon.soltoongame.shared.map.KhadangType;

/**
 * Created by amirkasra on 1/24/2018 AD.
 */
public class ServerJangi {
	public static void main(String[] args) {
		GameConfiguration.ROUNDS = 500;
		GameConfiguration.BOARD_HEIGHT=6;
		GameConfiguration.BOARD_WIDTH=49;
		GameConfiguration.PLAYERS_INITIAL_MONEY = 2000;
		GameConfiguration.PLAYERS_TURN_MONERY =100;
		ServerRunner.run();
	}

}
