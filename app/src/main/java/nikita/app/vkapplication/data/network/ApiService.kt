package nikita.app.vkapplication.data.network

import nikita.app.vkapplication.data.model.ParseInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products?skip=20&limit=20")
    suspend fun getProducts() : ParseInfo

//@Path("skipElements") skipElements: Int
}