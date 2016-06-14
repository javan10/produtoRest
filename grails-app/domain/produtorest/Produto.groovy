package produtorest;

class Produto {

    String nome
    
    static belongsTo = [categoria:Categoria, unidadeMedidaPadrao:UnidadeMedida]
       
    static constraints = {
        nome nullable:false, blank:false, maxSize:128, unique:true
    }
    
    String toString(){
        this.nome
    }
}
