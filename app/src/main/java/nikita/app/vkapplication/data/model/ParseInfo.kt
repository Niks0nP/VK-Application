package nikita.app.vkapplication.data.model

import com.google.gson.annotations.SerializedName

data class ParseInfo(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<Product>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
)