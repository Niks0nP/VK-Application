package nikita.app.vkapplication.data.network

import nikita.app.vkapplication.data.model.ParseInfo
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts() : ParseInfo

}