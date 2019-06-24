package me.hskim.kotlinspringboot

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }
        return "Blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository.findBySlug(slug)
                                ?.render()
                                ?: throw IllegalArgumentException("Wrong article slug provided")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RendererArticle (slug, title, headline, content, author, addedAt.format())

    data class RendererArticle(var slug: String,
                               val title: String,
                               var headline: String,
                               var content: String,
                               var author: User,
                               var addedAt: String)
}