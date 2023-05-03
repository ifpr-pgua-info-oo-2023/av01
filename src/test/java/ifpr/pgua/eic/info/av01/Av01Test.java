package ifpr.pgua.eic.info.av01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ifpr.pgua.eic.info.av01.Pessoa;
import ifpr.pgua.eic.info.av01.Registro;

/**
 * NÃO MODIFICAR!!!
 * 
 * CLIQUE NA SETINHA VERDE AO LADO DO NÚMERO DA LINHA DO MÉTODO.
 * IRÁ APARECER UMA CAIXA DIZENDO QUE TEM ERRO DE COMPILAÇÃO, PODE CLICAR
 * EM PROSSEGUIR.
 * O OBJETIVO É DEIXAR TODOS OS TESTES COM O CHECK VERDE. SE APARECER UM X
 * VERMELHO, SIGNIFICA QUE O TESTE NÃO ESTÁ PASSANDO.
 * PARA FAZER O TESTE PASSAR, MODIFIQUE AS CLASSES Pessoa.java e Registro.java,
 * CRIANDO O CÓDIGO NECESSÁRIO PARA FAZER O TESTE FUNCIONAR.
 */
public class Av01Test {
    
    @Test
    @DisplayName("A classe pessoa possui todos os atributos com os tipos definidos")
    public void testaTiposAtributosPessoa()throws NoSuchFieldException{
        Class<Pessoa> clazz = Pessoa.class;
        
        Field nome = clazz.getDeclaredField("nome");
        Field email = clazz.getDeclaredField("email");
        Field altura = clazz.getDeclaredField("altura");
        Field idade = clazz.getDeclaredField("idade");
        
        
        assertEquals(true,nome.getType()==String.class);
        assertEquals(true,email.getType()==String.class);
        assertEquals(true,altura.getType().getTypeName()=="double");
        assertEquals(true,idade.getType().getTypeName()=="int");
        
    }


    @Test
    @DisplayName("A classe pessoa possui todos os atributos e são privados")
    public void testaAtributosPessoa()throws NoSuchFieldException{
        Class<Pessoa> clazz = Pessoa.class;
        
        Field nome = clazz.getDeclaredField("nome");
        Field email = clazz.getDeclaredField("email");
        Field altura = clazz.getDeclaredField("altura");
        Field idade = clazz.getDeclaredField("idade");
        
        
        assertEquals(true,Modifier.isPrivate(nome.getModifiers()));
        assertEquals(true,Modifier.isPrivate(email.getModifiers()));
        assertEquals(true,Modifier.isPrivate(altura.getModifiers()));
        assertEquals(true,Modifier.isPrivate(idade.getModifiers()));
    }

    @Test
    @DisplayName("Existe somente um método construtor definido para a classe pessoa")
    public void testaConstrutorPessoa(){

        String nome = "Zé";
        String email = "ze@teste.com";
        double altura = 1.85;
        int idade = 2002;
        
        Pessoa pessoa = new Pessoa(nome,email,altura,idade);
        
        assertEquals(1, Pessoa.class.getDeclaredConstructors().length);

    }   

    @Test
    @DisplayName("Existe os getters e os atributos estão com valores corretos")
    public void testaGettersEInicializacaoPessoa(){

        String nome = "Zé";
        String email = "ze@teste.com";
        double altura = 1.85;
        int idade = 2002;

        Pessoa pessoa = new Pessoa(nome,email,altura,idade);
        


        assertEquals(pessoa.getNome(), nome);
        assertEquals(pessoa.getEmail(), email);
        assertEquals(pessoa.getAltura(), altura);
        assertEquals(pessoa.getIdade(), idade);
    }


    @Test
    @DisplayName("Existe os setters e os atributos são atualizados com valores corretos")
    public void testaSettersEAtualizacaoPessoa(){

        
        Pessoa pessoa = PessoaBuilder.umaPessoa().agora();

        
        String nome = "Chico";
        String email = "chico@teste.com";
        double altura = 1.45;
        int idade = 30;
        
        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setAltura(altura);
        pessoa.setIdade(idade);
        
        assertEquals(pessoa.getNome(), nome);
        assertEquals(pessoa.getEmail(), email);
        assertEquals(pessoa.getAltura(), altura);
        assertEquals(pessoa.getIdade(), idade);
    
    }

    @Test
    @DisplayName("A classe Registro existe e possui os atributos corretos e privados")
    public void testaRegistroDeclaradoCorreto() throws NoSuchFieldException{
        Class clazz = Registro.class;

        Field pessoas = clazz.getDeclaredField("pessoas");

        assertEquals(true, pessoas.getType() == ArrayList.class);
        

        assertEquals(true, Modifier.isPrivate(pessoas.getModifiers()));
        
    }

