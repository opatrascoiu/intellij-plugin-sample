package org.finos.intellij.legend.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import org.finos.intellij.legend.ide.completion.LegendLspCompletionSupport

internal class LegendLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.extension == "pure") {
            serverStarter.ensureServerStarted(LegendLspServerDescriptor(project))
        }
    }
}

private const val EXEC = "java"

private const val ARGS = "-jar ./server/legend-engine-ide-lsp-server-shaded-0.0.9-SNAPSHOT.jar ./server/pom.xml"

private class LegendLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Pure") {

    override fun isSupportedFile(file: VirtualFile) = file.extension == "pure"
//    override fun createCommandLine() = GeneralCommandLine(EXEC, ARGS)

    override fun createCommandLine(): GeneralCommandLine {
        return GeneralCommandLine().apply {
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withCharset(Charsets.UTF_8)
            withExePath(EXEC)
            addParameters(ARGS)
        }
    }

    // references resolution is implemented without using the LSP server
//    override val lspGoToDefinitionSupport = false

    // code completion is implemented without using the LSP server
//    override val lspCompletionSupport = null

    // code formatting is implemented without using the LSP server
//    override val lspFormattingSupport = null

//    override val lspHoverSupport = false

    // since 2023.2
    override val lspGoToDefinitionSupport = false;

    override val lspCompletionSupport = LegendLspCompletionSupport();

    override val lspDiagnosticsSupport = null;

    override val lspCodeActionsSupport = null;

    override val lspCommandsSupport = null;

    // since 2023.3:
//    override val lspFormattingSupport = null;

//    override val lspHoverSupport = true;
}