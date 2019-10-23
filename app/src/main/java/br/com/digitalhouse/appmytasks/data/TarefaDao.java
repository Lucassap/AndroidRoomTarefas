package br.com.digitalhouse.appmytasks.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.appmytasks.model.Tarefa;
import io.reactivex.Observable;

//TODO: Implementar a anotação referente ao DAO
@Dao
public interface TarefaDao {

   //TODO: Implementar todos os métodos de consulta
    // O app precisará de:
    // - Inserção de dados

    @Delete
    void deletaTarefa(Tarefa tarefa);

    @Query("SELECT * FROM tarefas WHERE id = :id")
    Tarefa getById(long id);

    @Query("SELECT * FROM tarefas WHERE nome = :nomeProduto")
    Tarefa getByNome(String nomeProduto);

    @Insert
    void insereTarefa(Tarefa tarefa);
    //- Consulta de todos os dados
    @Query("SELECT *  FROM tarefas")
    Observable<List<Tarefa>> getAllTarefas();

    // - Consulta contendo um limite de 5 dados em ordem decrescente
    @Query("SELECT *  FROM tarefas ORDER BY ID LIMIT 5 ")
    Observable<List<Tarefa>> getAllTarefasLimitadas();

    @Update
    void updateTarefas(Tarefa tarefa);


}
