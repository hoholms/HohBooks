package com.hoholms.book.hohbooks.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hoholms.book.hohbooks.R

data class Book(
    val cover: Int = R.drawable.ic_launcher_foreground,
    val title: String = "Book",
    val author: String = "Author",
    val description: String = "Description"
) {
    override fun toString(): String {
        return """
            |===================================
            |📖 Title: $title
            |👤 Author: $author
            |📚 Description: 
            |$description
            |🎨 Cover ID: $cover
            |===================================
        """.trimMargin()
    }
}

@Composable
fun BookCard(book: Book = Book(), onClick1: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        onClick = onClick1
    ) {
        Row(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side: Book Cover
            Image(
                painter = painterResource(id = book.cover),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            // Right side: Title, Author, and Description
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = book.description, style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookCard() {
    val book = Book(
        cover = R.drawable.ic_launcher_foreground,
        title = "Sample Book Title",
        author = "John Doe",
        description = "A sample book description for preview purposes. This is a long description that provides more information about the book."
    )

    BookCard(book = book)
}
