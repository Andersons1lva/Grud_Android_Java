package com.anderson.mysubscribers.ui.subscriberList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anderson.mysubscribers.R
import com.anderson.mysubscribers.data.db.AppDatabase
import com.anderson.mysubscribers.databinding.SubscriberListFragmentBinding
import com.anderson.mysubscribers.extension.navigateWithAnimations
import com.anderson.mysubscribers.repository.DatabaseDataSource
import com.anderson.mysubscribers.repository.SubscriberRepository

@Suppress("UNCHECKED_CAST")
class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding: SubscriberListFragmentBinding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDao = AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberListViewModel(repository) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()
    }


    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers).apply {
                onItemClick = { subscriber ->
                    val directions = SubscriberListFragmentDirections
                        .actionSubscriberListFragmentToSubscriberFragment(subscriber)

                    findNavController().navigateWithAnimations(directions)
                }
            }

            with(binding.recyclerSubscribers) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscriber()
    }

    private fun configureViewListeners() {
        binding.fabAddSubscriber.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.subscriberFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}