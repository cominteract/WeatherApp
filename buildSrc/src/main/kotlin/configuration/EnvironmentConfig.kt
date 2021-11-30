package configuration

object Staging {
    const val WEATHER_API_KEY = "7c2665cf52cf6d2f494cbed2c17ae423"
    const val SERVER_PREFIX = "https://api.openweathermap.org/data/2.5/"
}

object Production {
    const val WEATHER_API_KEY = "7c2665cf52cf6d2f494cbed2c17ae423"
    const val SERVER_PREFIX = "https://api.openweathermap.org/data/2.5/"

}

object BuildConfigKeys {
    const val API_BASE_URL = "API_BASE_URL"
    const val API_KEY = "API_KEY"
}