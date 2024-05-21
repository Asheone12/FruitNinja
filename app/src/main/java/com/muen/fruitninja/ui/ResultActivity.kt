package com.muen.fruitninja.ui

import android.content.Intent
import com.muen.fruitninja.R
import com.muen.fruitninja.databinding.ActivityResultBinding
import com.muen.fruitninja.util.BaseActivity

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    override fun onCreateViewBinding(): ActivityResultBinding {
        return ActivityResultBinding.inflate(layoutInflater)
    }

    override fun initData() {
        super.initData()
        val intent = intent
        val score = intent.getIntExtra("score",0)
        viewBinding.tvScore.text = getString(R.string.game_end_score, score.toString())
    }
    override fun initListener() {
        super.initListener()
        viewBinding.btnBack.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}