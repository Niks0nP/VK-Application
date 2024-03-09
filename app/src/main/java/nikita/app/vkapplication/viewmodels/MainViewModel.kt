package nikita.app.vkapplication.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nikita.app.vkapplication.application.App
import nikita.app.vkapplication.data.model.ParseInfo
import nikita.app.vkapplication.data.model.Product
import nikita.app.vkapplication.data.network.ApiService
import nikita.app.vkapplication.data.network.common.Common
import java.lang.Exception

class MainViewModel: ViewModel() {

    private lateinit var apiService: ApiService
    private val repositoryProducts = App.getInstance().repositoryProducts
    private val productLiveData = MutableLiveData<String?>()

    fun getRequestApi() {
        apiService = Common.apiService

        viewModelScope.launch {
            try {
                val response = apiService.getProducts()
                repositoryProducts.setParseInfo(response)
                productLiveData.value = response.total.toString()
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

}