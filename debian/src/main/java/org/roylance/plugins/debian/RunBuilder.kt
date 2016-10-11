package org.roylance.plugins.debian

import java.io.File

class RunBuilder {
    fun build(projectName: String, fileLocation: String) {
        val template = """#!/usr/bin/env bash
nohup /opt/$projectName/bin/$projectName "$@" > latest.out 2>&1&
"""

        File(fileLocation).delete()
        File(fileLocation).writeText(template)
    }
}