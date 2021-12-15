package pl.daneu.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;

public class UserJoinGuildListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        String userMention =  event.getMember().getUser().getAsMention();
        String channelMention = event.getGuild().getTextChannelById("916109614813151323").getAsMention();
        int totalUsers = event.getJDA().getUsers().size() + 1;

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setAuthor("Witaj " + event.getMember().getUser().getName() + " \uD83D\uDC4B", null, event.getUser().getAvatarUrl())
                .setDescription("Witamy " + userMention + " na discordzie serwera **TRENINGPVP.PL** \n" +
                        "Odbierz darmowa nagrode za dolaczenie - " + channelMention)
                .setFooter("Jestes nasza " + totalUsers + " osoba na serwerze! Zyczymy udanej zabawy!", event.getJDA().getSelfUser().getAvatarUrl())
                .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.decode("#FF8000"));

        event.getJDA().getTextChannelById("916108966042427393").sendMessageEmbeds(embedBuilder.build()).queue();
    }

}
