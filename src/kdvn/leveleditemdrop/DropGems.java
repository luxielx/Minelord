/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.inventory.ItemStack
 */
package kdvn.leveleditemdrop;

import kdvn.leveleditemdrop.LeveledItemDrop;
import kdvn.settings.SettingMethod;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DropGems
implements Listener {
    public static int getMobLevel(LivingEntity mob) {
        String name = mob.getName();
        if (!name.contains("Lv")) {
            return 0;
        }
        int lv = SettingMethod.getIntInString(name);
        if (lv == 0) {
            Double d = new Double(mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            lv = d.intValue() / 20;
        }
        return lv;
    }

    public void drop(LivingEntity mob) {
        float tiLe;
        int lv = DropGems.getMobLevel(mob);
        if (lv <= 25) {
            float tiLe2 = 2 * lv;
            if (tiLe2 < 10.0f) {
                tiLe2 = 10.0f;
            }
            if (SettingMethod.tiLe(tiLe2)) {
                mob.getWorld().dropItem(mob.getLocation(), LeveledItemDrop.getItemLv1());
            }
        } else if (lv > 25 && lv < 40) {
            float tiLe3 = 2 * lv - 30;
            if (SettingMethod.tiLe(tiLe3)) {
                mob.getWorld().dropItem(mob.getLocation(), LeveledItemDrop.getItemLv2());
            }
        } else if (lv >= 40 && lv < 60) {
            float tiLe4 = 2 * lv - 70;
            if (SettingMethod.tiLe(tiLe4)) {
                mob.getWorld().dropItem(mob.getLocation(), LeveledItemDrop.getItemLv3());
            }
        } else if (lv >= 60 && SettingMethod.tiLe(tiLe = (float)(2 * lv - 110))) {
            mob.getWorld().dropItem(mob.getLocation(), LeveledItemDrop.getItemLv4());
        }
    }

    @EventHandler
    public void onKilledByPlayer(EntityDeathEvent e) {
    }
}

