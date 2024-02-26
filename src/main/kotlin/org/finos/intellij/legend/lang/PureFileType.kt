package org.finos.intellij.legend.lang

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object PureFileType : LanguageFileType(PureLanguage) {
  override fun getName(): String = "Pure"

//  override fun getDescription(): String = PrismaBundle.message("filetype.prisma.description")
  override fun getDescription(): String = "Pure language"

  override fun getDefaultExtension(): String = "pure"

  override fun getIcon(): Icon = AllIcons.FileTypes.XsdFile;
}