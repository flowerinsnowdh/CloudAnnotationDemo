package online.flowerinsnow.cloudannotationsdemo.bukkit.util;

import java.util.Objects;

public abstract class TextUtils {
    private TextUtils() {
    }

    public static String parseColour(String text) {
        Objects.requireNonNull(text);
        return text.replace("&", "§")
                .replace("§§", "&");
    }
}
