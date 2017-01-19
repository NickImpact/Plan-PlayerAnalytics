package com.djrapitops.plan.data.handlers;

import com.djrapitops.plan.Plan;
import com.djrapitops.plan.PlanLiteHook;
import com.djrapitops.plan.data.UserData;
import com.djrapitops.plan.data.cache.DataCacheHandler;
import com.djrapitops.plan.utilities.FormatUtils;
import com.djrapitops.planlite.api.DataPoint;
import java.util.HashMap;
import java.util.Set;
import main.java.com.djrapitops.plan.data.PlanLitePlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Rsl1122
 */
public class PlanLiteHandler {

    private Plan plugin;
    private PlanLiteHook hook;
    private DataCacheHandler handler;
    private boolean enabled;

    /**
     * Class Constructor.
     *
     * @param plugin Current instance of Plan
     */
    public PlanLiteHandler(Plan plugin) {
        this.plugin = plugin;
        PlanLiteHook planLiteHook = plugin.getPlanLiteHook();
        enabled = planLiteHook.isEnabled();
        if (enabled) {
            hook = planLiteHook;
        }
    }

    /**
     * Handles the player login by getting the data of the player from planlite.
     *
     * @param event LoginEvent
     * @param data UserData for the player.
     */
    public void handleLogin(PlayerJoinEvent event, UserData data) {
        if (!enabled) {
            data.setPlanLiteFound(false);
            return;
        }
        Player p = event.getPlayer();
        String playerName = p.getName();

        handleEvents(playerName, data);
    }

    /**
     * Places the data to the PlanLitePlayerData and saves that to UserData.
     *
     * If PlanLite is used as UI the additional data of PlanLite has to be
     * disabled to avoid stackoverflow.
     *
     * @param playerName Name of the Player
     * @param data UserData for the player.
     */
    public void handleEvents(String playerName, UserData data) {
        if (!enabled) {
            return;
        }
        Set<String> enabledHooks = hook.getEnabledHooksNames();
        PlanLitePlayerData plData = new PlanLitePlayerData();

        // Avoiding StackOverFlow
        if (plugin.getConfig().getBoolean("Settings.PlanLite.UseAsAlternativeUI")
                && plugin.getPlanLiteHook().isEnabled()) {
            data.setPlanLiteFound(false);
            plData.setTowny(false);
            plData.setFactions(false);
            plData.setSuperbVote(false);
            plData.setVault(false);
            data.setPlanLiteData(plData);
        } else {
            HashMap<String, DataPoint> liteData = hook.getAllData(playerName, true);

            plData.setTowny(enabledHooks.contains("Towny"));
            plData.setFactions(enabledHooks.contains("Factions"));
            plData.setSuperbVote(enabledHooks.contains("SuperbVote"));
            plData.setVault(enabledHooks.contains("Vault"));
            if (plData.hasTowny()) {
                DataPoint town = liteData.get("TOW-TOWN");
                plData.setTown((town != null) ? town.data() : "Not in a town");
                DataPoint friends = liteData.get("TOW-FRIENDS");
                plData.setFriends((town != null) ? friends.data() : "");
                DataPoint perms = liteData.get("TOW-PLOT PERMS");
                plData.setPlotPerms((perms != null) ? perms.data() : "");
                DataPoint options = liteData.get("TOW-PLOT OPTIONS");
                plData.setPlotOptions((options != null) ? options.data() : "");
            }
            if (plData.hasFactions()) {
                DataPoint faction = liteData.get("FAC-FACTION");
                plData.setFaction((faction != null) ? faction.data() : "Not in a faction");
            }
            if (plData.hasSuperbVote()) {
                try {
                    plData.setVotes(Integer.parseInt(liteData.get("SVO-VOTES").data()));
                } catch (Exception e) {
                    plData.setVotes(0);
                }
            }
            if (plData.hasVault()) {
                try {
                    plData.setMoney(Double.parseDouble(FormatUtils.removeLetters(liteData.get("ECO-BALANCE").data())));
                } catch (Exception e) {
                    plData.setMoney(0);
                }
            }
            data.setPlanLiteFound(true);
            data.setPlanLiteData(plData);
        }
    }
}