package com.top1shvetsvadim1.testebs.presentation.fragments.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.top1shvetsvadim1.testebs.R
import com.top1shvetsvadim1.testebs.databinding.FragmentMainScreenBinding
import com.top1shvetsvadim1.testebs.presentation.adapters.mainScreenAdapter.ProductAdapter
import kotlinx.coroutines.launch

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMainScreenBinding == null")

    private lateinit var mProductAdapter: ProductAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[MainScreenViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadingData()
        launchDetailFragment()
        launchFavouriteFragment()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        mProductAdapter.onAddToFavouriteClickListeners = {
            if (!it.isFavorite) {
                viewModel.addToFavorite(it)
                Toast.makeText(requireContext(), "Добавленое в избранное", Toast.LENGTH_SHORT)
                    .show()
            } else if (it.isFavorite) {
                viewModel.deleteFromFavorite(it)
                Toast.makeText(requireContext(), "Удалено из избранного", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun launchDetailFragment() {
        mProductAdapter.onProductItemClickListeners = {
            findNavController().navigate(
                MainScreenFragmentDirections
                    .actionMainScreenFragmentToProductDetailFragment(it.id)
            )
        }
    }

    private fun launchFavouriteFragment(){
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_favourite -> {
                    findNavController().navigate(R.id.action_mainScreenFragment_to_favouriteFragment)
                    true
                } else ->{
                    false
                }
            }
        }
    }


    private fun loadingData(){
        lifecycleScope.launch {
            viewModel.listData.collect{
                mProductAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvList) {
            mProductAdapter = ProductAdapter()
            adapter = mProductAdapter
        }
    }

    companion object {

    }
}