package com.example.herocoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class ListFragment:Fragment() {
    private var onItemClick: (Hero) -> Unit ={}
    private lateinit var viewModel:MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_layout, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myAdapter = RecyclerViewAdapter(onItemClick = {})
        val listRecycler: RecyclerView = view.findViewById(R.id.listRecycler)
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getData()

        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            when(uiState) {
                is MyViewModel.UiState.Empty -> Unit
                is MyViewModel.UiState.Result -> {

                    val items = uiState.items
                    myAdapter = RecyclerViewAdapter(items as MutableList<Hero>){
                        onItemClick(it)
                    }
                    listRecycler.adapter = myAdapter
                }
                is MyViewModel.UiState.Error -> { }
            }
        }
        listRecycler.layoutManager = LinearLayoutManager(requireContext())
        listRecycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.END)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromIndex = viewHolder.adapterPosition
                val toIndex = target.adapterPosition
                Collections.swap(myAdapter.items, fromIndex, toIndex)
                myAdapter.notifyItemMoved(fromIndex,toIndex)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if(direction == ItemTouchHelper.END){
                    myAdapter.items.removeAt(viewHolder.adapterPosition)
                    myAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }

        })

        itemTouchHelper.attachToRecyclerView(listRecycler)
    }
    fun setItemClickListener(lambda: (Hero) -> Unit){
        onItemClick = lambda
    }
}