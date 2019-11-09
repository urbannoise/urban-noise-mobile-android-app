rootProject.buildFileName = "build.gradle.kts"

include(
    BuildModules.APP,
    BuildModules.CORE,
    BuildModules.Features.SPLASHSCREEN,
    BuildModules.Features.TUTORIAL,
    BuildModules.Features.DASHBOARD,
    BuildModules.Libraries.DATABASE,
    BuildModules.Libraries.NETWORK
)
