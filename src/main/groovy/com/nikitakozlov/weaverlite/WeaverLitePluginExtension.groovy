package com.nikitakozlov.weaverlite

class WeaverLitePluginExtension {
    def enabledForDebug = true
    def enabledForRelease = false

    def getEnabledForDebug() {
        return enabledForDebug
    }

    void setEnabledForDebug(enabledForDebug) {
        this.enabledForDebug = enabledForDebug
    }

    def getEnabledForRelease() {
        return enabledForRelease
    }

    void setEnabledForRelease(enabledForRelease) {
        this.enabledForRelease = enabledForRelease
    }
}
