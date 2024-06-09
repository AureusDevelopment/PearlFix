package me.petulikan1.PearlFix;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.command.CommandSender;

public class Utils {

    private final static PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.builder().build();

    private final static LegacyComponentSerializer legacySection
            = LegacyComponentSerializer.legacySection().toBuilder().useUnusualXRepeatedCharacterHexFormat().hexColors().extractUrls()
            .build();

    private static final MiniMessage mm = MiniMessage.builder().build();

    public static <T extends Translatable> String plainText(T t) {
        return plainTextComponentSerializer.serialize(Component.translatable(t));
    }

    public static String plainText(String msg) {
        return mm.serialize(legacySection.deserialize(plainTextComponentSerializer.serialize(Component.text(msg))));
    }

    public static String getTranslation(String key) {
        return Loader.getMain().getConfig().getString("Translations." + key, "NO_TRANSLATION_FOUND!");
    }

    public static void msg(String key, CommandSender s) {
        if (s == null)
            throw new RuntimeException("CommandSender can't be null!");
        s.sendMessage(component(key));
    }

    public static void msg(String key, CommandSender s, Object... objects) {
        if (s == null)
            throw new RuntimeException("CommandSender can't be null!");
        s.sendMessage(component(key, objects));
    }

    public static Component component(String key) {
        return mm.deserialize(getTranslation(key));
    }

    public static Component component(String key, Object... objects) {
        return mm.deserialize(replacedTranslation(key, objects));
    }

    public static String replacedTranslation(String key, Object... objects) {
        String translation = getTranslation(key);
        int i = 0;
        for (Object o : objects) {
            translation = translation.replace("{" + i + "}", o + "");
            i++;
        }
        return translation;
    }
}
