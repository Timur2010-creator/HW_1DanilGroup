package com.example.hw_1danilgroup.ui.fragments.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.hw_1danilgroup.data.datasource.RetrofitService
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.databinding.FragmentProductDetailsBinding
import com.example.hw_1danilgroup.ui.models.UiState
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductsDetailsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        binding.btnCancel.setOnClickListener { view ->
            parentFragmentManager.popBackStack()
        }
    }

    fun loadData(){

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val productData: ProductDto = args.productDto
                val productRating = productData.ratingDto
                binding.imgProduct.load(productData.image)
                binding.tvTitle.text = productData.title
                binding.tvDescription.text = productData.description
                binding.tvPrice.text = productData.price.toString()
                binding.tvRating.text = "${productRating.rate} (${productRating.count})"
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                _binding?.progressBar?.isVisible = false
            }
        }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect { state ->
                    when (state){
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.llFrame.isVisible = false
                        }
                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.llFrame.isVisible = true
                            binding.imgProduct.load(state.data.image)
                            binding.tvTitle.text = state.data.title
                            binding.tvDescription.text = state.data.description
                            binding.tvPrice.text = state.data.price.toString()
                            binding.tvRating.text = "${state.data.rating.rate} (${state.data.rating.count})"
                        }
                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.llFrame.isVisible = false
                            Toast.makeText(requireContext(),state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}