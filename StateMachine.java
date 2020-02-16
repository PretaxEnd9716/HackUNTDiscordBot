import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StateMachine extends ListenerAdapter
{
    private final long playerOneID, playerTwoID;
    private TicTacToe game;
    private boolean gameOn;

    public StateMachine( User playerOne, User playerTwo, TicTacToe game) {
        this.playerOneID = playerOne.getIdLong();
        this.playerTwoID = playerTwo.getIdLong();
        gameOn = true;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        MessageChannel channel = event.getChannel();
        String content = event.getMessage().getContentRaw();

        //Prints board
        String board = game.printBoard();

        //Ends the game
        if(game.getStatus() == TicTacToe.ONE_WINS || game.getStatus() == TicTacToe.TWO_WINS || game.getStatus() == TicTacToe.TIE)
        {
            event.getJDA().removeEventListener(this);
            setGameOn(false);
        }

        //Gets the user input
        if (event.getAuthor().getIdLong() == playerOneID && content.length() == 3 && game.getStatus() == TicTacToe.ONE)
        {
            //Gets the row and column
            char rowC = content.charAt(0);
            char colC = content.charAt(2);
            int row = Integer.parseInt(String.valueOf(rowC));
            int col = Integer.parseInt(String.valueOf(colC));

            game.placePiece(row, col);
            game.winner(1);

            if(game.getStatus() != TicTacToe.ONE_WINS)
                channel.sendMessage(board).queue();
        }

        if (event.getAuthor().getIdLong() == playerOneID && game.getStatus() != TicTacToe.ONE)
            channel.sendMessage("Pleas Wait For Your Turn "  + event.getMember().getEffectiveName()).queue();

        if (event.getAuthor().getIdLong() == playerTwoID && content.length() == 3 && game.getStatus() == TicTacToe.TWO)
        {
            //Gets the row and column
            char rowC = content.charAt(0);
            char colC = content.charAt(2);
            int row = Integer.parseInt(String.valueOf(rowC));
            int col = Integer.parseInt(String.valueOf(colC));

            game.placePiece(row, col);
            game.winner(1);

            if(game.getStatus() != TicTacToe.TWO_WINS)
                channel.sendMessage(board).queue();
        }

        if (event.getAuthor().getIdLong() == playerTwoID && game.getStatus() != TicTacToe.TWO)
            channel.sendMessage("Pleas Wait For Your Turn " + event.getMember().getEffectiveName()).queue();
    }
}
