package com.top1shvetsvadim1.testebs.presentation.fragments.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.testebs.R
import com.top1shvetsvadim1.testebs.databinding.FragmentProductDetailBinding
import com.top1shvetsvadim1.testebs.presentation.Loading
import java.util.*

class ProductDetailFragment : Fragment() {

    private val args by navArgs<ProductDetailFragmentArgs>()

    private val viewModel by lazy {
        ViewModelProvider(this)[ProductViewModel::class.java]
    }

    private var _binding: FragmentProductDetailBinding? = null
    private val binding: FragmentProductDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentProductDetailBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMethods()
        viewModelSubscriptions()
        launchFavoriteFragment()
        myBackPressed()
    }

    private fun myBackPressed() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun launchFavoriteFragment(){
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_favourite -> {
                    findNavController().navigate(R.id.action_productDetailFragment_to_favouriteFragment)
                    true
                } else -> {
                    false
                }
            }
        }
    }

    private fun viewModelMethods(){
        viewModel.getProductById(args.id)
    }

    private fun viewModelSubscriptions(){
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is Loading -> {
                    if(it.isLoading){
                        binding.progressBar.visibility = View.VISIBLE
                    } else if(!it.isLoading){
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        viewModel.product.observe(viewLifecycleOwner){
            with(binding) {
                Picasso.get().load(it.mainImage).into(ivLogo)
                tvProductName.text = it.name
                tvDescription.text = it.details
                tvSize.text = it.size
                tvPrice.text = String.format(Locale.getDefault(), "$ %d,-", it.price)
                tvSmallPrice.text = String.format(Locale.getDefault(), "$ %d,-", it.price)
            }
        }
    }

    companion object {
    }
}