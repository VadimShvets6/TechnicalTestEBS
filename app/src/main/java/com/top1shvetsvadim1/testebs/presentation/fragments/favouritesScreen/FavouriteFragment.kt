package com.top1shvetsvadim1.testebs.presentation.fragments.favouritesScreen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.top1shvetsvadim1.testebs.R
import com.top1shvetsvadim1.testebs.databinding.FragmentFavouriteBinding
import com.top1shvetsvadim1.testebs.databinding.FragmentMainScreenBinding
import com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater.FavoriteAdapter
import com.top1shvetsvadim1.testebs.presentation.adapters.mainScreenAdapter.ProductAdapter
import java.lang.RuntimeException

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteBinding == null")

    private lateinit var mProductAdapter : FavoriteAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.menu.findItem(R.id.action_favourite).setIcon(R.drawable.ic_heart_favorites)
        setupRecyclerView()
        setupOnClickListeners()
        viewModelSubscription()
    }

    private fun setupOnClickListeners() {
        mProductAdapter.onRemoveFromFavouriteClickListeners = {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Удаление из избранного")
                setMessage("Вы действительно хотите удалить элемент из избранного?")
                setPositiveButton("Да"
                ) { dialog, which ->
                    viewModel.deleteFromFavorite(it)
                    Toast.makeText(requireContext(), "Удаленно из избранного", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("Нет"
                ) { dialog, which ->
                    dialog.dismiss()
                }
            }.show()
        }
        mProductAdapter.onProductItemClickListeners = {
            findNavController().navigate(
                FavouriteFragmentDirections.actionFavouriteFragmentToProductDetailFragment(it.id)
            )
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun viewModelSubscription() {
        viewModel.shopList.observe(viewLifecycleOwner) {
            mProductAdapter.submitList(it)
            binding.tvListSize.text = it.size.toString()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvList) {
            mProductAdapter = FavoriteAdapter()
            adapter = mProductAdapter
        }
    }

    companion object {

    }
}