package produtorest;

class Categoria {

    String nome
    
    static belongsTo = [categoriaPai:Categoria]
    
    static hasMany = [filhos:Categoria] 
    
    static constraints = {
        nome nullable:false, blank:false, maxSize:128, unique:true
        categoriaPai nullable:true 
    }       
    
    String toString(){
        this.nome
    }
}
