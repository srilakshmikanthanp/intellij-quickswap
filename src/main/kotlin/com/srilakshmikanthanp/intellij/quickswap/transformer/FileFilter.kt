package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VirtualFile

abstract class FileFilter : FileTransformer {
  override fun transform(files: Collection<VirtualFile>): List<VirtualFile> {
    return files.filter { isEligible(it) }
  }

  abstract fun isEligible(file: VirtualFile): Boolean;
}
