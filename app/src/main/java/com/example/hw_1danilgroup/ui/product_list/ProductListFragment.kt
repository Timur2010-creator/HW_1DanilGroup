package com.example.hw_1danilgroup.ui.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hw_1danilgroup.data.api.RetrofitService
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.databinding.FragmentProductListBinding
import com.example.hw_1danilgroup.ui.product_list.adapter.ProductListAdapter
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private var adapter = ProductListAdapter {}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductListAdapter { product ->
            onClick(product)
        }
        binding.recyclerView.adapter = adapter
        loadProducts()
    }

    private fun onClick(product: ProductDto) {
        binding.progressBar.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val action = ProductListFragmentDirections
                    .actionProductListFragmentToProductDetailsFragment(product)

                findNavController().navigate(action)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.isVisible = false
            }
        }

    }

    private fun loadProducts() {
        binding.progressBar.isVisible = true

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val product: List<ProductDto> = RetrofitService.api.getAllProduct()
                adapter.submitList(product)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.isVisible = false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}