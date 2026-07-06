package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.VirtualFile

class FileContextFilter(private val fileIndex: ProjectFileIndex): FileFilter() {
  override fun isEligible(file: VirtualFile): Boolean {
    return fileIndex.isInSourceContent(file) || fileIndex.isInTestSourceContent(file)
  }
}
