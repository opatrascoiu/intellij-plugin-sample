package org.finos.intellij.legend.ide.completion

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.platform.lsp.api.customization.LspCompletionSupport

class LegendLspCompletionSupport : LspCompletionSupport() {
    override fun createLookupElement(parameters: com.intellij.codeInsight.completion.CompletionParameters, item: org.eclipse.lsp4j.CompletionItem): com.intellij.codeInsight.lookup.LookupElement? {
        return LookupElementBuilder.create("TEST Pure")
    }

    override fun getIcon(item: org.eclipse.lsp4j.CompletionItem): javax.swing.Icon? {
        return AllIcons.FileTypes.Css
    }

    override fun getTailText(item: org.eclipse.lsp4j.CompletionItem): kotlin.String? {
        return item.insertText
    }

    override fun getTypeText(item: org.eclipse.lsp4j.CompletionItem): kotlin.String? {
        return item.label
    }

    override fun isBold(item: org.eclipse.lsp4j.CompletionItem): kotlin.Boolean {
        return false;
    }

    override fun isStrikeout(item: org.eclipse.lsp4j.CompletionItem): kotlin.Boolean {
        return false;
    }

    override fun shouldRunCodeCompletion(parameters: com.intellij.codeInsight.completion.CompletionParameters): kotlin.Boolean {
        return true;
    }
}