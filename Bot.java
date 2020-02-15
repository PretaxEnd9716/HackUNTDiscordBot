package HackUNT.Discord.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
	public static void main(String[] args) throws Exception {
		JDA game = new JDABuilder("Njc4Mjg0MjI5MjMwMDY3NzE3.Xkgj9w.2LfdzD9lrtC_UYRHr8Lqv92btOM"
).addEventListeners(new MyListener()).build();
		
		
	}
}
