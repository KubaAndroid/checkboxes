package jw.adamiak.checkboxlistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jw.adamiak.checkboxlistcompose.ui.theme.CheckboxListComposeTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CheckboxListComposeTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {

					var myItems by remember {
						mutableStateOf(
							(0..20).map {
								MyListItem(
									title = "Item $it",
									isSelected = false
								)
							}
						)
					}

					LazyColumn(
						modifier = Modifier
							.fillMaxSize()
					) {
						items(myItems.size) { i ->
							Row(
								modifier = Modifier
									.fillMaxWidth()
									.clickable {
										myItems = myItems.mapIndexed { j, item  ->
											if (i == j) {
												item.copy(isSelected = !item.isSelected)
											} else item
										}
									}
									.padding(16.dp),
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically
							) {
								Text(text = myItems[i].title)
								if(myItems[i].isSelected) {
									Icon(
										imageVector = Icons.Default.Check,
										contentDescription = "Selected",
										tint = Color.Green,
										modifier = Modifier.size(20.dp)
									)
								}
							}
						}
					}

				}
			}
		}
	}
}


@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CheckboxListComposeTheme {
		Greeting("Android")
	}
}



data class InterestItem(
	val id: Int,
	val name: String,
	val category: String,
	val isSelected: Boolean = false
)

val fakeData = listOf(
	InterestItem(1, "beer", "other"),
	InterestItem(2, "tennis", "sport"),
	InterestItem(3, "knitting", "other"),
	InterestItem(4, "kotlin", "programming"),
	InterestItem(5, "cars", "sport"),
	InterestItem(6, "cats", "animals"),
	InterestItem(7, "dogs", "animals")
)

