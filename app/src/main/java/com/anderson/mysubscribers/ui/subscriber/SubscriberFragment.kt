package com.anderson.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anderson.mysubscribers.R
import com.anderson.mysubscribers.data.db.AppDatabase
import com.anderson.mysubscribers.data.db.dao.SubscriberDAO
import com.anderson.mysubscribers.databinding.SubscriberFragmentBinding
import com.anderson.mysubscribers.extension.hideKeyboard
import com.anderson.mysubscribers.repository.DatabaseDataSource
import com.anderson.mysubscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {

    private var _binding: SubscriberFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDatabase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when(subscriberState){
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) {stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.inputName.text?.clear()
        binding.inputEmail.text?.clear()
    }


    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.btnSubscriber.setOnClickListener{
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }


}