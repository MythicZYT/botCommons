package me.PolakOn420.botcommons;

import me.PolakOn420.botcommons.web.WebUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.sharding.ShardManager;

public class BotCommons {

    public static final String VERSION = "@version@";

    /**
     * Kills all the threads that BotCommons uses internally, allowing your bot to shut own without using System.exit
     *
     * @param manager Your {@link ShardManager ShardManager} instance for killing the threads that JDA does not shut down and keep your bot up
     */
    public static void shutdown(ShardManager manager) {
        manager.shutdown();
        manager.getShardCache().forEach((jda) -> {
            jda.getHttpClient().connectionPool().evictAll();
            jda.getHttpClient().dispatcher().executorService().shutdown();
        });
        shutdown();
    }

    /**
     * Kills all the threads that BotCommons uses internally, allowing your bot to shut own without using System.exit
     *
     * @param jda Your {@link JDA JDA} instance for killing the threads that JDA does not shut down and keep your bot up
     */
    public static void shutdown(JDA jda) {
        jda.shutdown();
        jda.getHttpClient().connectionPool().evictAll();
        jda.getHttpClient().dispatcher().executorService().shutdown();
        shutdown();
    }

    /**
     * Kills all the threads that BotCommons uses internally, allowing your bot to shut own without using System.exit
     */
    public static void shutdown() {
        try {
            WebUtils.ins.shutdown();
        } catch (Exception e) {
            e.printStackTrace(); // should never happen but just in case
        }
    }
}
