package pl.daneu.discordbot.listeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageReactionAddListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        if(!event.getChannel().getId().equalsIgnoreCase("916108966042427393")) return;

        if(event.getReactionEmote().getEmoji().equalsIgnoreCase("✅")
                || event.getReactionEmote().getEmoji().equalsIgnoreCase("❌")) return;

        event.getTextChannel().removeReactionById(event.getMessageId(), event.getReactionEmote().getEmoji(), event.getUser()).queue();
    }

}
