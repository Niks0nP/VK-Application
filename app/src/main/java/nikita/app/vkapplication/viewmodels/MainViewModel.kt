package nikita.app.vkapplication.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nikita.app.vkapplication.application.App
import nikita.app.vkapplication.data.model.Product
import nikita.app.vkapplication.data.network.ApiService
import nikita.app.vkapplication.data.network.common.Common
import java.lang.Exception

class MainViewModel: ViewModel() {

    private lateinit var apiService: ApiService
    private val repositoryProducts = App.getInstance().repositoryProducts
    private val productLiveData = MutableLiveData<String?>()
    private var num: Int = 0

//    val products: Flow<PagingData<Product>> = Pager(
//        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//        pagingSourceFactory = {ProductPageSource(Common.apiService, 0)}
//    ).flow
//        .cachedIn(viewModelScope)


    fun getRequestApi() {
        apiService = Common.apiService

        viewModelScope.launch {
            try {
                if (num == 0) {
                    val response = apiService.getProducts(0)
                    repositoryProducts.setParseInfo(response)
                    productLiveData.value = response.total.toString()
                    num += 1
                }
                else {
                    val response = apiService.getProducts(getSkip())
                    repositoryProducts.setParseInfo(response)
                    productLiveData.value = response.total.toString()
                }


            }
            catch (e: Exception) {
                Log.e("TAG", "Exception after request -> ${e.localizedMessage}")
            }
        }

    }

    fun getListProducts() : List<Product>? {
        return repositoryProducts.getParseInfo().products
    }

    fun getLiveData() : LiveData<String?> {
        return productLiveData
    }

    private fun getSkip() : Int {
        Log.e("TAG", "Skip = ${repositoryProducts.getSkip()}")
        return repositoryProducts.getSkip()
    }

}