package com.naszi.mobilapp.foodcategories


import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.repository.CommentRepository
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val mockRepository: CommentRepository = mockk()
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel(mockRepository)
    }
    @Test
    fun `test addComment success`() = coroutineRule.run {
        // Arrangement: Tesztelendő objektum és annak fiktív működése
        val commentToAdd = Comment(
            id = 1,
            categoryItemId = 1,
            comment = "Test comment"
        )
        coEvery { mockRepository.addComment(any()) } answers {  }

        // Action: A tesztelendő függvény meghívása
        mainViewModel.addComment(commentToAdd)

        /**
         * Assertion: Nincs hiba a függvény hívása során
         * Ha a függvény hibát dobna, a tesztelés itt hibával megszakadna
         */
    }
    @Test
    fun `test updateComment success`() = coroutineRule.run {
        // Arrangement: Tesztelendő objektum és annak fiktív működése
        val categoryIdToUpdate = 1
        val commentToUpdate = Comment(
            id = 1,
            categoryItemId = categoryIdToUpdate,
            comment = "Updated comment"
        )
        coEvery { mockRepository.updateComment(any()) } answers {  }

        // Action: A tesztelendő függvény meghívása
        mainViewModel.updateComment(categoryIdToUpdate, commentToUpdate)

        /**
         * Assertion: Nincs hiba a függvény hívása során
         * Ha a függvény hibát dobna, a tesztelés itt hibával megszakadna
         */
    }
    @Test
    fun `test deleteComment success`() = coroutineRule.run {
        // Arrangement: Tesztelendő objektum és annak fiktív működése
        val commentToDelete = Comment(id = 1, categoryItemId = 1, comment = "Comment to delete")
        coEvery { mockRepository.deleteComment(any()) } answers { /* Tesztelendő objektum működése */ }

        // Action: A tesztelendő függvény meghívása
        mainViewModel.deleteComment(commentToDelete)

        /**
         * Assertion: Nincs hiba a függvény hívása során
         * Ha a függvény hibát dobna, a tesztelés itt hibával megszakadna
         */
    }
}