package org.finos.intellij.legend.ide.lsp

import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.application.PluginPathManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import org.finos.intellij.legend.ide.completion.LegendLspCompletionSupport
import java.io.File

internal class LegendLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.extension == "pure") {
            serverStarter.ensureServerStarted(LegendLspServerDescriptor(project))
        }
    }
}

private const val EXEC = "java"

private const val LEGEND_SERVER_PATH = "server";

private const val LEGEND_SERVER_JAR = "legend-engine-ide-lsp-server-shaded-0.0.9-SNAPSHOT.jar"

private class LegendLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Pure") {

    override fun isSupportedFile(file: VirtualFile) = file.extension == "pure"

    override fun createCommandLine(): GeneralCommandLine {
        val lsp = getPluginDirectory(javaClass, LEGEND_SERVER_PATH)
        LOG.info("Path server path = '{}' exists = '{}'".format(lsp?.path, lsp?.exists()))
        if (lsp == null || !lsp.exists()) {
            // broken plugin installation?
            throw ExecutionException("Cannot find path for the Legend server" + lsp?.path)
        }

        return GeneralCommandLine().apply {
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withWorkDirectory(lsp.path)
            withCharset(Charsets.UTF_8)
            withExePath(EXEC)
            // TODO configure the pom
            withParameters("-jar", LEGEND_SERVER_JAR)
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
//    override val lspGoToDefinitionSupport = false;

    override val lspCompletionSupport = LegendLspCompletionSupport();

//    override val lspDiagnosticsSupport = null;

//    override val lspCodeActionsSupport = null;

//    override val lspCommandsSupport = null;

    // since 2023.3:
//    override val lspFormattingSupport = null;

//    override val lspHoverSupport = true;

    fun getPluginDirectory(pluginClass: Class<*>, resourceName: String): File? {
        return PluginPathManager.getPluginResource(pluginClass, resourceName)
    }
}