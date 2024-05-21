package com.muen.fruitninja.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.View.OnTouchListener
import androidx.collection.SparseArrayCompat
import com.muen.fruitninja.util.FruitProjectileManager
import com.muen.fruitninja.util.GameThread
import com.muen.fruitninja.util.ProjectileManager
import com.muen.fruitninja.util.TimedPath

class GameView : SurfaceView, OnTouchListener, SurfaceHolder.Callback {
    private var gameThread: GameThread? = null
    private var projectileManager: ProjectileManager? = null
    private var gameOverListener: GameFragment.OnGameOver? = null
    private var isGameInitialised = false
    private val paths = SparseArrayCompat<TimedPath>()

    constructor(context: Context?) : super(context) {
        initialise()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialise()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initialise()
    }

    private fun initialise() {
        setOnTouchListener(this)
        this.isFocusable = true
        this.holder.addCallback(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> createNewPath(event.x, event.y, event.getPointerId(0))
            MotionEvent.ACTION_POINTER_DOWN -> {
                val newPointerIndex = event.actionIndex
                createNewPath(
                    event.getX(newPointerIndex),
                    event.getY(newPointerIndex),
                    event.getPointerId(newPointerIndex)
                )
            }

            MotionEvent.ACTION_MOVE -> {
                var i = 0
                while (i < paths.size()) {
                    val pointerIndex = event.findPointerIndex(paths.indexOfKey(i))
                    if (pointerIndex >= 0) {
                        paths.valueAt(i).lineTo(event.getX(pointerIndex), event.getY(pointerIndex))
                        paths.valueAt(i).updateTimeDrawn(System.currentTimeMillis())
                    }
                    i++
                }
            }
        }
        gameThread!!.updateDrawnPath(paths)
        return true
    }

    private fun createNewPath(x: Float, y: Float, ptrId: Int) {
        val path = TimedPath()
        path.moveTo(x, y)
        path.updateTimeDrawn(System.currentTimeMillis())
        paths.append(ptrId, path)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (isGameInitialised) {
            gameThread!!.resumeGame(width, height)
        } else {
            isGameInitialised = true
            projectileManager = FruitProjectileManager(resources)
            gameThread = GameThread(getHolder(), projectileManager, gameOverListener)
            gameThread!!.startGame(width, height)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        gameThread!!.pauseGame()
    }

    fun setGameOverListener(gameOverListener: GameFragment.OnGameOver?) {
        this.gameOverListener = gameOverListener
    }
}
