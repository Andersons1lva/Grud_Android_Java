package com.anderson.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.anderson.mysubscribers.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscriber()

     fun getAllSubscriber(): LiveData<List<SubscriberEntity>>
}