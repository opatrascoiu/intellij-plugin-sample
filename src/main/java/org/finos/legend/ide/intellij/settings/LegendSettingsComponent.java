package org.finos.legend.ide.intellij.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.FormBuilder;
import org.finos.legend.ide.intellij.LegendBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class LegendSettingsComponent {
    public static final String LEGEND_LSP_JAR = "legend-engine-ide-lsp-server.*\\.jar";
    public static final Pattern LEGEND_LSP_JAR_PATTERN = Pattern.compile(LEGEND_LSP_JAR);

    private final JPanel mainPanel;
    private final TextFieldWithBrowseButton lspServerJar = new TextFieldWithBrowseButton();

    public LegendSettingsComponent(Project project) {
        FileChooserDescriptor singleFileDescriptor = new FileChooserDescriptor(false, false, true, false, false, false) {
            @Override
            public boolean isFileSelectable(@Nullable VirtualFile file) {
                return isLegendLsp(file);
            }
        };
        lspServerJar.addBrowseFolderListener(LegendBundle.message("legend.settings.text.lsp.title"), "", project, singleFileDescriptor);

        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel(LegendBundle.message("legend.settings.text.lsp.path")), lspServerJar, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public static boolean isLegendLsp(VirtualFile file) {
        if (file != null && !file.isDirectory()) {
            return LEGEND_LSP_JAR_PATTERN.matcher(file.getName()).matches();
        }
        return false;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return lspServerJar;
    }

    @NotNull
    public String getLspJar() {
        String text = lspServerJar.getText();
        return FileUtil.toSystemDependentName(text);
    }

    public void setLspJar(String lspJar) {
        lspServerJar.setText(lspJar);
    }
}