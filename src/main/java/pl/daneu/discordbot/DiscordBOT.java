package pl.daneu.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.daneu.discordbot.listeners.MessageReactionAddListener;
import pl.daneu.discordbot.listeners.SlashCommandListener;
import pl.daneu.discordbot.listeners.UserJoinGuildListener;

import javax.security.auth.login.LoginException;

public class DiscordBOT extends JavaPlugin {

    private JDA jda;

    @Override
    public void onEnable() {

        registerBOT();
    }

    @Override
    public void onDisable() { jda.shutdown(); }

    public JDA getJDA(){ return jda; }

    private void registerBOT(){
        try {
            jda = JDABuilder.createDefault("ODk4NTUyNDg1NDU3ODIxNzY2.YWl4Ng.FGeZczWZYWfF9x4DfuGmJ2IvysQ")
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        jda.updateCommands().addCommands(
                new CommandData("propozycja", "Propozycja dodania/zmienienia czegoś nowego na serwerze")
                        .addOption(OptionType.STRING, "tekst", "Wpisz swoją propozycję", true),

                new CommandData("blad", "Zgloś błąd na serwerze")
                        .addOption(OptionType.STRING, "opis-błędu", "Opisz błąd", true),

                new CommandData("bc", "Tworzy embedy")
                        .addOption(OptionType.STRING, "tytuł", "Wpisz tytuł swojego embedu", true)
                        .addOption(OptionType.STRING, "tekst", "Linijki embedu", true)
                        .addOption(OptionType.STRING, "url-zdjęcia", "Wstaw URL zdjęcia, które chcesz wrzucić"))
                .queue();

        jda.addEventListener(new SlashCommandListener(), new UserJoinGuildListener(), new MessageReactionAddListener());
    }
}
