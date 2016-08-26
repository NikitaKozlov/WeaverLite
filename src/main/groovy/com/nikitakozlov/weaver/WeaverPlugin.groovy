package com.nikitakozlov.weaver

import org.gradle.api.Plugin
import org.gradle.api.Project

class WeaverPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        project.extensions.create('weaverLite', WeaverLitePluginExtension)

        def showDevicesTask = project.tasks.create("showDevices") << {
            def adbExe = project.android.getAdbExe().toString()
            println "${adbExe} devices".execute().text
        }
        showDevicesTask.group = "blogplugin"
        showDevicesTask.description = "Runs adb devices command"
        println(project.weaverLite.enabledForDebug)
        println(project.weaverLite.enabledForRelease)
        println(project.weaverLite.aspectjVersion)
    }
}
