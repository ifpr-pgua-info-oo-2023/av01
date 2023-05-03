/**************/
/*REPITA COMIGO:*/
/*NÃO PRECISO ALTERAR ESSE ARQUIVO*/
/*NÃO PRECISO ALTERAR ESSE ARQUIVO*/
/*NÃO PRECISO ALTERAR ESSE ARQUIVO*/
/*NÃO PRECISO ALTERAR ESSE ARQUIVO*/
package ifpr.pgua.eic.info.av01;

import ifpr.pgua.eic.info.av01.Pessoa;

public class PessoaBuilder{


    private static PessoaBuilder instance;
    private Pessoa pessoa = new Pessoa("Zé", "ze@teste.com", 1.5,20);

    private PessoaBuilder(){}

    public static PessoaBuilder umaPessoa(){

        instance = new PessoaBuilder();
        return instance;
    }

    public PessoaBuilder comNome(String nome){
        instance.pessoa.setNome(nome);
        return instance;
    }

    public PessoaBuilder comEmail(String email){
        instance.pessoa.setEmail(email);
        return instance;
    }

    public PessoaBuilder comIdade(int idade){
        instance.pessoa.setIdade(idade);
        return instance;
    }

    public PessoaBuilder comAltura(double altura){
        instance.pessoa.setAltura(altura);
        return instance;
    }

    public Pessoa agora(){
        return instance.pessoa;
    }

}