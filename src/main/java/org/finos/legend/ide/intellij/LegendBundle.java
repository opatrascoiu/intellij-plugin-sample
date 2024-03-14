package org.finos.legend.ide.intellij;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

public class LegendBundle extends DynamicBundle {
    public static final String PATH_TO_BUNDLE = "messages.LegendBundle";
    private static final LegendBundle BUNDLE = new LegendBundle();

    public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, Object @NotNull ... params) {
        return BUNDLE.getMessage(key, params);
    }

    @NotNull
    public static Supplier<String> messagePointer(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, Object @NotNull ... params) {
        return BUNDLE.getLazyMessage(key, params);
    }

    public LegendBundle() {
        super(PATH_TO_BUNDLE);
    }
}
