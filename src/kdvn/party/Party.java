/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.alessiodp.parties.Parties
 *  com.alessiodp.parties.handlers.PartyHandler
 *  com.alessiodp.parties.handlers.PlayerHandler
 *  com.alessiodp.parties.objects.Party
 *  org.bukkit.entity.Player
 */
package kdvn.party;

import com.alessiodp.parties.Parties;
import com.alessiodp.parties.handlers.PartyHandler;
import com.alessiodp.parties.handlers.PlayerHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

public class Party {
    private static Parties party = Parties.getInstance();

    public static com.alessiodp.parties.objects.Party getParty(Player player) {
        return party.getPlayerHandler().getPartyFromPlayer(player);
    }

    public static com.alessiodp.parties.objects.Party getParty(String name) {
        com.alessiodp.parties.objects.Party par = null;
        HashMap list = Party.party.getPartyHandler().listParty;
        for (String key : list.keySet()) {
            if (!key.equals(name)) continue;
            return (com.alessiodp.parties.objects.Party)list.get(key);
        }
        return par;
    }

    public static List<Player> getMemberOnlineList(com.alessiodp.parties.objects.Party p) {
        return p.getOnlinePlayers();
    }

    public static boolean isInParty(Player player, com.alessiodp.parties.objects.Party party) {
        ArrayList playerInParty = party.getOnlinePlayers();
        if (playerInParty.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static boolean hasParty(Player player) {
        try {
            party.getPlayerHandler().getPartyFromPlayer(player);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}

