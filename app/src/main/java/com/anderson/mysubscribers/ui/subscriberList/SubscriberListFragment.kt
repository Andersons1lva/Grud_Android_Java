package com.anderson.mysubscribers.ui.subscriberList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anderson.mysubscribers.R
import com.anderson.mysubscribers.databinding.FragmentSubscriberListBinding

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {

    private var _binding: FragmentSubscriberListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriberListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}