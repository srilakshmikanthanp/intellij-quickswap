package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.debugger.DebuggerManagerEx
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.transformer.FileTransformers

class QuickSwapSelectedFilesAction : QuickSwapBaseAction() {
  override fun shouldEnable(project: Project, dataProvider: DataProvider): Boolean {
    val debugger = DebuggerManagerEx.getInstanceEx(project).context.debuggerSession
    val files = sourceFiles(project, dataProvider)
    return debugger != null && files.isNotEmpty()
  }

  override fun sourceFiles(project: Project, dataProvider: DataProvider): List<VirtualFile> {
    val selectedFiles = dataProvider.get(CommonDataKeys.VIRTUAL_FILE_ARRAY)?.asList().orEmpty()
    val transformer = FileTransformers.getDefault(project)
    val files = transformer.transform(selectedFiles)
    return files.toList()
  }
}
