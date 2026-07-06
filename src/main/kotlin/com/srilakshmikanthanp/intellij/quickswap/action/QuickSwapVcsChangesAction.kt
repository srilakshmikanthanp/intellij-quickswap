package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapVcsChangesAction : QuickSwapBaseAction() {
  override fun enable(project: Project, eventDataProvider: EventDataProvider): Boolean {
    if (!ProjectLevelVcsManager.getInstance(project).hasActiveVcss()) return false
    val files = sourceFiles(project, eventDataProvider)
    return files.isNotEmpty()
  }

  override fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): List<VirtualFile> {
    val files = ChangeListManager.getInstance(project).allChanges.mapNotNull { it.afterRevision?.file?.virtualFile }
    val transformer = FileTransformers.getDefault(project)
    return transformer.transform(files).toList()
  }
}
