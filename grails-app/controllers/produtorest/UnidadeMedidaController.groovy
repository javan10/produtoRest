package produtorest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UnidadeMedidaController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UnidadeMedida.list(params), model:[unidadeMedidaCount: UnidadeMedida.count()]
    }

    def show(UnidadeMedida unidadeMedida) {
        respond unidadeMedida
    }

    @Transactional
    def save(UnidadeMedida unidadeMedida) {
        if (unidadeMedida == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (unidadeMedida.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond unidadeMedida.errors, view:'create'
            return
        }

        unidadeMedida.save flush:true

        respond unidadeMedida, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(UnidadeMedida unidadeMedida) {
        if (unidadeMedida == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (unidadeMedida.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond unidadeMedida.errors, view:'edit'
            return
        }

        unidadeMedida.save flush:true

        respond unidadeMedida, [status: OK, view:"show"]
    }

    @Transactional
    def delete(UnidadeMedida unidadeMedida) {

        if (unidadeMedida == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        unidadeMedida.delete flush:true

        render status: NO_CONTENT
    }
}
