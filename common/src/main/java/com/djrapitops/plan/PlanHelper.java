package com.djrapitops.plan;

/**
 * Really dirty temporary hack.
 */
@Deprecated
public class PlanHelper {
    private static PlanPlugin instance;

    public static PlanPlugin getInstance() {
        return instance;
    }

    public static void setInstance(PlanPlugin planPlugin) {
        instance = planPlugin;
    }
}
