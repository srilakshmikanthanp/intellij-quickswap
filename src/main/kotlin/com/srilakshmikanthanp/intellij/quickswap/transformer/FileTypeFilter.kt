package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VirtualFile

class FileTypeFilter : FileFilter() {
  override fun isEligible(file: VirtualFile): Boolean {
    return file.name.endsWith(".java") || file.name.endsWith(".kt")
  }
}
