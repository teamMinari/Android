import android.content.Context
import com.nohjason.minari.screens.login.UI.SingUpPOST
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://cheong.baekjoon.kr/"

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    fun createService(context: Context): SingUpPOST {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SingUpPOST::class.java)
    }
}

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = TokenManager.getAccessToken(context)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}