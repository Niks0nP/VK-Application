package nikita.app.vkapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import nikita.app.vkapplication.data.model.Product
import nikita.app.vkapplication.databinding.FragmentMainBinding
import nikita.app.vkapplication.view.adapter.ProductAdapter
import nikita.app.vkapplication.viewmodels.MainViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter()
        binding.recyclerViewContainer.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewContainer.adapter = productAdapter

//        val products = mainViewModel.products
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                products.collectLatest {
//                    productAdapter.submitData(it)
//                }
//            }
//        }

        mainViewModel.getRequestApi()
        mainViewModel.getLiveData().observe(viewLifecycleOwner) { value ->
            if (value != null) {
                val listProducts = mainViewModel.getListProducts()
                showProducts(listProducts)
            }
        }

        binding.showMoreButton.setOnClickListener {
            mainViewModel.getRequestApi()
            showProducts(mainViewModel.getListProducts())
        }
    }

    private fun showProducts(listProducts: List<Product>?) {
        productAdapter.submitList(listProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}