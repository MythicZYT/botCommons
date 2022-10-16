package me.PolakOn420.botcommons.messaging;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MessageConfigDefaults {
    public static final Function<Long, MessageConfig.Builder> DELETE_MESSAGE_AFTER_SECONDS = (secs) -> new MessageConfig.Builder().setSuccessAction(
        (message) -> message.delete()
            .reason("automatic remove")
            .queueAfter(secs, TimeUnit.SECONDS)
    );
}
