package com.sps.croppricetracker.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sps.croppricetracker.R
import com.sps.croppricetracker.ui.components.SearchTextField
import com.sps.croppricetracker.ui.components.TopNav

val sampleCropList = listOf(
    Crop(
        id = 1,
        name = "Tomato",
        imageResId = R.drawable.tomato_300,
        description = "Tomato is a popular crop known for its juicy and nutritious fruits."
    ),
    Crop(
        id = 2,
        name = "Corn",
        imageResId = R.drawable.tomato_300,
        description = "Corn is a versatile crop used in various culinary dishes and animal feed."
    ),
    Crop(
        id = 3,
        name = "Lettuce",
        imageResId = R.drawable.tomato_300,
        description = "Lettuce is a leafy green crop commonly used in salads and sandwiches."
    ),
    Crop(
        id = 4,
        name = "Potato",
        imageResId = R.drawable.tomato_300,
        description = "Potato is a starchy root vegetable widely consumed around the world."
    ),
    Crop(
        id = 5,
        name = "Wheat",
        imageResId = R.drawable.tomato_300,
        description = "Wheat is a cereal crop commonly used for making flour and bread."
    ),
    // Add more sample crops as needed
    Crop(
        id = 20,
        name = "Cabbage",
        imageResId = R.drawable.tomato_300,
        description = "Cabbage is a leafy vegetable often used in salads, soups, and stir-fries."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            TopNav(title = "Home")
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)) {
            SearchTextField(
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        null,
                        tint = LocalContentColor.current.copy(alpha = 0.4f),
                        modifier = Modifier.padding(end = 4.dp)
                    )
                },
                trailingIcon = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .height(50.dp),
                placeholderText = "Search for crops",
                value = "",
                onTextChange = {}
            )
            CropGridList(sampleCropList)
        }
    }
}


@Composable
fun CropGridList(crops: List<Crop>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        items(crops) { crop ->
            CropItem(crop)
        }
    }
}


@Composable
fun CropItem(crop: Crop) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
//        elevation =
    ) {
        Column(modifier = Modifier.padding(0.dp)) {
            Image(
                painter = painterResource(crop.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = crop.name,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

data class Crop(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val description: String,
    // Add more properties specific to your crop model
)