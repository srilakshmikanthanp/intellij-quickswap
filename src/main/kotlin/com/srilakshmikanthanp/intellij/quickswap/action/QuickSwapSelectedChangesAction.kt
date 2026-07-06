package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.openapi.vcs.VcsDataKeys
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapSelectedChangesAction : QuickSwapBaseAction() {
  override fun enable(project: Project, eventDataProvider: EventDataProvider): Boolean {
    if (!ProjectLevelVcsManager.getInstance(project).hasActiveVcss()) return false
    val files = sourceFiles(project, eventDataProvider)
    return files.isNotEmpty()
  }

  override fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): List<VirtualFile> {
    val selectedChanges = eventDataProvider.get(VcsDataKeys.CHANGES).orEmpty().mapNotNull { it.afterRevision?.file?.virtualFile }
    val selectedFiles = eventDataProvider.get(CommonDataKeys.VIRTUAL_FILE_ARRAY)?.asList().orEmpty()
    val transformer = FileTransformers.getDefault(project)
    return transformer.transform(selectedChanges + selectedFiles).toList()
  }
}
