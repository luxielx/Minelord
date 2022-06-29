/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.cuonghoa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCuongHoa {
    private static final String CUONGHOA = " \u00a7c\u00a7l[\u00a7f\u00a7l+@\u00a7c\u00a7l]";
    private static final String CUONGHOA_CONTAIN = "\u00a7f\u00a7l+";
    private static final double TILE_LV = 10.0;

    public static boolean hasMyItem(ItemStack item) {
        if (!item.getItemMeta().hasLore()) {
            return false;
        }
        for (String s : item.getItemMeta().getLore()) {
            if (s.contains("S\u00e1t th\u01b0\u01a1ng")) {
                return true;
            }
            if (!s.contains("Ph\u00f2ng th\u1ee7")) continue;
            return true;
        }
        return false;
    }

    public static Map<String, Boolean> getListStats() {
        HashMap<String, Boolean> list = new HashMap<String, Boolean>();
        list.put("S\u00e1t th\u01b0\u01a1ng", false);
        list.put("Ph\u00f2ng th\u1ee7", false);
        list.put("Xuy\u00ean gi\u00e1p", false);
        list.put("S\u00e1t th\u01b0\u01a1ng PvP", false);
        list.put("S\u00e1t th\u01b0\u01a1ng PvE", false);
        list.put("Ph\u00f2ng th\u1ee7", false);
        list.put("Ph\u00f2ng th\u1ee7 PvP", false);
        list.put("Ph\u00f2ng th\u1ee7 PvE", false);
        list.put("M\u00e1u", false);
        list.put("T\u1ec9 l\u1ec7 ch\u00ed m\u1ea1ng", true);
        list.put("L\u00e1 Ch\u1eafn", false);
        list.put("\u0110\u1ed9 b\u1ec1n", false);
        return list;
    }

    public static Map<String, List<Double>> getDoubleInStats(ItemStack item) {
        HashMap<String, List<Double>> list = new HashMap<String, List<Double>>();
        if (!item.getItemMeta().hasLore()) {
            return list;
        }
        for (String s : item.getItemMeta().getLore()) {
            for (String key : ItemCuongHoa.getListStats().keySet()) {
                if (!s.contains(key)) continue;
                ArrayList<Double> listDouble = new ArrayList<Double>();
                Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
                Matcher m = p.matcher(s);
                while (m.find()) {
                    double d = Double.parseDouble(m.group(1));
                    listDouble.add(d);
                }
                list.put(key, listDouble);
            }
        }
        return list;
    }

    public static double getDoubleInString(String s) {
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(s);
        if (m.find()) {
            double d = Double.parseDouble(m.group(1));
            d = ItemCuongHoa.lamTron(d);
            return d;
        }
        return 0.0;
    }

    public static List<Double> getListDoubleInString(String s) {
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(s);
        ArrayList<Double> number = new ArrayList<Double>();
        while (m.find()) {
            double d = Double.parseDouble(m.group(1));
            d = ItemCuongHoa.lamTron(d);
            number.add(d);
        }
        return number;
    }

    public static ItemStack change1Lv(ItemStack item, boolean tang) {
        if (!item.getItemMeta().hasLore()) {
            return item;
        }
        ItemStack newItem = item;
        List lore = newItem.getItemMeta().getLore();
        for (String s : ItemCuongHoa.getListStats().keySet()) {
            int i = 0;
            while (i < lore.size()) {
                if (((String)lore.get(i)).contains(s)) {
                    if (((String)lore.get(i)).contains("S\u00e1t th\u01b0\u01a1ng ch\u00ed m\u1ea1ng")) {
                        String newLine = ((String)lore.get(i)).replace("" + ItemCuongHoa.getDoubleInString((String)lore.get(i)), "2.5");
                        lore.set(i, newLine);
                    } else {
                        Iterator<Double> iterator = ItemCuongHoa.getListDoubleInString((String)lore.get(i)).iterator();
                        while (iterator.hasNext()) {
                            double oldNumber = iterator.next();
                            double newNumber = 0.0;
                            newNumber = tang ? oldNumber * 1.1 : oldNumber * 1.0 / 1.1;
                            if (ItemCuongHoa.getListStats().get(s).booleanValue() && newNumber > 100.0) {
                                newNumber = 100.0;
                            }
                            newNumber = ItemCuongHoa.lamTron(newNumber);
                            String newLine = ((String)lore.get(i)).replace("" + oldNumber, "" + newNumber);
                            lore.set(i, newLine);
                        }
                    }
                }
                ++i;
            }
        }
        ItemMeta meta = newItem.getItemMeta();
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        return newItem;
    }

    public static ItemStack cuongHoa(ItemStack item, int capDo) {
        int weaponLv = ItemCuongHoa.getCapDo(item);
        ItemStack newItem = item;
        if (capDo < weaponLv) {
            int i = 0;
            while (i < weaponLv - capDo) {
                newItem = ItemCuongHoa.giam1Cap(item);
                ++i;
            }
        } else {
            int i = 0;
            while (i < Math.abs(weaponLv - capDo)) {
                newItem = ItemCuongHoa.cuongHoa1Cap(item);
                ++i;
            }
        }
        return newItem;
    }

    public static int getCapDo(ItemStack item) {
        int damage = 0;
        if (!item.getItemMeta().hasDisplayName()) {
            return 0;
        }
        if (!item.getItemMeta().hasLore()) {
            return 0;
        }
        if (!item.getItemMeta().getDisplayName().contains("\u00a7f\u00a7l+")) {
            return 0;
        }
        if (ItemCuongHoa.getItemName(item).contains("\u00a7f\u00a7l+")) {
            String damageLore = ItemCuongHoa.getItemName(item);
            String lvString = damageLore.substring(damageLore.lastIndexOf("+") + 1, damageLore.replaceAll("\u00a7c\u00a7l]", "").length());
            try {
                int satThuong;
                damage = satThuong = Integer.parseInt(lvString);
            }
            catch (Exception e) {
                return 0;
            }
        }
        return damage;
    }

    public static ItemStack cuongHoa1Cap(ItemStack item) {
        int lv = ItemCuongHoa.getCapDo(item);
        ItemStack newItem = item;
        ItemMeta meta = item.getItemMeta();
        if (!ItemCuongHoa.getItemName(item).contains("\u00a7f\u00a7l+")) {
            meta.setDisplayName(String.valueOf(ItemCuongHoa.getItemName(item)) + " \u00a7c\u00a7l[\u00a7f\u00a7l+@\u00a7c\u00a7l]".replace("@", "1"));
        } else {
            meta.setDisplayName(ItemCuongHoa.getItemName(item).replace("+" + lv, "+" + (lv + 1)));
        }
        newItem.setItemMeta(meta);
        ItemStack finalItem = newItem;
        finalItem = ItemCuongHoa.change1Lv(newItem, true);
        return finalItem;
    }

    public static ItemStack giam1Cap(ItemStack item) {
        ItemStack newItem = item;
        ItemMeta meta = newItem.getItemMeta();
        int lv = ItemCuongHoa.getCapDo(item);
        meta.setDisplayName(ItemCuongHoa.getItemName(item).replace("+" + lv, "+" + (lv - 1)));
        newItem.setItemMeta(meta);
        ItemStack finalItem = item;
        finalItem = ItemCuongHoa.change1Lv(newItem, false);
        return finalItem;
    }

    public static String getItemName(ItemStack i) {
        String name = "";
        name = !i.getItemMeta().hasDisplayName() ? i.getType().name().replaceAll("_", " ") : i.getItemMeta().getDisplayName();
        return name;
    }

    public static double lamTron(double i) {
        String s = ItemCuongHoa.lamTronString(i).replace(",", ".");
        double newDouble = Double.valueOf(s);
        return newDouble;
    }

    public static String lamTronString(double i) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(i).replace(",", ".");
    }

    public static int getIntInString(String s) {
        String intValue = s.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(intValue);
        }
        catch (Exception e) {
            return 0;
        }
    }
}

