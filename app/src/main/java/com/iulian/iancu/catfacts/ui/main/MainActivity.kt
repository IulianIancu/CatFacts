package com.iulian.iancu.catfacts.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.iulian.iancu.catfacts.R
import com.iulian.iancu.catfacts.data.CatFactsRepository
import com.iulian.iancu.catfacts.data.CatImageRepository
import com.iulian.iancu.catfacts.data.CatService
import com.iulian.iancu.catfacts.ui.MainViewModelFactory
import com.iulian.iancu.catfacts.ui.theme.CatFactsTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    var catFacts = emptyList<Pair<String, String>>()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                CatFactsRepository(CatService.getInstance()),
                CatImageRepository()
            )
        )[MainViewModel::class.java]

        viewModel.state.observe(this, ::onStateChange)
        setContent {
            Content()
        }
        viewModel.getCatFacts()
    }


    private fun onStateChange(state: State?) {
        if (state == null) return
        when (state.error) {
            Error.Network ->
                Toast.makeText(this, R.string.cat_error_network, Toast.LENGTH_SHORT).show()
            Error.Unknown ->
                Toast.makeText(this, R.string.cat_error_code, Toast.LENGTH_SHORT).show()
        }

        state.catList?.let { catFacts = it }

        setContent {
            Content()
        }
    }

    @Preview
    @Composable
    fun Content() {
        CatFactsTheme {
            Surface(color = MaterialTheme.colors.background) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                ) {
                    items(catFacts) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Box(Modifier.fillMaxSize()) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp),
                                        placeholder = painterResource(R.drawable.ic_t_pose),
                                        model = it.first,
                                        contentDescription = null,
                                        alignment = Alignment.Center
                                    )
                                    Text(text = it.second, Modifier.padding(4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}