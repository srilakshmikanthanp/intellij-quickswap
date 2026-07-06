package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.debugger.DebuggerManagerEx
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataKey
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.swap.HotSwapUIQuickSwapper

abstract class QuickSwapBaseAction : AnAction() {
  abstract fun shouldEnable(project: Project, dataProvider: DataProvider): Boolean
  abstract fun sourceFiles(project: Project, dataProvider: DataProvider): List<VirtualFile>

  class DataProvider(private val event: AnActionEvent) {
    fun <T> get(key: DataKey<T>): T? {
      return event.getData(key)
    }
  }

  final override fun getActionUpdateThread(): ActionUpdateThread {
    return ActionUpdateThread.BGT
  }

  final override fun actionPerformed(event: AnActionEvent) {
    val project = event.project ?: return
    val session = DebuggerManagerEx.getInstanceEx(project).context.debuggerSession ?: return
    val quickSwapper = HotSwapUIQuickSwapper(session)
    val files = sourceFiles(project, DataProvider(event))
    if (files.isEmpty()) return
    quickSwapper.swap(*files.toTypedArray())
  }

  final override fun update(event: AnActionEvent) {
    val project = event.project
    val debugger = DebuggerManagerEx.getInstanceEx(project).context
    event.presentation.isEnabledAndVisible = project != null && shouldEnable(project, DataProvider(event))
  }
}
