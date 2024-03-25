package com.hoholms.book.hohbooks.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hoholms.book.hohbooks.R
import com.hoholms.book.hohbooks.components.BookBottomSheet
import com.hoholms.book.hohbooks.components.BookCard
import com.hoholms.book.hohbooks.model.Book
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(navController: NavController, themeViewModel: ThemeViewModel = viewModel()) {
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var selectedBook by remember { mutableStateOf(Book()) }

    val books = listOf(
        Book(
            title = "The Alchemist",
            author = "Paulo Coelho",
            description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et ligula accumsan, lacinia eros non, tincidunt erat. Morbi finibus nulla ac lorem ultrices finibus et non tortor. Morbi laoreet tortor nunc, in mattis dui malesuada at. Suspendisse lobortis cursus lectus quis faucibus. Cras tincidunt lacinia sodales. Etiam et sem nisi. Suspendisse potenti.

Sed sit amet nisi ullamcorper, mollis lorem at, tempor tortor. Curabitur in dolor non ante commodo posuere eleifend et nisi. Proin a nunc vitae enim viverra faucibus. Praesent bibendum enim quam, commodo molestie risus cursus ac. Vivamus maximus fermentum dictum. Sed massa justo, ullamcorper id urna quis, malesuada maximus ligula. Aenean tincidunt elit id mollis lacinia.

Fusce nec est sed mi ultricies efficitur. Nunc tortor ligula, aliquet vitae purus vitae, elementum condimentum elit. Nulla sed elementum nunc, nec ultricies erat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Phasellus sed pellentesque nibh. Nam tristique elit sed semper suscipit. In hac habitasse platea dictumst. Nulla facilisi. Donec ac massa eget nibh egestas efficitur eu a tortor. Nulla a vehicula orci. Suspendisse in nibh nec diam egestas molestie. Nulla faucibus sem sit amet lorem mattis malesuada.

Fusce vehicula elit sed orci mollis suscipit. Pellentesque non varius est, lobortis convallis augue. Proin dignissim vitae diam sed pharetra. Praesent mattis lacus magna, sed ornare justo finibus ac. Integer mattis faucibus lacinia. Aenean nec erat consequat, maximus arcu nec, pharetra arcu. Integer porttitor libero libero, quis convallis ante molestie sed.

Sed quis leo pretium, eleifend purus eget, volutpat neque. Suspendisse sed ullamcorper ligula. Suspendisse potenti. Suspendisse eu elit eget magna lobortis rhoncus. Pellentesque ultricies turpis nec arcu rhoncus tempor. Proin laoreet tellus ac mi feugiat lobortis. Nunc non consectetur massa.""",
            cover = R.drawable.the_alchemist
        ), Book(
            title = "To Kill a Mockingbird",
            author = "Harper Lee",
            description = "An American novel and classic work of Southern Gothic literature that was first published in 1960. It was Harper Lee's first and only published novel."
        ), Book(
            title = "Pride and Prejudice",
            author = "Jane Austen",
            description = "A romantic novel of manners by Jane Austen, first published in 1813 under the title 'First Impressions'. The story follows the main character Elizabeth Bennet as she deals with issues of manners, upbringing, morality, education and marriage."
        ), Book(
            title = "One Hundred Years of Solitude",
            author = "Gabriel Garcia Marquez",
            description = "The novel tells the multi-generational story of the Buendía family's decadence under the tropical sun of Macondo,"
        ), Book(
            title = "Jane Eyre",
            author = "Charlotte Bronte",
            description = "An English novel by Charlotte Brontë, published on 16 October 1847 under the pseudonym 'Currer Bell'. The first person narrative follows the experiences of its heroine, Jane Eyre, from her childhood through to adulthood."
        ), Book(
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            description = "An American novel by F. Scott Fitzgerald published in 1925, set in the summer of 1922. The story is narrated by Nick Carraway."
        ), Book(
            title = "Brave New World",
            author = "Aldous Huxley",
            description = "A dystopian novel written in 1931 and published in 1932, set in a futuristic society where people are produced in an industrial environment."
        ), Book(
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            description = "A first-person narrative told by Holden Caulfield, who is an antihero seeking moral clarity and understanding between adolescence and adulthood as he faces the harsh realities of the world."
        ), Book(
            title = "Beloved",
            author = "Toni Morrison",
            description = "A novel by American author Toni Morrison. It was published in 1987, for which she won the Pulitzer Prize and National Book Critics Circle Award."
        ), Book(
            title = "The Hobbit",
            author = "J.R.R Tolkien",
            description = "An epic fantasy novel by J. R. R. Tolkien that was published in September 1937 to unprecedented critical acclaim."
        )
    )

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.books_for_adults_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                if (isSheetOpen) {
                    BookBottomSheet(
                        sheetState,
                        { isSheetOpen = false },
                        selectedBook
                    )
                }

                LazyColumn(Modifier.fillMaxWidth()) {
                    items(books) { book: Book ->
                        BookCard(book = book,
                            onClick1 = { isSheetOpen = true; selectedBook = book })
                    }
                }
            }
        }
    }
}

@Composable
fun Share(text: String, context: Context) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)


    FilledTonalButton(onClick = {
        startActivity(context, shareIntent, null)
    }) {
        Icon(imageVector = Icons.Default.Share, contentDescription = null)
        Text(stringResource(R.string.share), modifier = Modifier.padding(start = 8.dp))
    }
}
