package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.databinding.ActivityMainBinding
import com.muen.fruitninja.util.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnStart.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
    }
}