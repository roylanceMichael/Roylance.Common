package org.roylance.plugins.debian


object CommonStringsHelper {
    const val DebianBuild = "build/debian/"
    const val DebianBuildBuild = "build/debian/build/"
    const val BuildInstallPath = "build/install/"
    const val SBinPath = "${DebianBuildBuild}usr/sbin/"

    const val CreateAndInstallPackageName = "create_install_package.sh"
    const val RunName = "run.sh"

    fun buildProjectRunScriptLocation(projectName: String): String {
        return buildInstallPath(projectName, RunName)
    }

    fun buildSystemRunScriptLocation(): String {
        return "$SBinPath${RunName}"
    }

    fun buildInstallPath(projectName: String, fileName: String): String {
        return "$BuildInstallPath$projectName/$fileName"
    }
}