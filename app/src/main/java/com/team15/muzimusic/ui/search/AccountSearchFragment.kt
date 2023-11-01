package com.team15.muzimusic.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.adapter.AccountAdapter
import com.team15.muzimusic.adapter.ItemAccountClickListener
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.databinding.FragmentAccountSearchBinding
import com.team15.muzimusic.ui.account.AccountActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountSearchFragment : Fragment(), ItemAccountClickListener {

    private lateinit var binding: FragmentAccountSearchBinding
    private val viewModel by viewModels<SearchViewModel>({ requireActivity() })

    private lateinit var accountAdapter: AccountAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountSearchBinding.inflate(inflater, container, false)

        accountAdapter = AccountAdapter(this)
        binding.recyclerView.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.accounts.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvNoResult.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                accountAdapter.differ.submitList(it)

                binding.tvNoResult.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onAccountClick(account: Account) {
        startActivity(Intent(context, AccountActivity::class.java).apply {
            putExtra(Constants.Account, account)
        })
    }

}