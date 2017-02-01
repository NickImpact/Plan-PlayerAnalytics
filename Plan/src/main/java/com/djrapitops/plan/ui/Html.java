package main.java.com.djrapitops.plan.ui;

/**
 *
 * @author Rsl1122
 */
public enum Html {

    REPLACE0("REPLACE0"),
    REPLACE1("REPLACE1"),
    WARN_INACCURATE("<div class=\"warn\">Data might be inaccurate, player has just registered.</div>"),
    COLOR_0("<span class=\"black\">"),
    COLOR_1("<span class=\"darkblue\">"),
    COLOR_2("<span class=\"darkgreen\">"),
    COLOR_3("<span class=\"darkaqua\">"),
    COLOR_4("<span class=\"darkred\">"),
    COLOR_5("<span class=\"darkpurple\">"),
    COLOR_6("<span class=\"gold\">"),
    COLOR_7("<span class=\"gray\">"),
    COLOR_8("<span class=\"darkgray\">"),
    COLOR_9("<span class=\"blue\">"),
    COLOR_a("<span class=\"green\">"),
    COLOR_b("<span class=\"aqua\">"),
    COLOR_c("<span class=\"red\">"),
    COLOR_d("<span class=\"pink\">"),
    COLOR_e("<span class=\"yellow\">"),
    COLOR_f("<span class=\"white\">"),
    SPAN(""+REPLACE0+"</span>"),
    BUTTON("<a class=\"button\" href=\""+REPLACE0+"\">"+REPLACE1+"</a>"),
    BUTTON_CLASS("class=\"button\""),
    LINK_CLASS("class=\"link\""),
    TABLE_START("<table class=\"table\">"),
    TABLE_END("</table>"),
    TABLELINE("<tr class=\"tableline\"><td><b>"+REPLACE0+"</b></td>\r\n<td>"+REPLACE1+"</td></tr>"),
    ERROR_TABLE("<p class=\"red\">Error Calcuclating Table (No data)</p>"),
    IMG("<img src=\""+REPLACE0+"\">"),
    TOP_TOWNS("<p><b>Top 20 Towns</b></p>"),
    TOP_FACTIONS("<p><b>Top 20 Factions</b></p>"),
    TOTAL_BALANCE("<p>Server Total Balance: "+REPLACE0+"</p>"),
    TOTAL_VOTES("<p>Players have voted total of "+REPLACE0+" times.</p>"),
    TOWN("<p>Town: "+REPLACE0+"</p>"),
    PLOT_OPTIONS("<p>Plot options: "+REPLACE0+"</p>"),
    FRIENDS("<p>Friends with "+REPLACE0+"</p>"),
    FACTION("<p>Faction: " + REPLACE0 + "</p>"),
    BALANCE("<p>Balance: " + REPLACE0 + "</p>"),
    VOTES("<p>Player has voted " + REPLACE0 + " times.</p>")
    ;

    private final String html;

    private Html(String html) {
        this.html = html;
    }

    public String parse() {
        return html;
    }

    public String parse(String... p) {
        String returnValue = this.html;
        for (int i = 0; i < p.length; i++) {
            returnValue = returnValue.replaceAll("REPLACE" + i, p[i]);
        }
        return returnValue;
    }

}