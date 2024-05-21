package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.databinding.ActivityMainBinding
import com.muen.fruitninja.util.BaseActivity
import com.muen.fruitninja.util.GameConf

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        GameConf.screenWith = resources.displayMetrics.widthPixels
        GameConf.screenHeight = resources.displayMetrics.heightPixels
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnStart.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
    }
}