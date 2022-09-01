package com.anderson.mysubscribers.ui.subscriberList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anderson.mysubscribers.R
import com.anderson.mysubscribers.data.db.entity.SubscriberEntity
import com.anderson.mysubscribers.databinding.FragmentSubscriberListBinding

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {

    private var _binding: FragmentSubscriberListBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding =
            FragmentSubscriberListBinding.bind(view)  //if the view is already inflated then we can just bind it to view binding.

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Anderson", "Andersons1lva@yahoo.com.br"),
                SubscriberEntity(2, "Bryan", "Bryan@yahoo.com.br")
            )
        )

        binding.recyclerSubscribers.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }
    }

    //Note: Fragments outlive their views. Make sure you clean up any references to the binding class
    // instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        Toast.makeText(activity, "On destroy", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
        _binding = null
    }
}