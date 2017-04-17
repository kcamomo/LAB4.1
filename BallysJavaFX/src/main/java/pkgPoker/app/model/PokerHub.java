package pkgPoker.app.model;

import java.io.IOException;

import netgame.common.Hub;
import pkgPoker.app.MainApp;
import pkgPokerBLL.Action;
import pkgPokerBLL.GamePlay;
import pkgPokerBLL.Player;
import pkgPokerBLL.Rule;
import pkgPokerBLL.Table;

public class PokerHub extends Hub {

	private Table HubPokerTable = new Table();
	private GamePlay HubGamePlay;
	private int iDealNbr = 0;

	public PokerHub(int port) throws IOException {
		super(port);
	}

	protected void playerConnected(int playerID) {

		if (playerID == 2) {
			shutdownServerSocket();
		}
	}

	protected void playerDisconnected(int playerID) {
		shutDownHub();
	}

	protected void messageReceived(int ClientID, Object message) {

		if (message instanceof Action) {
			Player actPlayer = (Player) ((Action) message).getPlayer();
			Action act = (Action) message;
			switch (act.getAction()) {
			case Sit:
				HubPokerTable.AddPlayerToTable(actPlayer.getPlayerID(), actPlayer);
				MainApp.getPlayer().setiPlayerPosition(1);			
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case Leave:
				HubPokerTable.RemovePlayerFromTable(actPlayer.getPlayerID());
				MainApp.getPlayer().setiPlayerPosition(0);
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case TableState:
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case StartGame:
				// Get the rule from the Action object.
				Rule rle = new Rule(act.geteGame());
				
				//TODO Lab #5 - If neither player has 'the button', pick a random player
				//		and assign the button.				

				//TODO Lab #5 - Start the new instance of GamePlay
								
				// Add Players to Game
				
				// Set the order of players
				


			case Draw:

				//TODO Lab #5 -	Draw card(s) for each player in the game.
				//TODO Lab #5 -	Make sure to set the correct visiblity
				//TODO Lab #5 -	Make sure to account for community cards

				//TODO Lab #5 -	Check to see if the game is over
				HubGamePlay.isGameOver();
				
				resetOutput();
				//	Send the state of the gameplay back to the clients
				sendToAll(HubGamePlay);
				break;
			case ScoreGame:
				// Am I at the end of the game?

				resetOutput();
				sendToAll(HubGamePlay);
				break;
			}
			
		}

	}

}