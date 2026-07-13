package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VirtualFile

class CompositeFileTransformer(
  private val transformers: List<FileTransformer>
) : FileTransformer {
  override fun transform(files: Sequence<VirtualFile>): Sequence<VirtualFile> {
    return transformers.fold(files) { acc, transformer -> transformer.transform(acc) }
  }
}
