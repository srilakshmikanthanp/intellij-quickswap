package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapSelectedFilesAction : QuickSwapBaseAction() {
  override fun enable(project: Project, eventDataProvider: EventDataProvider): Boolean {
    val files = sourceFiles(project, eventDataProvider)
    return files.isNotEmpty()
  }

  override fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): List<VirtualFile> {
    val selectedFiles = eventDataProvider.get(CommonDataKeys.VIRTUAL_FILE_ARRAY)?.asList().orEmpty()
    val transformer = FileTransformers.getDefault(project)
    val files = transformer.transform(selectedFiles)
    return files.toList()
  }
}
