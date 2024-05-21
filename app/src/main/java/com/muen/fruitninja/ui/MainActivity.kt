package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.BuildConfig
import com.muen.fruitninja.R
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
        viewBinding.tvVersion.text = getString(R.string.game_version, BuildConfig.VERSION_NAME)
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnStart.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnEnd.setOnClickListener {
            finish()
        }
    }
}