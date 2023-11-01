package com.team15.muzimusic.ui.admin.account

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.AccountLockAdapter
import com.team15.muzimusic.adapter.AccountLockListener
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.databinding.ActivityLockAccountBinding
import com.team15.muzimusic.ui.account.AccountActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LockAccountActivity : AppCompatActivity(), AccountLockListener {

    private lateinit var binding: ActivityLockAccountBinding
    private val viewModel by viewModels<LockAccountViewModel>()

    private lateinit var accountAdapter: AccountLockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupRecyclerAccount()
        observers()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocksAccount()
    }

    private fun observers() {
        viewModel.lockAccounts.observe(this) {
            accountAdapter.differ.submitList(it)

            if (it.isEmpty()) {
                binding.tvAlert.visibility = View.VISIBLE
            } else {
                binding.tvAlert.visibility = View.GONE
            }
        }

        viewModel.status.observe(this) {
            it?.let {
                if (it) {
                    viewModel.getLocksAccount()
                    Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.status.postValue(null)
            }
        }
    }

    private fun setupRecyclerAccount() {
        accountAdapter = AccountLockAdapter(this)
        binding.rvAccount.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onAccountClick(account: Account) {
        startActivity(Intent(this, AccountActivity::class.java).apply {
            putExtra(Constants.Account, account)
        })
    }

    override fun onAccountUnlock(account: Account) {
        viewModel.unlockAccount(account)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}