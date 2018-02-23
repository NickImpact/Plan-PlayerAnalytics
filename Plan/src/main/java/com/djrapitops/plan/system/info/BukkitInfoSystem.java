/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package com.djrapitops.plan.system.info;

import com.djrapitops.plan.api.exceptions.connection.NoServersException;
import com.djrapitops.plan.api.exceptions.connection.WebException;
import com.djrapitops.plan.system.info.connection.BukkitConnectionSystem;
import com.djrapitops.plan.system.info.request.CacheNetworkPageContentRequest;
import com.djrapitops.plan.system.info.request.InfoRequest;
import com.djrapitops.plan.system.info.request.SetupRequest;
import com.djrapitops.plan.system.info.server.ServerInfo;
import com.djrapitops.plan.utilities.html.HtmlStructure;
import com.djrapitops.plugin.api.utility.log.Log;

/**
 * InfoSystem for Bukkit servers.
 *
 * @author Rsl1122
 */
public class BukkitInfoSystem extends InfoSystem {

    public BukkitInfoSystem() {
        super(new BukkitConnectionSystem());
    }

    @Override
    public void runLocally(InfoRequest infoRequest) throws WebException {
        if (infoRequest instanceof SetupRequest) {
            throw new NoServersException("Set-up requests can not be run locally.");
        }
        Log.debug("LocalRun: " + infoRequest.getClass().getSimpleName());
        infoRequest.runLocally();
    }

    @Override
    public void updateNetworkPage() throws WebException {
        String html = HtmlStructure.createServerContainer();
        sendRequest(new CacheNetworkPageContentRequest(ServerInfo.getServerUUID(), html));
    }
}