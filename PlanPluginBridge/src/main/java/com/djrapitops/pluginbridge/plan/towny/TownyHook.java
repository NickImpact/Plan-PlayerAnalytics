package com.djrapitops.pluginbridge.plan.towny;

import com.djrapitops.plan.data.plugin.HookHandler;
import com.djrapitops.pluginbridge.plan.Hook;

/**
 * A Class responsible for hooking to Towny and registering 2 data sources.
 *
 * @author Rsl1122
 * @since 3.1.0
 */
public class TownyHook extends Hook {

    /**
     * Hooks the plugin and registers it's PluginData objects.
     *
     * API#addPluginDataSource uses the same method from HookHandler.
     *
     * @param hookH HookHandler instance for registering the data sources.
     * @throws NoClassDefFoundError when the plugin class can not be found.
     */
    public TownyHook(HookHandler hookH) {
        super("com.palmergames.bukkit.towny.Towny", hookH);
    }

    public void hook() throws NoClassDefFoundError {
        if (enabled) {
            addPluginDataSource(new TownyData());
        }
    }
}
