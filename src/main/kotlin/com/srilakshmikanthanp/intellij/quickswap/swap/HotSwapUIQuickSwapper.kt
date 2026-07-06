package com.srilakshmikanthanp.intellij.quickswap.swap

import com.intellij.debugger.impl.DebuggerSession
import com.intellij.debugger.ui.HotSwapUI
import com.intellij.openapi.vfs.VirtualFile

class HotSwapUIQuickSwapper(private val session: DebuggerSession) : QuickSwapper {
  override fun swap(vararg files: VirtualFile) {
    HotSwapUI.getInstance(session.project).compileAndReload(session, *files)
  }
}
