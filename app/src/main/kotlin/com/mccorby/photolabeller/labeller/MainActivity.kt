package com.mccorby.photolabeller.labeller

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mccorby.executors.ExecutionContext
import com.mccorby.photolabeller.R
import com.mccorby.photolabeller.interactor.NoParams
import com.mccorby.photolabeller.interactor.UseCase
import com.mccorby.photolabeller.model.Stats
import com.mccorby.photolabeller.model.Trainer
import com.mccorby.photolabeller.repository.FederatedRepository
import com.mccorby.photolabeller.trainer.TrainingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnLabellingActionsListener {

    private var trainingAction: MenuItem? = null
    private var getModelAction: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        savedInstanceState ?: openFragment(MainFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_items, menu)
        menuInflater.inflate(R.menu.action_get_model, menu)
        trainingAction = menu!!.findItem(R.id.action_training)
        getModelAction = menu.findItem(R.id.action_get_model)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_training -> {
            openFragment(TrainingFragment.newInstance())
            true
        }
        else -> false
    }

    override fun onModelLoaded() {
        trainingAction?.isVisible = true
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun getModelFromServer(view: View) {
        println("test1234")
    }
}
