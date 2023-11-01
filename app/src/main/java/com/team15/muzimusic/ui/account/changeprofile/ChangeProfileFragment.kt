package com.team15.muzimusic.ui.account.changeprofile

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.FileUtils
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.models.AccountUpdate
import com.team15.muzimusic.databinding.FragmentChangeProfileBinding
import com.team15.muzimusic.ui.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ChangeProfileFragment : DialogFragment() {

    private lateinit var binding: FragmentChangeProfileBinding
    private val viewModel by viewModels<ChangeProfileViewModel>()
    private val parentViewModel by viewModels<AccountViewModel>({ requireActivity() })

    private var account: Account? = null
    private var isAccountNameValid = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeProfileBinding.inflate(inflater, container, false)

        account = arguments?.getParcelable(Constants.Account)

        textWatchers()

        account?.let {
            if (it.avatar.isNotEmpty()) Picasso.get().load(it.avatar)
                .placeholder(R.drawable.ic_user).fit().into(binding.imgAvatar)
            binding.edAccountName.setText(it.accountName)

            viewModel.accountName = it.accountName
        }

        viewModel.isLoading.observe(this) {
            binding.btnSubmit.isEnabled = !it
            isCancelable = !it
        }

        viewModel.status.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(context, viewModel.message!!, Toast.LENGTH_SHORT).show()
                    parentViewModel.updateStatus.postValue(true)
                    dismiss()
                } else {
                    Toast.makeText(context, viewModel.message!!, Toast.LENGTH_SHORT).show()
                }
                viewModel.status.postValue(null)
            }
        }

        binding.btnSubmit.setOnClickListener {
            val modal = getCurrentModal()
            modal?.let { viewModel.updateAccount(it) }
        }

        return binding.root
    }

    private fun getCurrentModal(): AccountUpdate? {
        if (isAccountNameValid) {
            return AccountUpdate(
                account!!.idAccount,
                binding.edAccountName.text.toString().trim(),
                viewModel.avatarFile
            )
        }
        return null
    }

    private fun textWatchers() {
        binding.edAccountName.addTextChangedListener {
            if (it == null || it.toString().trim().isEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                isAccountNameValid = false
            } else {
                binding.tvError.visibility = View.GONE
                isAccountNameValid = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPickImage.setOnClickListener {
            try {
                fileChooser.launch("image/*")
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "Vui lòng cài đặt File Manager",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private var fileChooser: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        val file: File? = FileUtils.from(requireContext(), uri!!)
        file?.let {
            Picasso.get().load(it).resize(200, 200).into(binding.imgAvatar)
            viewModel.avatarFile = it
        }
    }
}