    @Test
    @DisplayName("A classe Registro possui somente o construtor declarado")
    public void testaInstanciarRegistro(){

        Registro registro = new Registro();
        
        assertEquals(1, Registro.class.getDeclaredConstructors().length);

    }

    @Test
    @DisplayName("A classe Registro permite listar todas as pessoas cadastradas")
    public void testaListaTodasPessoas(){
        
        Registro registro = new Registro();

        ArrayList<Pessoa> lista = registro.getPessoas();

        assertNotNull(lista);
        assertEquals(0, lista.size());

    }


    @Test
    @DisplayName("A classe Registro permite registrar uma pessoa e armazena no arraylist")
    public void testaCadastrarPessoa(){
        String nome = "Zé";
        String email = "ze@teste.com";
        double altura = 1.85;
        int idade = 2002;

        Registro registro = new Registro();
        
        boolean ret = registro.cadastraPessoa(nome,email,altura,idade);
        
        assertEquals(true,ret);
        assertEquals(1, registro.getPessoas().size());
        assertEquals(nome, registro.getPessoas().get(0).getNome());

    }


    @Test
    @DisplayName("A classe Registro não permite registrar duas pessoas com o mesmo email")
    public void testaNaoCadastrarEmailRepetido(){
        
        Registro registro = new Registro();
        
        String nome1 = "Zé";
        String email1 = "ze@teste.com";
        double altura1 = 1.85;
        int idade1 = 2002;

        String nome2 = "Chico";
        String email2 = "ze@teste.com";
        double altura2 = 1.85;
        int idade2 = 2002;


        boolean ret1 = registro.cadastraPessoa(nome1,email1,altura1,idade1);
        boolean ret2 = registro.cadastraPessoa(nome2,email2,altura2,idade2);
        
        assertEquals(true,ret1);
        assertEquals(false, ret2);
        assertEquals(1, registro.getPessoas().size());
        assertEquals(nome1, registro.getPessoas().get(0).getNome());
    
    }

    @Test
    @DisplayName("A classe Registro informa qual a maior idade")
    public void testaInformaMaiorIdade(){

        Pessoa pessoa1 = PessoaBuilder.umaPessoa().comEmail("p1@teste.com").comIdade(10).agora();
        Pessoa pessoa2 = PessoaBuilder.umaPessoa().comEmail("p2@teste.com").comIdade(30).agora();
        Pessoa pessoa3 = PessoaBuilder.umaPessoa().comEmail("p3@teste.com").comIdade(2).agora();
        Pessoa pessoa4 = PessoaBuilder.umaPessoa().comEmail("p4@teste.com").comIdade(14).agora();
        
        ArrayList<Pessoa> pessoas = new ArrayList<>(List.of(pessoa1,pessoa2,pessoa3,pessoa4));

        Registro registro = new Registro();

        for(Pessoa p:pessoas){
            registro.cadastraPessoa(p.getNome(),p.getEmail(),p.getAltura(),p.getIdade());
        }

        int maiorIdade = registro.buscarMaiorIdade();

        assertEquals(pessoa2.getIdade(),maiorIdade);
    }

    @Test
    @DisplayName("A classe Registro informa qual a media de altura das pessoas cadastradas")
    public void testaInformaMediaAltura(){

        Pessoa pessoa1 = PessoaBuilder.umaPessoa().comEmail("p1@teste.com").comAltura(1.65).agora();
        Pessoa pessoa2 = PessoaBuilder.umaPessoa().comEmail("p2@teste.com").comAltura(1.34).agora();
        Pessoa pessoa3 = PessoaBuilder.umaPessoa().comEmail("p3@teste.com").comAltura(1.87).agora();
        Pessoa pessoa4 = PessoaBuilder.umaPessoa().comEmail("p4@teste.com").comAltura(1.55).agora();
        
        ArrayList<Pessoa> pessoas = new ArrayList<>(List.of(pessoa1,pessoa3,pessoa2,pessoa4));

        Registro registro = new Registro();

        double soma = 0.0;

        for(Pessoa p:pessoas){
            registro.cadastraPessoa(p.getNome(),p.getEmail(),p.getAltura(),p.getIdade());
            soma += p.getAltura();
        }

        double mediaReal = soma/pessoas.size();

        double mediaObtida = registro.calcularMediaAltura();

        assertEquals(mediaReal,mediaObtida,0.0001);
    }


}
