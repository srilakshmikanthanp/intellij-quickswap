package com.srilakshmikanthanp.intellij.quickswap.transformer

import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex

object FileTransformers {
  fun getDefault(project: Project): FileTransformer {
    return CompositeFileTransformer(
      listOf(
        DirectoryToChildFileTransformer(),
        FileContextFilter(ProjectFileIndex.getInstance(project)),
        FileTypeFilter()
      )
    )
  }
}
