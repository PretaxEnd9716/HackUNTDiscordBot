// A simple bot made in JDA 4 for HackUNT
// NOTE (for Eclipse users at least): MUST TERMINATE PROGRAM after running it to prevent glitches

package HackUNT.Discord.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCommand extends Command
{
	
	public PingCommand() {
		this.name = "ping";
		this.help = "Ping Pong!";
		
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply("Pong!");
	}
	
	
}