package nikita.app.vkapplication.data.network.common

import nikita.app.vkapplication.data.network.ApiService
import nikita.app.vkapplication.data.network.RetrofitClient

object Common {

    private const val BASE_URL = "https://dummyjson.com/"
    val apiService: ApiService
        get() = RetrofitClient.provideClient(BASE_URL).create(ApiService::class.java)

}