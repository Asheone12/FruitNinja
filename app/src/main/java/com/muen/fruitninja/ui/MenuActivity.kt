package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.databinding.ActivityMenuBinding
import com.muen.fruitninja.util.BaseActivity

class MenuActivity : BaseActivity<ActivityMenuBinding>() {
    override fun onCreateViewBinding(): ActivityMenuBinding {
        return ActivityMenuBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnStart.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
    }
}