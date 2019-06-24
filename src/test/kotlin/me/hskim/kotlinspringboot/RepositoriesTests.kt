package me.hskim.kotlinspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests  @Autowired constructor (var entityManager : TestEntityManager,
                                                 var userRepository: UserRepository,
                                                 var articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        var hskim = User("springHs", "Hs", "Kim")
        entityManager.persist(hskim)
        var article = Article("My name is", "hyun seok kim and ..", "Blah blah", hskim)
        entityManager.persist(article)
        entityManager.flush()
        var found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        var hskim = User("springHs", "Hs", "Kim")
        entityManager.persist(hskim)
        entityManager.flush()
        var user = userRepository.findByLogin(hskim.login)
        assertThat(user).isEqualTo(hskim)
    }
}