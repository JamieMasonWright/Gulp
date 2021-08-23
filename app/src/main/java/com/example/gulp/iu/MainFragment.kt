package com.example.gulp.iu

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gulp.R
import com.example.gulp.data.DataSource
import com.example.gulp.data.model.Drink
import com.example.gulp.domain.RepoImpl
import com.example.gulp.iu.viewmodel.MainViewModel
import com.example.gulp.iu.viewmodel.ViewModelFactory
import com.example.gulp.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),MainAdapter.OnDrinkClickListener {

    private val viewModel by viewModels<MainViewModel> { ViewModelFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupDefaultDrinksList()
    }

    private fun setupDefaultDrinksList(){
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_drink.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainFragment", "onRetrofitRequest: ${result.exception}")
                    Toast.makeText(requireContext(), "An error has occured ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.fetchAlcoholicFilter.observe(viewLifecycleOwner, { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_drink.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainFragment", "onRetrofitRequest: ${result.exception}")
                    Toast.makeText(requireContext(), "An error has occured ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {return false}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.filter_no_alcohol -> {
                viewModel.setAlcoholicOrNotFilter("Non_Alcoholic")
                false
            }

            R.id.filter_with_alcohol-> {
                viewModel.setAlcoholicOrNotFilter("Alcoholic")
                false
            }
            else -> {
                false
            }
        }
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.drinkDetailFragment,bundle)
    }

    private fun setupRecyclerView(){
        rv_drink.layoutManager = LinearLayoutManager(requireContext())
        rv_drink.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
}