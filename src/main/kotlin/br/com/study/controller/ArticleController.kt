package br.com.study.controller

import br.com.study.dto.ArticleDto
import br.com.study.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/articles")
class ArticleController(val articleService: ArticleService) {

    @GetMapping
    fun findByTitle(@RequestParam title: String?) = articleService.findAllByTitle(title)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody articleDto: ArticleDto) = articleService.save(articleDto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody articleDto: ArticleDto) = articleService.update(id, articleDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = articleService.deleteById(id)

}