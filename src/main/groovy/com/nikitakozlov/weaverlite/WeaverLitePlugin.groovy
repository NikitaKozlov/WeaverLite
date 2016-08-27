package com.nikitakozlov.weaverlite

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessageHolder
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.api.tasks.compile.JavaCompile
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main

class WeaverLitePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        final def variants
        if (project.plugins.withType(AppPlugin)) {
            variants = project.android.applicationVariants
        } else if (project.plugins.withType(LibraryPlugin)) {
            variants = project.android.libraryVariants
        } else {
            throw new ProjectConfigurationException("Either 'com.android.application' or 'com.android.library' " +
                    "plugin is required.", null)
        }

        project.extensions.create('weaverLite', WeaverLitePluginExtension)
        final def ext = project.weaverLite

        final def logger = project.logger

        final aspectJVersion = "1.8.6"

        if (ext.enabledForRelease) {
            project.dependencies {
                compile "org.aspectj:aspectjrt:${aspectJVersion}"
            }
        } else if (ext.enabledForDebug) {
            project.dependencies {
                debugCompile "org.aspectj:aspectjrt:${aspectJVersion}"
            }
        }

        variants.all { variant ->
            if ((variant.buildType.isDebuggable() && !ext.enabledForDebug) ||
                    (!variant.buildType.isDebuggable() && !ext.enabledForRelease)) {
                logger.debug("AspectJ weaving is disabled for build type '${variant.buildType.name}'.")
                return
            }

            JavaCompile javaCompile = variant.javaCompile
            javaCompile.doLast {

                final def inPath = javaCompile.destinationDir.toString()
                final def destinationDir = inPath
                final def aspectPath = javaCompile.classpath.asPath
                final def classPath = aspectPath
                final def bootClassPath = project.android.bootClasspath.join(File.pathSeparator)

                String[] args = ["-showWeaveInfo", "-1.5",
                                 "-inpath", inPath,
                                 "-aspectpath", aspectPath,
                                 "-d", destinationDir,
                                 "-classpath", classPath,
                                 "-bootclasspath", bootClassPath]

                logger.debug "AspectJ compiler/weaver args: " + Arrays.toString(args)

                Main main = new Main()
                main.runMain(args, false)
            }
        }
    }
}
