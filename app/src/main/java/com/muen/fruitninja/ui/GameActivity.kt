package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.databinding.ActivityGameBinding
import com.muen.fruitninja.rxbus.event.GameOver
import com.muen.fruitninja.rxbus.rxBus
import com.muen.fruitninja.util.BaseActivity

class GameActivity : BaseActivity<ActivityGameBinding>() {
    override fun onCreateViewBinding(): ActivityGameBinding {
        return ActivityGameBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        rxBus<GameOver>{
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("score",it.score)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        return
    }
}