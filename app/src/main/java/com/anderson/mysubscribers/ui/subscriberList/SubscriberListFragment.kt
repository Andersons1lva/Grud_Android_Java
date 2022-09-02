package com.anderson.mysubscribers.ui.subscriberList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anderson.mysubscribers.R
import com.anderson.mysubscribers.data.db.AppDatabase
import com.anderson.mysubscribers.data.db.dao.SubscriberDAO
import com.anderson.mysubscribers.databinding.SubscriberListFragmentBinding
import com.anderson.mysubscribers.repository.DatabaseDataSource
import com.anderson.mysubscribers.repository.SubscriberRepository

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO = AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {_binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        return binding.root
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscriberEvent.observe(viewLifecycleOwner){ allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers)
            with(binding.recyclerSubscribers) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}