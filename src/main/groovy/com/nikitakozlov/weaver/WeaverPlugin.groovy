package com.nikitakozlov.weaver

import org.gradle.api.Plugin
import org.gradle.api.Project

class WeaverPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        def showDevicesTask = target.tasks.create("showDevices") << {
            def adbExe = target.android.getAdbExe().toString()
            println "${adbExe} devices".execute().text
        }
        showDevicesTask.group = "blogplugin"
        showDevicesTask.description = "Runs adb devices command"
    }
}
