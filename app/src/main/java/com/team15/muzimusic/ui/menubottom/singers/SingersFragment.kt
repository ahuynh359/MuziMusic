package com.team15.muzimusic.ui.menubottom.singers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.adapter.AccountAdapter
import com.team15.muzimusic.adapter.ItemAccountClickListener
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.databinding.FragmentSingersBinding
import com.team15.muzimusic.ui.account.AccountActivity

class SingersFragment : BaseDialogFragment(), ItemAccountClickListener {

    private lateinit var binding: FragmentSingersBinding
    private lateinit var accountAdapter: AccountAdapter

    private var singers: ArrayList<Account> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        singers =
            arguments?.getParcelableArrayList<Account>(Constants.Singers) as ArrayList<Account>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountAdapter = AccountAdapter(this)
        accountAdapter.differ.submitList(singers)

        binding.recyclerAccount.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(this@SingersFragment.context)
        }
    }

    override fun onAccountClick(account: Account) {
        startActivity(Intent(context, AccountActivity::class.java).apply {
            putExtra(Constants.Account, account)
        })
    }

}