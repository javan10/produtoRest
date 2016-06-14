package produtorest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CategoriaController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Categoria.list(params), model:[categoriaCount: Categoria.count()]
    }

    def show(Categoria categoria) {
        respond categoria
    }

    @Transactional
    def save(Categoria categoria) {
        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond categoria.errors, view:'create'
            return
        }

        categoria.save flush:true

        respond categoria, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Categoria categoria) {
        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond categoria.errors, view:'edit'
            return
        }

        categoria.save flush:true

        respond categoria, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Categoria categoria) {

        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        categoria.delete flush:true

        render status: NO_CONTENT
    }
}
