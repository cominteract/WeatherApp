rootProject.name = "weather-app"
include(
    ":app",
    "utilities",
    ":ui",
    ":domain",
    ":data",
    ":local",
    ":network"
)

//include(
//    ":app",
//    ":ui",
//    "utilities"
//)

//include(
//    ":domain",
//    ":data",
//    ":local",
//    ":network"
//)
include(":domain")
include(":network")
include(":local")
include(":data")
include(":ui")
