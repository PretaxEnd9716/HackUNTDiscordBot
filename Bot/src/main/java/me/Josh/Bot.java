package me.Josh;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    private Bot() throws LoginException {
        JDA api = new JDABuilder()
                .setToken("Njc4Mjk4NDIyNDc3ODQ4NjEz.Xkgx9g.kHnoboaPPenM01n74qac5B7W7r0")
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("HENTAI GAMES"))
                .build();
    }
    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
