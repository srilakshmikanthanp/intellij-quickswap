package com.srilakshmikanthanp.intellij.quickswap.action

import com.intellij.debugger.DebuggerManagerEx
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.srilakshmikanthanp.intellij.quickswap.swap.HotSwapUIQuickSwapper

abstract class QuickSwapBaseAction : AnAction() {
  protected abstract fun sourceFiles(project: Project, eventDataProvider: EventDataProvider): Sequence<VirtualFile>
  protected open fun supports(project: Project, eventDataProvider: EventDataProvider): Boolean = true

  private fun enable(event: AnActionEvent): Boolean {
    val project = event.project ?: return false
    val session = DebuggerManagerEx.getInstanceEx(project).context.debuggerSession ?: return false
    if (!session.isAttached) return false
    val eventDataProvider = EventDataProvider(event)
    return session.process.canRedefineClasses() && supports(project, eventDataProvider) && sourceFiles(project, eventDataProvider).any()
  }

  final override fun getActionUpdateThread(): ActionUpdateThread {
    return ActionUpdateThread.BGT
  }

  final override fun update(event: AnActionEvent) {
    event.presentation.isEnabledAndVisible = enable(event)
  }

  final override fun actionPerformed(event: AnActionEvent) {
    val project = event.project ?: return
    val session = DebuggerManagerEx.getInstanceEx(project).context.debuggerSession ?: return
    val quickSwapper = HotSwapUIQuickSwapper(session)
    val files = sourceFiles(project, EventDataProvider(event)).toList()
    if (files.isEmpty()) return
    quickSwapper.swap(*files.toTypedArray())
  }
}
