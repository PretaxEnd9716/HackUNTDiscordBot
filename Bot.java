// A simple bot made in JDA 4 for HackUNT
// NOTE (for Eclipse users at least): MUST TERMINATE PROGRAM after running it to prevent glitches

package HackUNT.Discord.bot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
	public static void main(String[] args) throws Exception {
		JDA game = new JDABuilder("Njc4Mjg0MjI5MjMwMDY3NzE3.XkheVQ.SDCMBYCII_YkPystlI6Bfw8miO8").build();
		
		
		// Initialize Commands
		EventWaiter waiter = new EventWaiter();
		
		CommandClientBuilder client = new CommandClientBuilder();
		
		client.setOwnerId("678284229230067717");
		
		client.setPrefix("!");
		client.addCommands(new PingCommand(), new TicTacToeCommand(waiter));
		
		game.addEventListener(waiter);
		game.addEventListener(client.build());
		
		

	}
}
