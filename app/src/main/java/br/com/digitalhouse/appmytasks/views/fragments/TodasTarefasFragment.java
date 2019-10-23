package br.com.digitalhouse.appmytasks.views.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.appmytasks.R;
import br.com.digitalhouse.appmytasks.adapter.RecyclerViewTarefaAdapter;
import br.com.digitalhouse.appmytasks.data.Database;
import br.com.digitalhouse.appmytasks.data.TarefaDao;
import br.com.digitalhouse.appmytasks.model.Tarefa;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodasTarefasFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewTarefaAdapter adapter;
    private List<Tarefa> tarefaList = new ArrayList<>();
    private TarefaDao tarefaDao;

    public TodasTarefasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todas_tarefas, container, false);

        //TODO: Fazer as chamadas necessárias quando inicializado o fragmento

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewTodasTarefas);
        adapter = new RecyclerViewTarefaAdapter(tarefaList);

        //TODO: inicializar o DAO
        tarefaDao = Database.getDatabase(getContext()
    }

    //TODO: Desenvolver o método que busca todas as tarefas
    private void buscarTodosTarefas(){
        tarefaDao.getAllTarefas()
                //SubscribeOn: determina em qual thread o observable irá emitir os dados
                .subscribeOn(Schedulers.io())
                //ObserveOn: determina em qual thread será executa quando os dados forem emitidos
                .observeOn(AndroidSchedulers.mainThread())
                //Assina o observer ao observable
                //Se for sucesso irá pegar a lista de produtos e setar no método do adapter que atualiza a lista de produtos
                .subscribe(tarefas -> {
                            adapter.atualizaListaTarefa(tarefas);
                        },

                        //Senão irá lançar uma exceção
                        throwable -> {
                            Log.i("TAG", "método getAllTarefas" + throwable.getMessage());
                        });





    }


}
