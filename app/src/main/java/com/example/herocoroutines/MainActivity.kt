package com.example.herocoroutines

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragment) as ListFragment

        listFragment.setItemClickListener {
            val detailsFragment = supportFragmentManager.findFragmentById(R.id.detailsFragment) as? DetailsFragment
            var bundle = Bundle()
            bundle.putParcelable(PARCELABLE_VALUE, it)

            if(detailsFragment != null){
                detailsFragment.description = it.biography.fullName
                detailsFragment.show()
            } else{
                val detailsFragment = DetailsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.listFragment, DetailsFragment::class.java, bundle)
                    .addToBackStack("details_fragment")
                    .commit()
            }
        }
    }
    companion object{
        const val PARCELABLE_VALUE = "parcelableValue"
    }
}

