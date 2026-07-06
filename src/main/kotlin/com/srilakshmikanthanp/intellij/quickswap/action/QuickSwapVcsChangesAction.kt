package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.debugger.DebuggerManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.openapi.vcs.changes.ChangeListManager
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapVcsChangesAction : QuickSwapBaseAction() {
  override fun shouldEnable(project: Project, dataProvider: DataProvider): Boolean {
    if (!ProjectLevelVcsManager.getInstance(project).hasActiveVcss()) return false
    val debugger = DebuggerManagerEx.getInstanceEx(project).context.debuggerSession
    val files = sourceFiles(project, dataProvider)
    return debugger != null && files.isNotEmpty()
  }

  override fun sourceFiles(project: Project, dataProvider: DataProvider): List<VirtualFile> {
    val files = ChangeListManager.getInstance(project).allChanges.mapNotNull { it.afterRevision?.file?.virtualFile }
    val transformer = FileTransformers.getDefault(project)
    return transformer.transform(files).toList()
  }
}
