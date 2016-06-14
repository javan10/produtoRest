package produtorest;

class UnidadeMedida {
   
    String nome;
    
    String abreviacao;
    //static belongsTo = [produto:Produto]
    
    static constraints = {
        nome nullable:false, blank:false, maxSize:128, unique:true
        abreviacao nullable:false, blank:false, maxSize:3, unique:true
    }
    
    String toString(){
       this.nome
    }
}
