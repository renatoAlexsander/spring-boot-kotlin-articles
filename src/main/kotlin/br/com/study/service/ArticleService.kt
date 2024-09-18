package br.com.study.service

import br.com.study.dto.ArticleDto
import br.com.study.entity.Article
import br.com.study.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ArticleService(val articleRepository: ArticleRepository) {

    fun save(articleDto: ArticleDto): Article {
        val article = Article.toEntity(articleDto)
        return articleRepository.save(article)
    }

    fun update(id: Long, articleDto: ArticleDto) =
        articleRepository.findById(id)
            .map {
                val article = Article(id = it.id, title = articleDto.title, content = articleDto.content)
                articleRepository.save(article)
            }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun findAllByTitle(title: String?): List<Article> {
        if (title != null) {
            val articles = articleRepository.findByTitle(title)

            if (articles.isEmpty()) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum artigo encontrado com o título: $title")
            }

            return articles
        }
        return articleRepository.findAll();
    }

    fun deleteById(id: Long) {
        val articleToRemove = articleRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        articleRepository.delete(articleToRemove)
    }

}