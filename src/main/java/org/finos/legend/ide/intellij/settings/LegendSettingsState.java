package org.finos.legend.ide.intellij.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@Service(Service.Level.PROJECT)
@State(
        name = "org.intellij.sdk.settings.AppSettingsState",
        storages = @Storage("legend.xml")
)
final class LegendSettingsState implements PersistentStateComponent<LegendSettingsState> {
    public String lspJar = "";

    static LegendSettingsState getInstance(Project project) {
        return project.getService(LegendSettingsState.class);
    }

    @Override
    public LegendSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull LegendSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}