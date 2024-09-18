package br.com.study.entity

import br.com.study.dto.ArticleDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "articles")
class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        fun toEntity(articleDto: ArticleDto) = Article(title = articleDto.title, content = articleDto.content)
    }

}
