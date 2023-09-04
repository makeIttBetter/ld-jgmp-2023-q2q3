package com.builders.web

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {
    void apply(Project project) {
        def majorVersion = project.findProperty("majorVersion")
        def minorVersion = project.findProperty("minorVersion")
        def envDescription = project.hasProperty('envDescription') ? project.envDescription : 'LOCAL'

        project.tasks.whenTaskAdded { task ->
            if (task.name == 'war') {
                task.doFirst {
                    task.archiveVersion = "${majorVersion}.${minorVersion}-${envDescription}"
                }
            }
        }
    }
}

