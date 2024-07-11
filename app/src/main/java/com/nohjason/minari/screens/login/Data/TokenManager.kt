import android.content.Context

object TokenManager {
    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"

    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
        val sharedPreferences = context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken)
        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
        editor.apply()
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun getRefreshToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }
}
