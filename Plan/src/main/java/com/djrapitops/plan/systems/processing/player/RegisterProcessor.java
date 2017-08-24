/* 
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.systems.processing.player;

import main.java.com.djrapitops.plan.Log;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.data.Action;
import main.java.com.djrapitops.plan.database.Database;
import main.java.com.djrapitops.plan.database.tables.Actions;

import java.sql.SQLException;
import java.util.UUID;

/**
 * //TODO Class Javadoc Comment
 *
 * @author Rsl1122
 */
public class RegisterProcessor extends PlayerProcessor {

    private final long time;
    private final int playersOnline;
    private final String name;

    public RegisterProcessor(UUID uuid, long time, String name, int playersOnline) {
        super(uuid);
        this.time = time;
        this.playersOnline = playersOnline;
        this.name = name;
    }

    @Override
    public void process() {
        UUID uuid = getUUID();
        Plan plugin = Plan.getInstance();
        Database db = plugin.getDB();
        if (db.wasSeenBefore(uuid)) {
            return;
        }
        plugin.getDataCache().addFirstLeaveCheck(uuid);
        try {
            db.getUsersTable().registerUser(uuid, time, name);
            db.getActionsTable().insertAction(uuid, new Action(time, Actions.REGISTERED, "Online: " + playersOnline + " Players"));
        } catch (SQLException e) {
            Log.toLog(this.getClass().getName(), e);
        }
    }
}