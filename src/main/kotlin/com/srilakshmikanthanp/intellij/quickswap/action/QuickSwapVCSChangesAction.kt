package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapVCSChangesAction : QuickSwapBaseAction() {
  override fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): Sequence<VirtualFile> {
    val files = ChangeListManager.getInstance(project).allChanges.asSequence().mapNotNull { it.afterRevision?.file?.virtualFile }
    return FileTransformers.getDefault(project).transform(files)
  }

  override fun supports(project: Project, eventDataProvider: EventDataProvider): Boolean {
    return ProjectLevelVcsManager.getInstance(project).hasActiveVcss()
  }
}
