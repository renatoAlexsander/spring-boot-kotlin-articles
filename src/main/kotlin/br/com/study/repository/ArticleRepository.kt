package br.com.study.repository

import br.com.study.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {

    fun findByTitle(title: String): List<Article>
}