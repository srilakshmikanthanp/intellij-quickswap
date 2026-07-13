package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.vfs.VirtualFile

interface FileTransformer {
  fun transform(files: Sequence<VirtualFile>): Sequence<VirtualFile>
}
