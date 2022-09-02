package com.anderson.mysubscribers.ui.subscriberList

import androidx.lifecycle.ViewModel
import com.anderson.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(private val repository: SubscriberRepository)
    : ViewModel() {

        val allSubscriberEvent = repository.getAllSubscriber()
}