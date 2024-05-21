package com.muen.fruitninja.ui;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.muen.fruitninja.R;

public class MainActivity extends FragmentActivity implements MainMenuFragment.OnMainMenuButtonClicked, GameFragment.OnGameOver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_main);

	showMainMenu();
    }

	private void showMainMenu() {
	MainMenuFragment fragment = new MainMenuFragment();
	FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.fragment_container, fragment, "MainMenu");
	transaction.addToBackStack("MainMenu");
	transaction.commit();
    }

    @Override
    public void onPlayButtonClicked() {
	GameFragment gameFragment = new GameFragment();

	FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.fragment_container, gameFragment, "Game");
	transaction.addToBackStack("Game");
	transaction.commit();
    }

    @Override
    public void onGameOver(int score) {
	ResultsFragment resultsFragment = new ResultsFragment();
	resultsFragment.setScore(score);

	FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.fragment_container, resultsFragment, "Results");
	transaction.commit();
    }
    
    @Override
    public void onBackPressed() {
	if (getSupportFragmentManager().findFragmentByTag("Results") != null) {
	    getSupportFragmentManager().popBackStack();
	    showMainMenu();
	}
    }
}