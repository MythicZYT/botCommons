package me.PolakOn420.botcommons.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.internal.utils.Checks;

import java.util.List;

/**
 * Provides a basic command context that should be sufficient for most bots
 */
public class DefaultCommandContext implements ICommandContext {
    private final GuildMessageReceivedEvent event;
    private final List<String> args;

    public DefaultCommandContext(List<String> args, GuildMessageReceivedEvent event) {
        Checks.notNull(event, "event");
        Checks.notNull(args, "args");

        this.args = args;
        this.event = event;
    }

    public List<String> getArgs() {
        return this.args;
    }

    @Override
    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }
}
