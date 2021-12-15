package pl.daneu.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@Nonnull SlashCommandEvent event) {

        // PROPOZYCJA COMMAND
        if(event.getName().equalsIgnoreCase("propozycja")) {
            if(!event.getChannel().getId().equalsIgnoreCase("916108966042427393")){

                String correctChannel = event.getJDA().getTextChannelById("916108966042427393").getAsMention();
                event.reply("Nie możesz pisać tej komendy na tym kanale. Pisz na -> " + correctChannel)
                        .setEphemeral(true)
                        .queue();
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            String userTag = event.getUser().getAsTag();
            String message = event.getOptions().get(0).getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setAuthor(userTag, null, event.getUser().getAvatarUrl())
                    .setDescription("Propozycja: " + message)
                    .setFooter("TreningPVP.pl ● " + dateFormat.format(new Date()), event.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.decode("#FF8000"));

            event.getInteraction()
                    .reply("Stworzyłeś swoją propozycję!")
                    .queue(privateMessage -> privateMessage.deleteOriginal().queueAfter(10, TimeUnit.SECONDS));

            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue(embed -> {
                embed.addReaction("✅").submit();
                embed.addReaction("❌").submit();
            });

            return;
        }

        // BLAD COMMAND
        if(event.getName().equalsIgnoreCase("blad")) {
            if(!event.getChannel().getId().equalsIgnoreCase("916108966042427393")){

                String correctChannel = event.getJDA().getTextChannelById("916108966042427393").getAsMention();
                event.reply("Nie możesz pisać tej komendy na tym kanale. Pisz na -> " + correctChannel)
                        .setEphemeral(true)
                        .queue();
                return;
            }

            String userName = event.getUser().getName();
            String userMention = event.getUser().getAsMention();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss | dd/MM/yyyy");

            String message = event.getOptions().get(0).getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setAuthor("Zgłoszenie użytkownika " + userName, null, event.getUser().getAvatarUrl())
                    .setDescription(message)
                    .setFooter("TreningPVP.pl ● " + dateFormat.format(new Date()), event.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.decode("#FF8000"));

            event.getInteraction()
                    .reply(userMention + " Wysłałeś zgłoszenie! Teraz poczekaj cierpliwie, aż Administracja rozpatrzy twoją sprawę!")
                    .queue(privateMessage -> privateMessage.deleteOriginal().queueAfter(10, TimeUnit.SECONDS));

            event.getJDA().getTextChannelById("916109614813151323").sendMessageEmbeds(embedBuilder.build()).queue();

            return;
        }

        // BC COMMAND
        if(event.getName().equalsIgnoreCase("bc")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss | dd/MM/yyyy");

            String text = event.getOptions().get(1).getAsString().replaceAll("\\|", "\n");
            String title = event.getOptions().get(0).getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder();

            if(event.getOptions().size() == 3 && !event.getOptions().get(2).getAsString().equalsIgnoreCase("")) {
                embedBuilder = new EmbedBuilder()
                        .setAuthor(title)
                        .setDescription(text)
                        .setFooter("TreningPVP.pl ● " + dateFormat.format(new Date()), event.getJDA().getSelfUser().getAvatarUrl())
                        .setImage(event.getOptions().get(2).getAsString())
                        .setColor(Color.decode("#FF8000"));
            }else{
                embedBuilder.clear();

                embedBuilder.setAuthor(title)
                        .setDescription(text)
                        .setFooter("TreningPVP.pl ● " + dateFormat.format(new Date()), event.getJDA().getSelfUser().getAvatarUrl())
                        .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                        .setColor(Color.decode("#FF8000"));
            }

            event.getInteraction()
                    .reply("Stworzyłeś swój embed!")
                    .queue(privateMessage -> privateMessage.deleteOriginal().queueAfter(10, TimeUnit.SECONDS));

            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
    }

}
