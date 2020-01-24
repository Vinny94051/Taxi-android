package com.example.taximuslim.dagger

import com.example.taximuslim.data.repository.AuthRepo
import com.example.taximuslim.domain.IAuthInteractor
import com.example.taximuslim.presentation.view.auth.fragments.daughter.AuthorizationFragment
import com.example.taximuslim.presentation.view.auth.fragments.daughter.EnterSmsCodeFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(target : AuthRepo)

    fun inject(target: IAuthInteractor)

    fun inject(target : AuthViewModel)

    fun inject(target: AuthorizationFragment)
    fun inject(enterSmsCodeFragment: EnterSmsCodeFragment)

}