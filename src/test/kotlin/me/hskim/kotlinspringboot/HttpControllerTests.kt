package me.hskim.kotlinspringboot

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllerTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val hskim = User("hskim", "Hs", "Kim")
        var article = Article("what is your name?", "My name is", "hskim", hskim)
        var article2 = Article("what is your name? 2", "My name is", "hskim", hskim)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(article, article2)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].author.login").value(hskim.login))
                .andExpect(jsonPath("\$.[0].slug").value(article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(hskim.login))
                .andExpect(jsonPath("\$.[1].slug").value(article2.slug))
    }

    @Test
    fun `List users`() {
        val hskim = User("hskim", "Hs", "Kim")
        val andykim = User("andykim", "Hs", "Kim")
        every { userRepository.findAll() } returns listOf(hskim, andykim)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].login").value(hskim.login))
                .andExpect(jsonPath("\$.[1].login").value(andykim.login))
    }
}