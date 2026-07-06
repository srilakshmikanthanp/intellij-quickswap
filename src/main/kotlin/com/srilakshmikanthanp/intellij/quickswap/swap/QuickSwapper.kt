package com.srilakshmikanthanp.intellij.quickswap.swap

import com.intellij.openapi.vfs.VirtualFile

interface QuickSwapper {
  fun swap(vararg files: VirtualFile)
}
