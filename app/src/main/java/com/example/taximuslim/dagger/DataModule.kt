package com.example.taximuslim.dagger

import com.example.taximuslim.data.repository.order.IOrderRepository
import com.example.taximuslim.data.repository.order.OrderRepo
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindOrderRepository(orderRepo: OrderRepo) : IOrderRepository
}