// A simple bot made in JDA 4 for HackUNT
// NOTE (for Eclipse users at least): MUST TERMINATE PROGRAM after running it to prevent glitches

package HackUNT.Discord.bot;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TicTacToeCommand extends Command {

	private static User player1;
	private static User player2;
	private final EventWaiter waiter;

	public TicTacToeCommand(EventWaiter waiter) {
		this.waiter = waiter;
		this.name = "tictactoe";
		this.aliases = new String[] { "ttt" };
		String space = "                         ";
		this.help = "Play Tic Tac Toe against your friends!\n" + space + "Command Syntax: \"!tictactoe @user\"";

	}

	public void runGame() {

	}

	@Override
	protected void execute(CommandEvent event) {
		player1 = event.getMessage().getAuthor();

		try {
			player2 = event.getMessage().getMentionedUsers().get(0);
		}
		catch (Exception e) {
			event.reply("ERROR: Must specify a user to play against!");
		}

		TicTacToe game = new TicTacToe();
		boolean gameOver = (game.getStatus() == 4) || (game.getStatus() == 5) || (game.getStatus() == 3);
		synchronized () {
			while (!gameOver && player2 != null) {

				String player1id = player1.getId();
				String player2id = player2.getId();

				if (game.getStatus() == 1) {
					event.reply("<@" + player1id + ">, it's your turn!");
					event.reply("Enter in a row and column (format r,c):");

					waiter.waitForEvent(MessageReceivedEvent.class,
							e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()),
							e -> {
								try {
									int row = Integer.parseInt(e.getMessage().getContentRaw().substring(0, 1));
									int col = Integer.parseInt(e.getMessage().getContentRaw().substring(2));
									game.placePiece(row, col);
									String board = game.printBoard();
									event.reply(board);

								}
								catch (Exception ex) {
									event.reply("ERROR: Invalid Format!");
								}

							}, 1L, TimeUnit.MINUTES, () -> event.reply("You Took too long!"));

				}
				else {
					event.reply("<@" + player2id + ">, it's your turn!");
					event.reply("Enter in a row and column (format r,c):");
					waiter.waitForEvent(MessageReceivedEvent.class,
							e -> e.getAuthor().equals(player2) && e.getChannel().equals(event.getChannel()), e -> {
								try {
									int row = Integer.parseInt(e.getMessage().getContentRaw().substring(0, 1));
									int col = Integer.parseInt(e.getMessage().getContentRaw().substring(2));
									game.placePiece(row, col);
									String board = game.printBoard();
									event.reply(board);
								}
								catch (Exception ex) {
									event.reply("ERROR: Invalid Format!");
								}

							}, 1L, TimeUnit.MINUTES, () -> event.reply("You Took too long!"));
				}
				game.winner(game.getStatus());
				if (gameOver) {

				}
				game.changeTurns();
			}
		}
	}

}
