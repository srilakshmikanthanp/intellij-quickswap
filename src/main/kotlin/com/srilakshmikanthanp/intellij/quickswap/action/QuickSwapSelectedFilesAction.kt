package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapSelectedFilesAction : QuickSwapBaseAction() {
  override fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): Sequence<VirtualFile> {
    val selectedFiles = eventDataProvider.get(CommonDataKeys.VIRTUAL_FILE_ARRAY)?.asSequence().orEmpty()
    return FileTransformers.getDefault(project).transform(selectedFiles)
  }
}
