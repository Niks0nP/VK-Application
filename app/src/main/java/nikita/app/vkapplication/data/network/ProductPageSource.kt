package nikita.app.vkapplication.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nikita.app.vkapplication.data.model.Product
import java.lang.Exception

class ProductPageSource(
    private val apiService: ApiService,
    private var skipElement: Int
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        val pageSize: Int = 20

        if (skipElement == 0) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val response = apiService.getProducts(skipElement)

        if (response.total != 0) {
            val products = checkNotNull(response.products)
            val nextKey = if (products.size < pageSize) null else skipElement + 20
            val prevKey = if (skipElement == 0) null else skipElement - 20

            return LoadResult.Page(products, prevKey, nextKey)
        }
        else {
            return LoadResult.Error(Exception(response.toString()))
        }

    }

}