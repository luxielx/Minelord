/*
 * Decompiled with CFR 0_122.
 */
package kdvn.minelord.player;

public enum EnumClassMineLord {
    ARCHER("archer"),
    KNIGHT("knight"),
    MAGE("mage"),
    NULL("null");
    
    private String basicName;

    private EnumClassMineLord(String basicName, int n2, String string2) {
        this.basicName = basicName;
    }

    public String toString() {
        return this.basicName;
    }
}

