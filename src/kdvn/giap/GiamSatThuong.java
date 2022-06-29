/*
 * Decompiled with CFR 0_122.
 */
package kdvn.giap;

public class GiamSatThuong {
    public static double giamSatThuong(double damage, float tiLe) {
        if (tiLe < 0.0f || tiLe > 100.0f) {
            return damage;
        }
        double satThuong = damage;
        return satThuong *= (double)(tiLe / 100.0f);
    }
}

