package me.hskim.kotlinspringboot

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {
    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner  {

        val hskim = userRepository.save(User("hskim", "Hs", "Kim"))

        articleRepository.save(Article(
                title = "what is your name?",
                headline = "My name is",
                content = "hskim",
                author = hskim
        ))

        articleRepository.save(Article(
                title = "what is your name? 2",
                headline = "My name is",
                content = "hskim",
                author = hskim
        ))
    }
}