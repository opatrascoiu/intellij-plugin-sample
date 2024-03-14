package org.finos.legend.ide.intellij.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.finos.legend.ide.intellij.LegendBundle;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
final class LegendSettingsConfigurable implements Configurable {
    private LegendSettingsComponent settingsComponent;
    private final Project project;

    // A default constructor with Project as argument is required because this implementation
    // is registered in an projectConfigurable EP
    public LegendSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Override
    public String getDisplayName() {
        return LegendBundle.message("legend.settings.displayName");
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new LegendSettingsComponent(project);
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        LegendSettingsState settings = LegendSettingsState.getInstance(project);
        boolean modified = !settingsComponent.getLspJar().equals(settings.lspJar);
        return modified;
    }

    @Override
    public void apply() {
        LegendSettingsState settings = LegendSettingsState.getInstance(project);
        settings.lspJar = settingsComponent.getLspJar();
    }

    @Override
    public void reset() {
        LegendSettingsState settings = LegendSettingsState.getInstance(project);
        settingsComponent.setLspJar(settings.lspJar);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}