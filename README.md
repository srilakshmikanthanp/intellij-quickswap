# QuickSwap

[![Install from JetBrains Marketplace](https://img.shields.io/badge/JetBrains%20Marketplace-Install%20Plugin-000000?logo=jetbrains)](https://plugins.jetbrains.com/plugin/32787)

QuickSwap extends IntelliJ IDEA's standard HotSwap, letting you selectively reload only the files you choose into a running debug session.

## Features

- **File/Folder Selection**: Right-click Java files/folders in Project View → **QuickSwap**
- **VCS Changes**: Right-click in Local Changes view → **QuickSwap**
- **All Modified Files**: **QuickSwap All VCS Modified** appears in Find Action (Ctrl+Shift+A) only when there are VCS-modified files.

_Note: The above actions are available only during an active debug session._

## Installation

```bash
cd intellij-quickswap
./gradlew buildPlugin
```

1. Settings → Plugins → ⚙️ → Install Plugin from Disk
2. Select `build/distributions/intellij-quickswap-*.zip`
3. Restart IntelliJ

## DCEVM Setup (for structural changes)

The standard JVM supports reloading method-body changes only. To apply structural changes (such as adding or removing methods or fields), use a JVM that supports enhanced class redefinition (for example, a DCEVM-enabled JVM) and start it with:

`-XX:+AllowEnhancedClassRedefinition`

If the JVM does not support this feature, structural class changes are rejected.

_JetBrains Runtime (JBR) with DCEVM is one JVM that supports this feature._
