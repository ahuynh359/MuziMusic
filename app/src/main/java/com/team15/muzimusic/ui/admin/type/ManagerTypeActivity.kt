package com.team15.muzimusic.ui.admin.type

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.TypeManageAdapter
import com.team15.muzimusic.adapter.TypeManageListener
import com.team15.muzimusic.base.activities.BaseActivity
import com.team15.muzimusic.base.dialogs.ConfirmDialog
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.common.SimpleDividerItemDecoration
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.databinding.ActivityManagerTypeBinding
import com.team15.muzimusic.ui.admin.type.form_type.FormTypeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagerTypeActivity : BaseActivity(), TypeManageListener {

    private lateinit var binding: ActivityManagerTypeBinding
    private lateinit var typeAdapter: TypeManageAdapter

    private val viewModel by viewModels<ManageTypeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.getTypes()

        setupRecyclerType()
        observers()
    }

    private fun observers() {
        viewModel.types.observe(this) {
            typeAdapter.differ.submitList(it)
        }

        viewModel.status.observe(this) {
            it?.let {
                if (it) {
                    viewModel.getTypes()
                    Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show()
                }
                viewModel.status.postValue(null)
            }
        }
    }

    private fun setupRecyclerType() {
        typeAdapter = TypeManageAdapter(this)
        binding.rvType.apply {
            adapter = typeAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SimpleDividerItemDecoration(context, R.drawable.divider))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed()
            R.id.actionAdd -> {
                FormTypeFragment().show(supportFragmentManager, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTypeEdit(type: Type, position: Int) {
        FormTypeFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Type, type)
            }
        }.show(supportFragmentManager, null)
    }

    override fun onTypeDelete(type: Type, position: Int) {
        showConfirmDialog(
            "Xóa thể loại",
            "Bạn chắc chắn muốn xóa thể loại này?",
            "Xóa",
            "Hủy",
            "",
            object : ConfirmDialog.ConfirmCallback {
                override fun negativeAction() {
                }

                override fun positiveAction() {
                    viewModel.deleteType(type)
                }
            }
        )
    }

}