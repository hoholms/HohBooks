package com.hoholms.book.hohbooks.model

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