package org.finos.intellij.legend.ide.tool

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import org.finos.intellij.legend.ide.LegendBundle
import org.finos.intellij.legend.ide.services.LegendProjectService
import javax.swing.JButton


class LegendToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = LegendToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class LegendToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<LegendProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(LegendBundle.message("legend.randomLabel", "?"))

            add(label)
            add(JButton(LegendBundle.message("legend.shuffle")).apply {
                addActionListener {
                    label.text = LegendBundle.message("legend.randomLabel", service.getRandomNumber())
                }
            })
        }
    }
}
