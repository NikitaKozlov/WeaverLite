package com.nikitakozlov.weaver

class WeaverLitePluginExtension {
    def aspectjVersion = '1.8.6'
    def enabledForDebug = true
    def enabledForRelease = false

    def getAspectjVersion() {
        return aspectjVersion
    }

    void setAspectjVersion(aspectjVersion) {
        this.aspectjVersion = aspectjVersion
    }

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
