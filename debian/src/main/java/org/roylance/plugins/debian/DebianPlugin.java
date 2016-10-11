package org.roylance.plugins.debian;

import org.apache.commons.io.FileUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

public class DebianPlugin extends DefaultTask {
    public String projectName = "";
    public String serverVersion = "";
    public String maintainerInfo = "";

    @TaskAction
    public void buildDebianPackage() throws IOException, InterruptedException {
        final String errorMessage = " is empty or null, please fill in before running task";
        if (projectName == null || projectName.length() == 0) {
            System.out.println("projectName" + errorMessage);
            return;
        }

        if (serverVersion == null || serverVersion.length() == 0) {
            System.out.println("serverVersion" + errorMessage);
            return;
        }

        if (maintainerInfo == null || maintainerInfo.length() == 0) {
            System.out.println("maintainerInfo" + errorMessage);
            return;
        }

        final RunBuilder runBuilder = new RunBuilder();
        final DebianPackageBuilder debianPackageBuilder = new DebianPackageBuilder(
                projectName,
                serverVersion,
                maintainerInfo,
                CommonStringsHelper.DebianBuildBuild + "DEBIAN/control");

        runBuilder.build(projectName, CommonStringsHelper.INSTANCE.buildProjectRunScriptLocation(projectName));
        buildDebianStructure();
        runBuilder.build(projectName, CommonStringsHelper.INSTANCE.buildSystemRunScriptLocation(projectName));
        debianPackageBuilder.build();

        new CreateInstallPackageBuilder(serverVersion,
                projectName,
                CommonStringsHelper.DebianBuild + CommonStringsHelper.CreateAndInstallPackageName)
                .build();

        FileUtils.copyDirectory(
                new File(CommonStringsHelper.BuildInstallPath + projectName),
                new File(CommonStringsHelper.DebianBuildBuild + "opt/" + projectName));

        final File debianDirectory =  new File(CommonStringsHelper.DebianBuild + projectName + "_" + serverVersion + "_all");
        FileUtils.moveDirectory(new File(CommonStringsHelper.DebianBuildBuild),
                debianDirectory);

        final Process process;
        process = Runtime.getRuntime().exec("chmod -R 755 " + debianDirectory.getAbsolutePath());
        process.waitFor();
    }

    private void buildDebianStructure() {
        // make directory structure for debian package
        new File(CommonStringsHelper.DebianBuild).delete();
        new File(CommonStringsHelper.DebianBuild).mkdir();
        new File(CommonStringsHelper.DebianBuildBuild).mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "usr").mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "usr/sbin").mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "opt").mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "opt/" + projectName).mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "etc").mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "etc/init.d").mkdir();
        new File(CommonStringsHelper.DebianBuildBuild + "DEBIAN").mkdir();
    }
}
