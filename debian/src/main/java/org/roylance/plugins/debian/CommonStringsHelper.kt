package org.roylance.plugins.debian


object CommonStringsHelper {
    const val DebianBuild = "build/debian/"
    const val DebianBuildBuild = "build/debian/build/"
    const val BuildInstallPath = "build/install/"
    const val SBinPath = "${DebianBuildBuild}usr/sbin/"

    const val CreateAndInstallPackageName = "create_install_package.sh"
    private const val RunName = "run.sh"

    fun buildProjectRunScriptLocation(projectName: String): String {
        return buildInstallPath(projectName, "${projectName}_$RunName")
    }

    fun buildSystemRunScriptLocation(projectName: String): String {
        return "$SBinPath${projectName}_$RunName"
    }

    fun buildInstallPath(projectName: String, fileName: String): String {
        return "$BuildInstallPath$projectName/$fileName"
    }
}