package nikita.app.vkapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import nikita.app.vkapplication.R
import nikita.app.vkapplication.data.model.Product

class ProductAdapter :
    ListAdapter<Product, ProductAdapter.ProductHolder>(DiffUtilCallback) {

    class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun setItemInfo(product: Product) {
            val title = itemView.findViewById<TextView>(R.id.title)
            val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
            val description = itemView.findViewById<TextView>(R.id.description)

            title.text = product.title
            description.text = product.description
            with(itemView) {
                Glide.with(context)
                    .load(product.thumbnail)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                    .into(thumbnail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = getItem(position)
        ProductHolder(holder.itemView).setItemInfo(item!!)
    }


    object DiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}