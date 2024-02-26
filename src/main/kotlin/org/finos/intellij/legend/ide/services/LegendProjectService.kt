package org.finos.intellij.legend.ide.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import org.finos.intellij.legend.ide.LegendBundle

@Service(Service.Level.PROJECT)
class LegendProjectService(project: Project) {

    init {
        thisLogger().info(LegendBundle.message("projectService", project.name))
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    fun getRandomNumber() = (1..100).random()
}
