package com.example.taximuslim.presentation.view.auth.driver.rules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DriveAuthRuleViewModel : ViewModel() {
    val ruleCaption = MutableLiveData<String>()
    val ruleDescription = MutableLiveData<String>()

    fun nextRule(){
        ruleCaption.value = "Водитель обязан точно соблюдать все правила дорожного движения"
        ruleDescription.value = "Пророк (мир ему и благословение) говорил: «Вы лучше знаете ваши мирские дела» (Муслим). Этот хадис разъясняет нам, что если мы в жизни являемся специалистами в какой-то области, то мы должны следовать принятым нормам и советам более компетентных людей. Специалисты выработали правила дорожного движения, и они прошли практику, поэтому водители обязаны соблюдать эти правила. Если же шофёрих нарушит, то будет вынужден отвечать за это как перед правоохранительными органами, так и перед Аллахом"
    }
}
