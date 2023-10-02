package com.example.herocoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class DetailsFragment:Fragment() {

    var fullName: TextView? = null
    var alterEgos: TextView? = null
    var placeOfBirth: TextView? = null
    var firstAppearance: TextView? = null
    var publisher: TextView? = null
    var alignment: TextView? = null
    var aliases: TextView? = null
    var description = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hero = arguments?.getParcelable<Hero>(MainActivity.PARCELABLE_VALUE)
        fullName = view.findViewById(R.id.fullName)
        alterEgos = view.findViewById(R.id.alterEgos)
        val detailsImage: ImageView = view.findViewById(R.id.detailsImage)
        placeOfBirth = view.findViewById(R.id.placeOfBirth)
        firstAppearance = view.findViewById(R.id.firstAppearance)
        publisher = view.findViewById(R.id.publisher)
        alignment = view.findViewById(R.id.alignment)
        aliases = view.findViewById(R.id.aliases)


        fullName?.text = "FULL NAME: ${hero?.biography?.fullName}"
        alterEgos?.text = "ALTER EGOS: ${hero?.biography?.alterEgos}"
        Glide.with(view).load(hero?.images?.lg).into(detailsImage)
        placeOfBirth?.text = "PLACE OF BIRTH: ${hero?.biography?.placeOfBirth}"
        firstAppearance?.text = "FIRST APPEARENCE: ${hero?.biography?.firstAppearance}"
        publisher?.text = "PUBLISHER: ${hero?.biography?.publisher}"
        alignment?.text = "ALIGNMENT: ${hero?.biography?.alignment}"
        aliases?.text = "ALIASES: "
        hero?.biography?.aliases?.forEach { name -> aliases?.append(" $name")}

    }
    fun show(){
        fullName?.text = description
    }

}