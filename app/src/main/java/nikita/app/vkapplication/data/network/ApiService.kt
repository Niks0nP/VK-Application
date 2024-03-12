package nikita.app.vkapplication.data.network

import nikita.app.vkapplication.data.model.ParseInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products?&limit=20")
    suspend fun getProducts(@Query("skip") skipElements: Int) : ParseInfo

}